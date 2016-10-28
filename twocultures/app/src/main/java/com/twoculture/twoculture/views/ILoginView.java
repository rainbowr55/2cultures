package com.twoculture.twoculture.views;

/**
 * Created by songxingchao on 27/10/2016.
 */

public interface ILoginView extends IShowMessage{
    void customShowProgress(boolean isShow);
    void onLoginSuccess();
}
