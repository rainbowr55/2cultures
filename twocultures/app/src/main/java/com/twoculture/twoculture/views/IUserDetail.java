package com.twoculture.twoculture.views;

import com.twoculture.twoculture.models.UserDetail;

/**
 * Created by liangcaihong on 2016/12/14.
 */

public interface IUserDetail extends IShowMessage{

    void onLoadSuccess(UserDetail userDetail);

    void onLoadFailed();
}
