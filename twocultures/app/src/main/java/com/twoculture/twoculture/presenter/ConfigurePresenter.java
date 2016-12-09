package com.twoculture.twoculture.presenter;

import android.util.Log;

import com.twoculture.twoculture.interfaces.ConfigureService;
import com.twoculture.twoculture.models.Configure;
import com.twoculture.twoculture.network.RxClient;
import com.twoculture.twoculture.tools.AppConstants;
import com.twoculture.twoculture.views.IConfigureView;

import java.util.Locale;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by liangcaihong on 2016/12/9.
 */

public class ConfigurePresenter implements IConfigurePresenter {

    private IConfigureView iConfigureView;

    public ConfigurePresenter(IConfigureView iConfigureView) {
        this.iConfigureView = iConfigureView;
    }

    @Override
    public void getConfigure() {
        iConfigureView.onLoadingShow();
        RxClient.getInstance().getService(ConfigureService.class).getConfigure(AppConstants.TOKEN, (Locale.getDefault().getLanguage() + "-" + Locale.getDefault().getCountry().toLowerCase()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Configure>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.e("test", e.getMessage());
                        iConfigureView.setMessage(e.getMessage());
                    }

                    @Override
                    public void onNext(Configure configure) {
                        iConfigureView.onLoadConfigSuccess(configure);

                    }
                });
    }
}
