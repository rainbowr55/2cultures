package com.twoculture.twoculture.network;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.twoculture.twoculture.interfaces.EventService;
import com.twoculture.twoculture.interfaces.LoginService;
import com.twoculture.twoculture.interfaces.RegisterService;
import com.twoculture.twoculture.interfaces.TopicService;
import com.twoculture.twoculture.models.AllTopics;
import com.twoculture.twoculture.models.EventItem;
import com.twoculture.twoculture.models.LoginResult;
import com.twoculture.twoculture.models.SignupResult;
import com.twoculture.twoculture.tools.Constants;
import com.twoculture.twoculture.tools.MethodConst;

import java.util.List;

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
    private EventService eventService;
    private Retrofit retrofit;

    private RxClient() {
        initRetrofit();
        loginService = retrofit.create(LoginService.class);
        registerService = retrofit.create(RegisterService.class);
        topicService = retrofit.create(TopicService.class);
        eventService = retrofit.create(EventService.class);

    }

    public void initRetrofit() {
        final Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(loggingInterceptor).build();
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
    }

    public <T> T getService(final Class<T> service) {

        if (retrofit == null) {
            initRetrofit();
        }
        return retrofit.create(service);
    }

    public static RxClient getInstance() {
        if (instance == null) {
            instance = new RxClient();
        }
        return instance;
    }

    public Observable<LoginResult> login(String email, String password, String locale, String deviceToken) {
        return loginService.login(email, password, locale, deviceToken);
    }

    public Observable<SignupResult> signup(String email, String password, String locale) {
        return registerService.signup(email, password, locale);
    }

    public Observable<AllTopics> getAllTopics(int pageIndex, int pageNumber) {
        return topicService.getAllTopics(Constants.TOKEN, pageIndex, pageNumber);
    }

    public Observable<List<EventItem>> getAllEvents(int pageIndex, int pageNumber) {
        return eventService.getEvent(Constants.TOKEN, pageIndex, pageNumber);
    }

}
