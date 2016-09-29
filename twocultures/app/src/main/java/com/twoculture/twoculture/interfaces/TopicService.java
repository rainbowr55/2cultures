package com.twoculture.twoculture.interfaces;

import com.twoculture.twoculture.models.AllTopics;

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
}
