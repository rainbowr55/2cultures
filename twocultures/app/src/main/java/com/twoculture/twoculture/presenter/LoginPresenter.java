package com.twoculture.twoculture.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.twoculture.easemob.TwoCApplication;
import com.twoculture.twoculture.models.LoginResult;
import com.twoculture.twoculture.network.RxClient;
import com.twoculture.twoculture.tools.AppConstants;
import com.twoculture.twoculture.ui.LoginActivity;
import com.twoculture.twoculture.views.ILoginView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by songxingchao on 27/10/2016.
 */

public class LoginPresenter implements ILoginPresenter {

    private ILoginView mLoginView;

    public LoginPresenter(ILoginView loginView) {
        mLoginView = loginView;
    }

    @Override
    public void loginToServer(String userName, String password, String locale, String deviceToken) {
        RxClient.getInstance().
                login(userName, password, locale, deviceToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginResult>() {
                    @Override
                    public void onCompleted() {
                        Log.d(LoginActivity.class.getName(), "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {

                        mLoginView.customShowProgress(false);
                        mLoginView.setMessage(e.getMessage());

                    }

                    @Override
                    public void onNext(LoginResult result) {
                        mLoginView.customShowProgress(false);
                        AppConstants.TOKEN = result.token;
                        mLoginView.setMessage(result.msg);
                        mLoginView.onLoginSuccess();
                        SharedPreferences sharedPreferences = TwoCApplication.applicationContext.getSharedPreferences(AppConstants.TOKEN_FILE_NAME, Context.MODE_PRIVATE);
                        SharedPreferences.Editor  editor = sharedPreferences.edit();
                        editor.putString(AppConstants.TOKEN_FIELD_NAME, result.token);
                        editor.commit();
                    }
                });
    }
}
