package com.twoculture.twoculture.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.twoculture.base.widget.ToastUtil;
import com.twoculture.twoculture.R;
import com.twoculture.twoculture.models.Configure;
import com.twoculture.twoculture.models.District;
import com.twoculture.twoculture.models.Language;
import com.twoculture.twoculture.models.UserProfile;
import com.twoculture.twoculture.models.UserStatu;
import com.twoculture.twoculture.presenter.ConfigurePresenter;
import com.twoculture.twoculture.presenter.UserProfilePresenter;
import com.twoculture.twoculture.tools.RoundedImageView;
import com.twoculture.twoculture.ui.fragment.CityDialogFragment;
import com.twoculture.twoculture.ui.fragment.CountryDialogFragment;
import com.twoculture.twoculture.ui.fragment.EditNameDialogFragment;
import com.twoculture.twoculture.ui.fragment.GenderDialogFragment;
import com.twoculture.twoculture.ui.fragment.LanguageDialogFragment;
import com.twoculture.twoculture.ui.fragment.UserStatusDialogFragment;
import com.twoculture.twoculture.ui.interfaces.GenderClickListener;
import com.twoculture.twoculture.ui.interfaces.LanguageClickListener;
import com.twoculture.twoculture.ui.interfaces.UserNameInputListener;
import com.twoculture.twoculture.ui.interfaces.UserStatusClickListener;
import com.twoculture.twoculture.views.IConfigureView;
import com.twoculture.twoculture.views.IUserProfileView;

import butterknife.BindView;

public class MyProfileActivity extends BaseActivity implements View.OnClickListener, IConfigureView, IUserProfileView, LanguageClickListener, GenderClickListener, UserNameInputListener, UserStatusClickListener {

    public static final String IS_INIT_KEY = "isInit";
    private static final int DISTRICT_REQUEST_CODE = 100;
    public static final int DISTRICT_FROM = 1;
    public static final int DISTRICT_LIVE_IN = 2;
    public static final int DISTRICT_MIGRATE = 3;

    public static final int LANGUAGE_SPEAK = 1;
    public static final int LANGUAGE_SECOND = 2;
    public static final int LANGUAGE_LEARN = 3;

    @BindView(R.id.tv_profile_name)
    TextView tvProfileName;
    @BindView(R.id.tv_profile_gender)
    TextView tvProfileGender;
    @BindView(R.id.tv_profile_from)
    TextView tvProfileFrom;
    @BindView(R.id.tv_profile_living_in)
    TextView tvProfileLivingIn;
    @BindView(R.id.tv_profile_migrate_to)
    TextView tvProfileMigrateTo;
    @BindView(R.id.tv_profile_speak)
    TextView tvProfileSpeak;
    @BindView(R.id.tv_profile_language)
    TextView tvProfileLanguage;
    @BindView(R.id.tv_profile_learning_language)
    TextView tvProfileLearningLanguage;
    @BindView(R.id.tv_profile_relationship)
    TextView tvProfileRelationship;

    @BindView(R.id.iv_add_head_image)
    RoundedImageView ivAddHeadImage;
    @BindView(R.id.rl_profile_name)
    RelativeLayout rlProfileName;
    @BindView(R.id.rl_profile_gender)
    RelativeLayout rlProfileGender;
    @BindView(R.id.rl_profile_from)
    RelativeLayout rlProfileFrom;
    @BindView(R.id.rl_profile_living_in)
    RelativeLayout rlProfileLivingIn;
    @BindView(R.id.rl_profile_migrate_to)
    RelativeLayout rlProfileMigrateTo;
    @BindView(R.id.rl_profile_speak)
    RelativeLayout rlProfileSpeak;
    @BindView(R.id.rl_profile_language)
    RelativeLayout rlProfileLanguage;
    @BindView(R.id.rl_profile_learning_language)
    RelativeLayout rlProfileLearningLanguage;
    @BindView(R.id.rl_profile_relationship)
    RelativeLayout rlProfileRelationship;

    CountryDialogFragment countryFragment;
    CityDialogFragment cityDialogFragment;
    LanguageDialogFragment languageDialogFragment;
    GenderDialogFragment genderDialogFragment;
    EditNameDialogFragment editNameDialogFragment;
    UserStatusDialogFragment userStatusDialogFragment;

    private ConfigurePresenter configurePresenter;
    Configure configure;
    private int districtType = -1;
    private int languageType = -1;

