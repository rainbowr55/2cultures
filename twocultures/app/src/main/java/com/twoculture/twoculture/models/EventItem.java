package com.twoculture.twoculture.models;

import java.util.List;

/**
 * Created by songxingchao on 1/10/2016.
 */

public class EventItem {
    public int event_id;
    public String title;
    public String description;
    public String event_bg;
    public int price;
    public int authority;
    public boolean is_finish;
    public String activity_time;
    public String registration_deadline;
    public int comment_num;
    public int favorite_num;
    public int join_num;
    public int approve_num;
    public int join_gsg_num;
    public boolean is_favorite;
    public boolean is_forward;
    public int is_forward_count;
    public boolean is_joined_event;
    public boolean is_liked;
    public boolean is_commentd;
    public List<TopicPhoto> event_photos;
}


