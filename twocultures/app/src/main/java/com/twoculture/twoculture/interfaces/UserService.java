package com.twoculture.twoculture.interfaces;

import com.twoculture.twoculture.models.Friends;
import com.twoculture.twoculture.models.User;
import com.twoculture.twoculture.models.UserDetail;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by liangcaihong on 2016/12/13.
 */

public interface UserService {

    @POST("mobile/search/search_by_username_or_email")
    Observable<List<User>> getAllUser(@Query("token") String token,
                                      @Query("search") String search);

    @GET("mobile/my/friends")
    Observable<Friends> getFriends(@Query("token") String token);


    @GET("mobile/browse/browse_user_profile")
    Observable<UserDetail> getUserDetail(@Query("token") String token, @Query("user_id") String userId);
}
