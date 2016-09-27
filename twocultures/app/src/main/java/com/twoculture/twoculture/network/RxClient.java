package com.twoculture.twoculture.network;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.twoculture.twoculture.interfaces.LoginService;
import com.twoculture.twoculture.interfaces.RegisterService;
import com.twoculture.twoculture.interfaces.TopicService;
import com.twoculture.twoculture.models.AllTopics;
import com.twoculture.twoculture.models.LoginResult;
import com.twoculture.twoculture.models.SignupResult;
import com.twoculture.twoculture.tools.Constants;
import com.twoculture.twoculture.tools.MethodConst;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by songxingchao on 25/09/2016.
 */
public class RxClient {
    private static final String BASE_URL = MethodConst.URL;
    private static RxClient instance;
    private LoginService loginService;
    private RegisterService registerService;
    private TopicService topicService;
    private RxClient() {
        final Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(loggingInterceptor).build();

        final Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        loginService = retrofit.create(LoginService.class);
        registerService = retrofit.create(RegisterService.class);
        topicService = retrofit.create(TopicService.class);

    }

    public static RxClient getInstance() {
        if (instance == null) {
            instance = new RxClient();
        }
        return instance;
    }

    public Observable<LoginResult> login(String email, String password, String locale, String deviceToken){
      return loginService.login(email,password,locale,deviceToken);
    }

    public Observable<SignupResult> signup(String email,String password,String locale){
        return registerService.signup(email,password,locale);
    }

    public Observable<AllTopics> getAllTopics(){
        return topicService.getAllTopics(Constants.TOKEN);
    }

}
