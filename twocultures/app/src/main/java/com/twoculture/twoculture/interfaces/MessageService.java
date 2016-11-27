package com.twoculture.twoculture.interfaces;

import com.twoculture.twoculture.models.BaseResponse;
import com.twoculture.twoculture.models.FriendRequest;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by rainbow on 16/11/25.
 */

public interface MessageService {

    @GET("mobile/messages/show_friends_request")
    Observable<List<FriendRequest>> getMessageList(@Query("token") String token);

    @GET("mobile/messages/process_friend_request")
    Observable<BaseResponse> processFriendRequest(@Query("token") String token,@Query("message_id") int message_id,@Query("process_result_code") int process_result_code);
}
