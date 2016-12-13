package com.twoculture.twoculture.interfaces;

import com.twoculture.twoculture.models.AllTopics;
import com.twoculture.twoculture.models.Comment;
import com.twoculture.twoculture.models.response.BaseResponse;
import com.twoculture.twoculture.models.response.EventUsersListResponse;
import com.twoculture.twoculture.models.response.PostTopicResponse;
import com.twoculture.twoculture.models.response.UploadImageResponse;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;


/**
 * Created by songxingchao on 27/09/2016.
 */

public interface TopicService {
    @GET("mobile/v2/topics")
    Observable<AllTopics> getAllTopics(
            @Query("token") String token,
            @Query("page") int pageIndex,
            @Query("page_num") int pageNumber
    );

    @GET("mobile/we/events/favorite_the_event")
    Observable<BaseResponse> favouriteTopic(
            @Query("event_id") int event_id
    );

    @GET("mobile/we/events/unfavorite_the_event")
    Observable<BaseResponse> unfavouriteTopic(
            @Query("event_id") int event_id
    );

    @GET("mobile/we/events/event_join")
    Observable<EventUsersListResponse> getJoinedUser(
            @Query("token") String token,
            @Query("event_id") int event_id,
            @Query("page") int pageIndex,
            @Query("page_num") int pageNumber
    );

    @Multipart
    @POST("mobile/we/topics/create_topic_photo")
    Observable<UploadImageResponse> uploadAttachment(@Query("token") String token, @Part MultipartBody.Part filePart);

    @POST("mobile/we/topics/topics")
    Observable<PostTopicResponse> postTopic(@Query("token") String token, @Query("title") String title, @Query("text") String text, @Query("topic_category_id") int categoryId, @Query("is_gsg")boolean isGsg);

    @POST("mobile/we/topics/topic_comments")
    Observable<List<Comment>> getComments(@Query("token") String token, @Query("topic_id") int topicId, @Query("page") int pageIndex, @Query("per_num") int pageNumber);
}
