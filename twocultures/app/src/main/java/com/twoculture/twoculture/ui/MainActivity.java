package com.twoculture.twoculture.ui;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.hyphenate.exceptions.HyphenateException;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.twoculture.base.utils.SecureSharedPreferences;
import com.twoculture.easemob.DemoHelper;
import com.twoculture.easemob.TwoCApplication;
import com.twoculture.easemob.db.DemoDBManager;
import com.twoculture.twoculture.R;
import com.twoculture.twoculture.models.UserProfile;
import com.twoculture.twoculture.presenter.UserProfilePresenter;
import com.twoculture.twoculture.tools.AppConstants;
import com.twoculture.twoculture.tools.CLog;
import com.twoculture.twoculture.views.IUserProfileView;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, IUserProfileView {
    private static final String TAG = "MainActivity";
    private UserProfilePresenter userProfilePresenter;

    @BindView(R.id.container)
    ViewPager mViewPager;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    private NavigationView nav_view;
    @BindView(R.id.toolbar)
    Toolbar mToolBar;
    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (progressDialog != null && !MainActivity.this.isFinishing() && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    private void initData() {
        userProfilePresenter = new UserProfilePresenter(this);
        userProfilePresenter.getUserProfile();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    private void initView() {
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);
        setSupportActionBar(mToolBar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.tab_topic:
                        mViewPager.setCurrentItem(0);

                        break;
                    case R.id.tab_messages:
                        mViewPager.setCurrentItem(1);

                        break;
                    case R.id.tab_event:
                        mViewPager.setCurrentItem(2);

                        break;
                    case R.id.tab_friends:
                        mViewPager.setCurrentItem(3);

                        break;
                }
            }
        });
        setTitle("2Cultures");

        progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialog) {
                CLog.d(TAG, "EMClient.getInstance().onCancel");
                progressShow = false;
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_setting) {

        } else if (id == R.id.nav_friends) {

        } else if (id == R.id.nav_invitation) {

        } else if (id == R.id.nav_me) {

        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public void onLoadSuccess(UserProfile userProfile) {
        progressDialog.setMessage(getString(com.twoculture.easemob.R.string.Is_landing));
        progressDialog.show();
        SecureSharedPreferences secureSharedPreferences = new SecureSharedPreferences(this);
        if (userProfile != null) {
            //保存profile
            secureSharedPreferences.edit().putBoolean(AppConstants.USER_INIT_STATE, userProfile.is_init_profile);
            //处理环信
            if (DemoHelper.getInstance().isLoggedIn()) {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                progressDialog.dismiss();
            } else {
                loginEasemob(userProfile);
            }
            if (!userProfile.is_init_profile) {
                Intent intent = new Intent(this, MyProfileActivity.class);
                intent.putExtra(MyProfileActivity.IS_INIT_KEY, true);
                startActivity(intent);
            }
        } else {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onLoadFailed() {
        //token过期需要重新登录
        Intent intent = new Intent(MainActivity.this, LaunchActivity.class);
        startActivity(intent);
        this.finish();
    }

    private boolean progressShow;

    /**
     * login
     */
    public void loginEasemob(UserProfile userProfile) {
        if (!EaseCommonUtils.isNetWorkConnected(this)) {
            Toast.makeText(this, com.twoculture.easemob.R.string.network_isnot_available, Toast.LENGTH_SHORT).show();
            return;
        }
        if (userProfile.id < 0) {
            CLog.d(TAG, "userProfile id error");
            return;
        }
        // After logout，the DemoDB may still be accessed due to async callback, so the DemoDB will be re-opened again.
        // close it before login to make sure DemoDB not overlap
        DemoDBManager.getInstance().closeDB();

        // reset current user name before login
        DemoHelper.getInstance().setCurrentUserName(userProfile.id + "");

        final long start = System.currentTimeMillis();
        // call login method
        CLog.d(TAG, "EMClient.getInstance().login");
        EMClient.getInstance().login(userProfile.id + "", userProfile.id + "", new EMCallBack() {

            @Override
            public void onSuccess() {
                CLog.d(TAG, "login: onSuccess");


                // ** manually load all local groups and conversation
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();

                // update current user's display name for APNs
                boolean updatenick = EMClient.getInstance().updateCurrentUserNick(
                        TwoCApplication.currentUserNick.trim());
                if (!updatenick) {
                    CLog.e("LoginActivity", "update current user nick fail");
                }

                if (!MainActivity.this.isFinishing() && progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                // get user's info (this should be get from App's server or 3rd party service)
                DemoHelper.getInstance().getUserProfileManager().asyncGetCurrentUserInfo();

                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);

                finish();
            }

            @Override
            public void onProgress(int progress, String status) {
                CLog.d(TAG, "login: onProgress");
            }

            @Override
            public void onError(final int code, final String message) {
                CLog.d(TAG, "login: onError: " + code);
                CLog.d(TAG, "login failed reason:" + message);
                if (code == 204) {
                    registerEasemob(userProfile);
                }
                if (!progressShow) {
                    return;
                }
                runOnUiThread(new Runnable() {
                    public void run() {
                        progressDialog.dismiss();
//                        Toast.makeText(getApplicationContext(), getString(com.twoculture.easemob.R.string.Login_failed) + message,
//                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


    private void registerEasemob(UserProfile userProfile) {
        CLog.d(TAG, "register start");
        new Thread(new Runnable() {
            public void run() {
                try {
                    // call method in SDK
                    EMClient.getInstance().createAccount(userProfile.id + "", userProfile.id + "");
                    runOnUiThread(new Runnable() {
                        public void run() {
                            if (MainActivity.this.isFinishing())
                                progressDialog.dismiss();
                            // save current user
                            DemoHelper.getInstance().setCurrentUserName(userProfile.id + "");
                            CLog.d(TAG, "register sucess");
                            //Toast.makeText(getApplicationContext(), getResources().getString(R.string.Registered_successfully), Toast.LENGTH_SHORT).show();

                        }
                    });
                } catch (final HyphenateException e) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            if (MainActivity.this.isFinishing())
                                progressDialog.dismiss();
                            int errorCode = e.getErrorCode();
                            CLog.d(TAG, "register failed reason:" + errorCode);
                        }
                    });
                }
            }
        }).start();
    }

    @Override
    public void onLoadingShow() {

    }

    @Override
    public void setMessage(String msg) {

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new TopicsFragment();
            }
            if (position == 1) {
                return new MessagesFragment();
            }
            if (position == 2) {
                return new EventsFragment();
            }
            if (position == 3) {
                return new MessagesFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 0";
                case 1:
                    return "SECTION 1";
                case 2:
                    return "SECTION 2";
                case 3:
                    return "SECTION 3";
            }
            return null;
        }
    }
}
