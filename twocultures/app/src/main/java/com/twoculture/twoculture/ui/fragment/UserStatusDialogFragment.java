package com.twoculture.twoculture.ui.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.twoculture.twoculture.R;
import com.twoculture.twoculture.adapter.UserStatusAdapter;
import com.twoculture.twoculture.models.UserStatu;

import java.util.ArrayList;

/**
 * Created by liangcaihong on 2016/12/9.
 */

public class UserStatusDialogFragment extends BottomSheetDialogFragment {


    private UserStatusAdapter userStatusAdapter;
    private RecyclerView rvMyProfileLanguages;
    private Context mContext;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }


    private void initData() {
        Bundle bundle1 = getArguments();
        ArrayList<UserStatu> userStatus = (ArrayList<UserStatu>) bundle1.get("status");
        if (userStatus != null) {
            userStatusAdapter.addData(userStatus);
        }
    }


    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);

        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_my_profile_language, null);

        rvMyProfileLanguages = (RecyclerView) view.findViewById(R.id.rv_my_profile_languages);
        rvMyProfileLanguages.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        userStatusAdapter = new UserStatusAdapter(mContext);
        rvMyProfileLanguages.setAdapter(userStatusAdapter);
        initData();
        dialog.setContentView(view);
    }


}
