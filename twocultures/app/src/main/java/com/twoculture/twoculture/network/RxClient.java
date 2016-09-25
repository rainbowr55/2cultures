package com.twoculture.twoculture.network;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.twoculture.twoculture.interfaces.LoginService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by songxingchao on 25/09/2016.
 */
public class RxClient {
    private static final String BASE_URL = "";
    private static RxClient instance;
    private LoginService loginService;
    private RxClient() {
        final Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        loginService = retrofit.create(LoginService.class);

    }

    public static RxClient getInstance() {
        if (instance == null) {
            instance = new RxClient();

        }
        return instance;
    }

    public Observable<Boolean> login(String userName, String password){
      return loginService.login(userName,password);
    }

}
