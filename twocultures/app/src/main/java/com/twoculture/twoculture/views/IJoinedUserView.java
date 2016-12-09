package com.twoculture.twoculture.views;

import com.twoculture.twoculture.models.response.EventUsersListResponse;

import java.util.List;

/**
 * Created by Kevin Song on 7/12/2016.
 * Copyright (c) 2016 Woolworths. All rights reserved.
 */

public interface IJoinedUserView {
    void refreshList(List<EventUsersListResponse.User> userList);
}
