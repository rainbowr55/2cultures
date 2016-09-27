package com.twoculture.twoculture.models;

/**
 * Created by songxingchao on 27/09/2016.
 */

public class TopicContent {
    public int topic_id;
    public int translate_count;
    public int comment_num;
    public int like_num;
    public int forward_count;
    public int language_id;
    public int topic_category_id;


    public boolean is_admin;
    public boolean is_translate;
    public boolean is_favorite;
    public boolean is_like;
    public boolean is_self;
    public boolean is_forward;
    public boolean is_gsg;

    public String topic_title;
    public String create_at;
    public String category_name;
    public String text;

}

