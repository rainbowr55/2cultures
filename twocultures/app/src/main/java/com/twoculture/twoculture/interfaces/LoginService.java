package com.twoculture.twoculture.interfaces;

import com.twoculture.twoculture.models.LoginResult;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
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
}
