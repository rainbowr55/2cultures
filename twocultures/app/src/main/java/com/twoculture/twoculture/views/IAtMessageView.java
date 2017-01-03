package com.twoculture.twoculture.views;

import com.twoculture.twoculture.models.AtNotificationList;

/**
 * Created by rainbow on 17/1/2.
 */

public interface IAtMessageView extends IShowMessage {

    void onLoadSuccess(AtNotificationList notificationList);
}
