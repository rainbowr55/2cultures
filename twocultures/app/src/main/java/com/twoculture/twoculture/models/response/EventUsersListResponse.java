package com.twoculture.twoculture.models.response;

import com.twoculture.twoculture.models.response.BaseResponse;

import java.util.List;

/**
 * Created by Kevin Song on 6/12/2016.
 * Copyright (c) 2016 Woolworths. All rights reserved.
 */

public class EventUsersListResponse extends BaseResponse {
    public int joined_users_num;
    public List<User> joined_users;

    public static class User{
        public int user_id;
        public String user_name;
        public String header_image;
    }
}