    private District fromDistrict;
    private District liveDistrict;
    private District migrateDistrict;

    private Language speakLanguage;
    private Language secondLanguage;
    private Language learningLanguage;

    private int userStatu = -1;
    private UserProfile userProfile = new UserProfile();

    private boolean isInit;

    private UserProfilePresenter userProfilePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        userProfilePresenter = new UserProfilePresenter(this);
        configurePresenter = new ConfigurePresenter(this);
        configurePresenter.getConfigure();
    }

    private void initView() {
        tvTopRight.setOnClickListener(this);
        rlProfileName.setOnClickListener(this);
        rlProfileGender.setOnClickListener(this);
        rlProfileFrom.setOnClickListener(this);
        rlProfileLivingIn.setOnClickListener(this);
        rlProfileMigrateTo.setOnClickListener(this);
        rlProfileSpeak.setOnClickListener(this);
        rlProfileLanguage.setOnClickListener(this);
        rlProfileLearningLanguage.setOnClickListener(this);
        rlProfileRelationship.setOnClickListener(this);
        countryFragment = new CountryDialogFragment();
        cityDialogFragment = new CityDialogFragment();
        languageDialogFragment = new LanguageDialogFragment();
        genderDialogFragment = new GenderDialogFragment();
        editNameDialogFragment = new EditNameDialogFragment();
        userStatusDialogFragment = new UserStatusDialogFragment();
        isInit = getIntent().getBooleanExtra(IS_INIT_KEY, false);
    }

    @Override
    protected String getTitleName() {
        return this.getString(R.string.my_profile_title);
    }

    @Override
    protected String getRightTitle() {
        return this.getString(R.string.my_profile_right_title);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_my_profile_activiry;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_top_right://save
                onRightTitleClick();
                break;
            case R.id.rl_profile_name://name
                if (editNameDialogFragment != null) {
                    editNameDialogFragment.show(getFragmentManager(), "username");
                }
                break;
            case R.id.rl_profile_gender:
                if (genderDialogFragment != null) {
                    genderDialogFragment.show(getSupportFragmentManager(), "gender");
                }
                break;
            case R.id.rl_profile_from:
                goDistrict(DISTRICT_FROM);
                break;
            case R.id.rl_profile_living_in:
                goDistrict(DISTRICT_LIVE_IN);
                break;
            case R.id.rl_profile_migrate_to:
                goDistrict(DISTRICT_MIGRATE);
                break;
            case R.id.rl_profile_speak:
                goLanguage(LANGUAGE_SPEAK);
                break;
            case R.id.rl_profile_language:
                goLanguage(LANGUAGE_SECOND);
                break;
            case R.id.rl_profile_learning_language:
                goLanguage(LANGUAGE_LEARN);
                break;
            case R.id.rl_profile_relationship:
                if (userStatusDialogFragment != null && configure != null && configure.user_status != null) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("status", configure.user_status);
                    userStatusDialogFragment.setArguments(bundle);
                    userStatusDialogFragment.show(getSupportFragmentManager(), "status");
                }
                break;
            default:
                break;
        }
    }

    private void goLanguage(int type) {
        languageType = type;
        if (languageDialogFragment != null && configure != null && configure.languages != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("language", configure.languages);
            languageDialogFragment.setArguments(bundle);
            languageDialogFragment.show(getSupportFragmentManager(), "language");
        }
    }

    private void goDistrict(int type) {
        districtType = type;
        if (configure != null && configure.countries != null && configure.countries.size() > 0) {
            Intent intent = new Intent();
            intent.putExtra("country", configure.countries);

            intent.setClass(this, CountryListActivity.class);
            startActivityForResult(intent, DISTRICT_REQUEST_CODE);
        }
    }

    @Override
    public void onLoadConfigSuccess(Configure configure) {
        dismissLoadding();
        if (configure != null) {
            this.configure = configure;
        }
        if (!isInit) {
            if (userProfile != null) {
                initProfile(userProfile);
            }
        }
    }

    private void initProfile(UserProfile userProfile) {

        tvProfileName.setText(userProfile.name);
        if (userProfile.gender == 0) {
            tvProfileGender.setText(getString(R.string.gender_female));
        } else {
            tvProfileGender.setText(getString(R.string.gender_male));
        }
        tvProfileSpeak.setText(userProfile.speak);
        tvProfileLivingIn.setText(userProfile.living_city_str);
        tvProfileFrom.setText(userProfile.from_city_str);
        tvProfileMigrateTo.setText(userProfile.migrate_to_city_str);
        tvProfileRelationship.setText(userProfile.status);
        this.userProfile = userProfile;
        userStatu = userProfile.status_id;

    }

    @Override
    public void onLoadingShow() {
        showLoading();
    }

    @Override
    public void setMessage(String msg) {
        showToast(msg);
        dismissLoadding();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (DISTRICT_REQUEST_CODE == requestCode) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    District district = (District) data.getSerializableExtra("district");
                    String districtName = district.countryName + "     " + district.cityName;
                    if (districtType == DISTRICT_LIVE_IN) {
                        liveDistrict = district;
                        tvProfileLivingIn.setText(districtName);
                        userProfile.living_country_id = liveDistrict.countryId;
                        userProfile.living_city_id = liveDistrict.cityId;
                    } else if (districtType == DISTRICT_MIGRATE) {
                        migrateDistrict = district;
                        tvProfileMigrateTo.setText(districtName);
                        userProfile.migrate_to_country_id = migrateDistrict.countryId;
                        userProfile.migrate_to_city_id = migrateDistrict.cityId;
                    } else if (districtType == DISTRICT_FROM) {
                        fromDistrict = district;
                        tvProfileFrom.setText(districtName);
                        userProfile.from_country_id = fromDistrict.countryId;
                        userProfile.from_city_id = fromDistrict.cityId;
                    }

                }
            }
        }

    }

    protected void onRightTitleClick() {
        //save profile
        if (isInit) {
            if (isInputValid()) {
                saveProfile();
            } else {
                ToastUtil.showMiddleToast(this, getString(R.string.profile_input_valid_hint));
            }
        } else {//不是刚初始化
            saveProfile();
        }
    }

    private void saveProfile() {
        //获取表单内容
        String inputName = tvProfileName.getText().toString();
        String name = TextUtils.isEmpty(inputName) ? userProfile.name : inputName;
        userStatu = userStatu == -1 ? userProfile.status_id : userStatu;
        userProfilePresenter.updateUserProfile(name, userStatu, this.userProfile);
    }

    /**
     * check the user whether finish must-fill
     *
     * @return
     */
    private boolean isInputValid() {
        if (TextUtils.isEmpty(tvProfileName.getText().toString())) {
            return false;
        } else if (TextUtils.isEmpty(tvProfileGender.getText().toString())) {
            return false;
        } else if (fromDistrict == null) {
            return false;
        } else if (liveDistrict == null) {
            return false;
        } else if (speakLanguage == null) {
            return false;
        } else if (userStatu == -1) {
            return false;
        }
        return true;
    }

    @Override
    public void onLanguageClick(Language language) {
        if (language != null) {
            if (languageType == LANGUAGE_SPEAK) {
                speakLanguage = language;
                tvProfileSpeak.setText(language.name);
                userProfile.speak_id = speakLanguage.id;
            } else if (languageType == LANGUAGE_SECOND) {
                secondLanguage = language;
                tvProfileLanguage.setText(language.name);
                userProfile.second_language_id = secondLanguage.id;
            } else if (languageType == LANGUAGE_LEARN) {
                learningLanguage = language;
                tvProfileLearningLanguage.setText(language.name);
                userProfile.learning_language_id = learningLanguage.id;
            }
        }
        if (languageDialogFragment != null) {
            languageDialogFragment.dismiss();
        }
    }

    @Override
    public void onGenderClick(int gender) {
        genderDialogFragment.dismiss();
        if (gender == 0) {//female
            tvProfileGender.setText(getString(R.string.gender_female));
        } else {
            tvProfileGender.setText(getString(R.string.gender_male));
        }
        userProfile.gender = gender;
    }

    @Override
    public void setUserName(String userName) {
        tvProfileName.setText(userName);
    }

    @Override
    public void onUserStatusClick(UserStatu userStatu) {
        userStatusDialogFragment.dismiss();
        if (userStatu != null) {
            this.userStatu = userStatu.id;
            tvProfileRelationship.setText(userStatu.name);
        }
    }

    @Override
    public void onLoadSuccess(UserProfile userProfile) {

    }

    @Override
    public void onLoadFailed() {

    }
}
