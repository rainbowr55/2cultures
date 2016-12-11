package com.twoculture.twoculture.interfaces;

import com.twoculture.twoculture.models.LoginResult;
import com.twoculture.twoculture.models.UserProfile;
import com.twoculture.twoculture.models.response.BaseResponse;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by songxingchao on 25/09/2016.
 */
public interface LoginService {
    @GET("mobile/login")
    Observable<LoginResult> login(
            @Query("email") String email,
            @Query("password") String password,
            @Query("locale") String locale,
            @Query("device_token") String token
    );

    @GET("mobile/my/my_profile")
    Observable<UserProfile> getUserProfile(@Query("token") String token);

    @POST("mobile/my/my_profile/update_my_profile")
    Observable<BaseResponse> updateUserProfile(@Query("token") String token, @Query("user_name") String userName, @Query("status") int status, @Query("my_profile") String uploadProfile);

}
