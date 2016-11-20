package com.twoculture.twoculture.interfaces;

import com.twoculture.twoculture.models.SignupResult;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by songxingchao on 26/09/2016.
 */
public interface RegisterService {
    @GET("mobile/register")
    Observable<SignupResult> signup(
            @Query("email") String email,
            @Query("password") String password,
            @Query("locale") String locale
    );
}
