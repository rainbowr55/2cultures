package com.twoculture.twoculture.views;

import com.twoculture.twoculture.models.EventItem;

/**
 * Created by rainbow on 17/1/3.
 */

public interface IEventDetailView extends IShowMessage {
    void onLoadSuccess(EventItem eventItem);
}
