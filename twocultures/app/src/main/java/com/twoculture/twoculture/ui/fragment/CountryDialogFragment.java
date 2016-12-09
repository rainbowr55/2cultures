package com.twoculture.twoculture.ui.fragment;

import android.app.Dialog;
import android.content.Context;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.twoculture.base.widget.ToastUtil;
import com.twoculture.twoculture.R;
import com.twoculture.twoculture.adapter.CountryAdapter;
import com.twoculture.twoculture.models.Configure;
import com.twoculture.twoculture.presenter.ConfigurePresenter;
import com.twoculture.twoculture.tools.CLog;
import com.twoculture.twoculture.tools.ViewHelper;
import com.twoculture.twoculture.ui.interfaces.CountryClickListener;
import com.twoculture.twoculture.views.IConfigureView;

/**
 * Created by liangcaihong on 2016/12/9.
 */

public class CountryDialogFragment extends BottomSheetDialogFragment implements IConfigureView {

    private ConfigurePresenter configurePresenter;
    private CountryAdapter countryAdapter;
    RecyclerView recyclerView;
    protected Dialog loadingDialog;
    private Context mContext;


    private CountryClickListener countryClickListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }


    private void initData() {
        configurePresenter = new ConfigurePresenter(this);
        configurePresenter.getConfigure();
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);

        View view = getActivity().getLayoutInflater().inflate(R.layout.my_profile_countries, null);

        recyclerView = (RecyclerView) view.findViewById(R.id.my_profile_countries_list);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        countryAdapter = new CountryAdapter();
        recyclerView.setAdapter(countryAdapter);
        initData();
        dialog.setContentView(view);
    }

    @Override
    public void onLoadConfigSuccess(Configure configure) {
        dismissDialog();
        CLog.d("test", "configure = " + configure);
        if (configure != null) {
            CLog.d("test", "configure size= " + configure.countries.size());
            countryAdapter.addData(configure.countries);
        }
    }

    @Override
    public void onLoadingShow() {
        if (loadingDialog == null) {
            loadingDialog = ViewHelper.show(mContext);
        }
    }

    @Override
    public void setMessage(String msg) {
        dismissDialog();
        ToastUtil.showMiddleToast(mContext, msg);
    }

    private void dismissDialog() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }

    public void setCountryClickListener(CountryClickListener countryClickListener) {
        this.countryClickListener = countryClickListener;
        if (countryAdapter != null) {
            countryAdapter.setCountryClickListener(countryClickListener);
        }
    }
}
