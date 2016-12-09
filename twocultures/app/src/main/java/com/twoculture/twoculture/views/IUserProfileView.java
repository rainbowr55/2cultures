package com.twoculture.twoculture.views;

import com.twoculture.twoculture.models.UserProfile;

/**
 * Created by liangcaihong on 2016/12/6.
 */

public interface IUserProfileView extends IShowMessage {


    void onLoadSuccess(UserProfile userProfile);

    void onLoadFailed();
}
