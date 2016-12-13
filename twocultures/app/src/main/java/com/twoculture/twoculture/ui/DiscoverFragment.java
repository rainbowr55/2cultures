package com.twoculture.twoculture.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twoculture.base.widget.LoadingDialog;
import com.twoculture.base.widget.ToastUtil;
import com.twoculture.twoculture.R;
import com.twoculture.twoculture.adapter.MyUserRecyclerViewAdapter;
import com.twoculture.twoculture.models.User;
import com.twoculture.twoculture.presenter.UserPresenter;
import com.twoculture.twoculture.ui.interfaces.UserClickListener;
import com.twoculture.twoculture.views.IUserView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiscoverFragment extends Fragment implements IUserView, UserClickListener {


    @BindView(R.id.rv_users)
    RecyclerView rvUsers;
    private Context context;
    private MyUserRecyclerViewAdapter userRecyclerViewAdapter;
    private UserPresenter userPresenter;
    private LoadingDialog loadingDialog;

    public DiscoverFragment() {
    }

    public static DiscoverFragment newInstance() {
        DiscoverFragment fragment = new DiscoverFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        userRecyclerViewAdapter = new MyUserRecyclerViewAdapter(context,this);
        rvUsers.setAdapter(userRecyclerViewAdapter);
        initData();

    }

    private void initData() {
        userPresenter = new UserPresenter(this);
        userPresenter.searchUser("");
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
        if (users != null)
            userRecyclerViewAdapter.addDatas(users);
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
    public void onUserClick(User item) {

    }
}
