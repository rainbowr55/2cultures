package com.twoculture.twoculture.models;

/**
 * Created by Kevin Song on 12/12/2016.
 * Copyright (c) 2016 Woolworths. All rights reserved.
 * test
 */

public class Comment {
    public int comment_id;
    public String created_at;
    public boolean is_self;
    public String text;
    public User user;
    public static class User{
        public int user_id;
        public String user_name;
        public String header_image;
    }
}
