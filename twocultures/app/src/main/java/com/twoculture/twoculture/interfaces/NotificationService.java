package com.twoculture.twoculture.interfaces;

import com.twoculture.twoculture.models.SystemMessage;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by liangcaihong on 2016/12/2.
 */

public interface NotificationService {
    @GET("mobile/messages/get_system_messages")
    Observable<List<SystemMessage>> getNotificationList(@Query("token") String token);
}
