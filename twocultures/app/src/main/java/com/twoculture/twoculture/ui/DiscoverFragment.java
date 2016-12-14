package com.twoculture.twoculture.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.twoculture.base.widget.LoadingDialog;
import com.twoculture.base.widget.ToastUtil;
import com.twoculture.easemob.Constant;
import com.twoculture.twoculture.R;
import com.twoculture.twoculture.adapter.MyUserRecyclerViewAdapter;
import com.twoculture.twoculture.models.User;
import com.twoculture.twoculture.presenter.UserPresenter;
import com.twoculture.twoculture.ui.interfaces.UserClickListener;
import com.twoculture.twoculture.views.IUserView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiscoverFragment extends Fragment implements IUserView, UserClickListener, View.OnClickListener, TextView.OnEditorActionListener {

    public static String DISCOVER_TYPE_KEY = "type";
    public static int DISCOVER_TYPE = 1;
    public static int FRIEND_TYPE = 2;

    private int type = DISCOVER_TYPE;
    @BindView(R.id.rv_users)
    RecyclerView rvUsers;
    @BindView(R.id.rr_search_main)
    RelativeLayout rrSearchMain;
    @BindView(R.id.et_search_user)
    EditText etSearchUser;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    private Context context;
    private MyUserRecyclerViewAdapter userRecyclerViewAdapter;
    private UserPresenter userPresenter;
    private LoadingDialog loadingDialog;

    public DiscoverFragment() {
    }

    public static DiscoverFragment newInstance(int type) {
        DiscoverFragment fragment = new DiscoverFragment();
        Bundle args = new Bundle();
        args.putInt(DISCOVER_TYPE_KEY, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getInt(DISCOVER_TYPE_KEY, DISCOVER_TYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        rvUsers.setLayoutManager(new LinearLayoutManager(context));
        userRecyclerViewAdapter = new MyUserRecyclerViewAdapter(context, this);
        rvUsers.setAdapter(userRecyclerViewAdapter);
        initView();
        initData();

    }

    private void initView() {
        ivSearch.setOnClickListener(this);
        etSearchUser.setOnEditorActionListener(this);
    }

    private void initData() {
        userPresenter = new UserPresenter(this);
        if (type == DISCOVER_TYPE) {
            userPresenter.searchUser("");
            rrSearchMain.setVisibility(View.VISIBLE);
        } else {
            userPresenter.getMyFriend();
            rrSearchMain.setVisibility(View.GONE);
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }


    @Override
    public void onLoadSuccess(List<User> users) {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
        if (users != null && users.size() > 0) {
            userRecyclerViewAdapter.addDatas(users);
        } else {
            ToastUtil.showMiddleToast(context, context.getString(R.string.no_data_hint));
        }

    }

    @Override
    public void onLoadFailed() {

    }

    @Override
    public void onLoadingShow() {

        if (loadingDialog == null) {
            loadingDialog = LoadingDialog.show(context, "", false, null);
        } else {
            loadingDialog.show();
        }
    }

    @Override
    public void setMessage(String msg) {
        ToastUtil.showMiddleToast(context, msg);
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }


    @Override
    public void onUserClick(User user) {
        if (user != null) {
            Intent intent = new Intent();
            intent.setClass(context, UserInfoDetailActivity.class);
            intent.putExtra(Constant.EXTRA_USER_ID, user.user_id + "");
            context.startActivity(intent);
        }
    }

    public void search() {
        String keyWord = etSearchUser.getText().toString().trim();
        if (TextUtils.isEmpty(keyWord)) {
            ToastUtil.showMiddleToast(context, context.getString(R.string.user_search_input_empty_hint));
            return;
        }
        if (userPresenter != null) {
            userPresenter.searchUser(keyWord);
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.iv_search:
                search();
                break;
        }
    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
            search();
            return true;
        }
        return false;
    }
}
