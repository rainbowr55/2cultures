package com.twoculture.twoculture.ui.interfaces;

import com.twoculture.twoculture.models.EventInvite;

/**
 * Created by rainbow on 16/12/12.
 */

public interface ViewMoreClickCallback {
    void onViewMoreClick(EventInvite eventInvite);

    void onDeleteEventInvite(EventInvite eventInvite);

}
