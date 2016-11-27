package com.twoculture.twoculture.views;

import com.twoculture.twoculture.models.BaseResponse;
import com.twoculture.twoculture.models.FriendRequest;

import java.util.List;

/**
 * Created by rainbow on 16/11/25.
 */

public interface IFriendRequestView extends IShowMessage{

    void onLoadingShow();
    void onLoadSuccess(List<FriendRequest> friendRequest);
    void onProcessFriendRequest(int messageId,BaseResponse response);

}
