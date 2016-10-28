package com.twoculture.twoculture.presenter;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.twoculture.twoculture.models.LoginResult;
import com.twoculture.twoculture.network.RxClient;
import com.twoculture.twoculture.tools.Constants;
import com.twoculture.twoculture.ui.LoginActivity;
import com.twoculture.twoculture.ui.MainActivity;
import com.twoculture.twoculture.views.ILoginView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by songxingchao on 27/10/2016.
 */

public class LoginPresenter implements ILoginPresenter {

    private ILoginView mLoginView;

    public LoginPresenter(ILoginView loginView){
        mLoginView = loginView;
    }
    @Override
    public void loginToServer(String userName, String password, String locale, String deviceToken) {
        RxClient.getInstance().
                login(userName,password,locale,deviceToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginResult>() {
                    @Override
                    public void onCompleted() {
                        Log.d(LoginActivity.class.getName(),"onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {

                        mLoginView.customShowProgress(false);
                        mLoginView.setMessage(e.getMessage());

                    }

                    @Override
                    public void onNext(LoginResult result) {
                        mLoginView.customShowProgress(false);
                        Constants.TOKEN = result.token;
                        mLoginView.setMessage(result.msg);
                        mLoginView.onLoginSuccess();

                    }
                });
    }
}
