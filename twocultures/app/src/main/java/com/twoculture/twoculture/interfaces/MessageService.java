package com.twoculture.twoculture.interfaces;

import com.twoculture.twoculture.models.FriendRequest;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by rainbow on 16/11/25.
 */

public interface MessageService {

    @GET("mobile/messages/show_friends_request")
    Observable<List<FriendRequest>> getMessageList();
}
