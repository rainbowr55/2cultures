package com.twoculture.twoculture.interfaces;

import com.twoculture.twoculture.models.AtNotificationList;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by rainbow on 17/1/2.
 */

public interface MessageAtService {

    @GET("mobile/messages/get_at_notifications")
    Observable<AtNotificationList> getAtNotifications(
            @Query("token") String toke, @Query("page") int page
    );
}
