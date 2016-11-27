package com.twoculture.twoculture.presenter;

/**
 * Created by rainbow on 16/11/25.
 */

public interface IFriendRequestPresenter {

    void getFriendRequestList();

    int processFriendRequest(int messageId, int processCode);


}
