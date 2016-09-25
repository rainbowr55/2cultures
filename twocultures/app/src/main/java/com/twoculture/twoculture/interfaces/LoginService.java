package com.twoculture.twoculture.interfaces;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by songxingchao on 25/09/2016.
 */
public interface LoginService {
    @GET("login?username={username}&password={password}")
    Observable<Boolean> login(
            @Path("username") String usernmame,
            @Path("password") String password
    );
}
