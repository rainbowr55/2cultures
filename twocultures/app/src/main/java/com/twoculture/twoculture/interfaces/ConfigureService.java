package com.twoculture.twoculture.interfaces;

import com.twoculture.twoculture.models.Configure;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by rainbow on 2016/12/9.
 */

public interface ConfigureService {

    /**
     * @param token
     * @param locale eg zh-cn
     * @return
     */
    @GET("mobile/get_configure_json")
    Observable<Configure> getConfigure(@Query("token") String token, @Query("locale") String locale);
}
