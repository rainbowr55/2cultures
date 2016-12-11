package com.twoculture.twoculture.presenter;

import com.twoculture.twoculture.models.UserProfile;

/**
 * Created by liangcaihong on 2016/12/6.
 */

public interface IUserProfilePresenter {

    void getUserProfile();

    void updateUserProfile(String name, int status, UserProfile uploadProfile);


}
