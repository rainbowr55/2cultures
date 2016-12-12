package com.twoculture.twoculture.interfaces;

import com.twoculture.twoculture.models.EventInvite;
import com.twoculture.twoculture.models.response.BaseResponse;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by rainbow on 16/12/12.
 */

public interface EventInvitationService {

    @GET("mobile/messages/get_event_invites")
    Observable<List<EventInvite>> getEventInvitations(
            @Query("token") String toke
    );

    @GET("mobile/messages/destroy_event_invite")
    Observable<BaseResponse> removeInvitation(@Query("token") String toke, @Query("message_id") int messageId);
}
