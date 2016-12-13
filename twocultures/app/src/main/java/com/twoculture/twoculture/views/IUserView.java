package com.twoculture.twoculture.views;

import com.twoculture.twoculture.models.User;

import java.util.List;

/**
 * Created by liangcaihong on 2016/12/13.
 */

public interface IUserView extends IShowMessage {

    void onLoadSuccess(List<User> users);

    void onLoadFailed();
}
