package com.twoculture.twoculture.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twoculture.twoculture.R;

/**
 * Created by songxingchao on 1/10/2016.
 */

public class MessagesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_topics, container, false);
        initView(rootView);
        getDataFromServer();
        return rootView;

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    private void initView(View rootView) {

    }

    private void getDataFromServer() {

    }

    @Override
    public void onRefresh() {

    }


}
