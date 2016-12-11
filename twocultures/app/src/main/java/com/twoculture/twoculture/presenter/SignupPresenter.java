package com.twoculture.twoculture.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.twoculture.twoculture.models.SignupResult;
import com.twoculture.twoculture.network.RxClient;
import com.twoculture.twoculture.tools.AppConstants;
import com.twoculture.twoculture.tools.StringUtils;
import com.twoculture.twoculture.ui.EmailLoginActivity;
import com.twoculture.twoculture.ui.GlobalApplication;
import com.twoculture.twoculture.ui.SignupActivity;
import com.twoculture.twoculture.views.ISignupView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by songxingchao on 27/10/2016.
 */

public class SignupPresenter implements ISignupPresenter {

    private ISignupView mSignupView;

    public SignupPresenter(ISignupView signupView) {
        mSignupView = signupView;
    }

    @Override
    public void signup(String email, String password, String locale) {

        if (!StringUtils.isEmailValid(email)) {
            mSignupView.setMessage("email address is not valid.");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            mSignupView.setMessage("password conn't be null.");
            return;
        }

        RxClient.getInstance().signup(email, password, locale)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SignupResult>() {
                    public void onCompleted() {
                        Log.d(SignupActivity.class.getName(), "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(SignupActivity.class.getName(), e.getMessage());
                        mSignupView.setMessage(e.getMessage());
                    }

                    @Override
                    public void onNext(SignupResult result) {
                        Log.d(EmailLoginActivity.class.getName(), result.msg + result.status + result.token);
                        if(result.status!=200){
                            mSignupView.onSingupFailed(result.msg);
                            return;
                        }
                        mSignupView.setMessage(result.msg);
                        AppConstants.TOKEN = result.token;
                        mSignupView.onSignupSuccess();
                        SharedPreferences sharedPreferences = GlobalApplication.applicationContext.getSharedPreferences(AppConstants.TOKEN_FILE_NAME, Context.MODE_PRIVATE);
                        SharedPreferences.Editor  editor = sharedPreferences.edit();
                        editor.putString(AppConstants.TOKEN_FIELD_NAME, result.token);
                        editor.commit();
                    }
                });
    }
}
