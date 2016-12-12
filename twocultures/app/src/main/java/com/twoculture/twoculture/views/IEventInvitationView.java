package com.twoculture.twoculture.views;

import com.twoculture.twoculture.models.EventInvite;

import java.util.List;

/**
 * Created by rainbow on 16/12/12.
 */

public interface IEventInvitationView extends IShowMessage {
    void onLoadSuccess(List<EventInvite> eventInvites);
    void onDelEventInvitation(EventInvite eventInvite);
}
