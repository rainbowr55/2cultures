package com.twoculture.twoculture.interfaces;

import com.twoculture.twoculture.models.EventItem;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by songxingchao on 1/10/2016.
 */

public interface EventService {
    @GET("/mobile/we/events/events")
    Observable<List<EventItem>> getEvent(
            @Query("token") String toke,
            @Query("page") int page,
            @Query("per_num") int pageNumber
    );
}
