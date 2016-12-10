package com.twoculture.twoculture.views;

/**
 * Created by songxingchao on 27/10/2016.
 */

public interface ISignupView extends IShowMessage {

    void onSignupSuccess();
    void onSingupFailed(String message);
}
