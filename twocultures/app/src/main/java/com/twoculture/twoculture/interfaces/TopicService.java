package com.twoculture.twoculture.interfaces;

import com.twoculture.twoculture.models.AllTopics;
import com.twoculture.twoculture.models.BaseResponse;
import com.twoculture.twoculture.models.EventUsersListResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


/**
 * Created by songxingchao on 27/09/2016.
 */

public interface TopicService {
    @GET("mobile/v2/topics")
    Observable<AllTopics> getAllTopics(
            @Query("token") String token,
            @Query("page") int pageIndex,
            @Query("page_num") int pageNumber
    );

    @GET("mobile/we/events/favorite_the_event")
    Observable<BaseResponse> favouriteTopic(
            @Query("event_id") int event_id
    );

    @GET("mobile/we/events/unfavorite_the_event")
    Observable<BaseResponse> unfavouriteTopic(
            @Query("event_id") int event_id
    );

    @GET("mobile/we/events/event_join")
    Observable<EventUsersListResponse> getJoinedUser(
            @Query("token") String token,
            @Query("event_id") int event_id,
            @Query("page") int pageIndex,
            @Query("page_num") int pageNumber
    );

}
