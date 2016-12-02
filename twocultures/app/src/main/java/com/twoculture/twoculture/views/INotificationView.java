package com.twoculture.twoculture.views;

import com.twoculture.twoculture.models.SystemMessage;

import java.util.List;

/**
 * Created by liangcaihong on 2016/12/2.
 */
public interface INotificationView extends IShowMessage {
    void onLoadSuccess(List<SystemMessage> systemMessages);
}
