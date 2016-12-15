package com.twoculture.twoculture.models;

/**
 * Created by liangcaihong on 2016/12/5.
 */

public class UserProfile {

    public boolean is_init_profile;

    public String name;

    public int gender;

    public int id = -1;

    public boolean is_vip;

    public boolean is_buddy;

    public String email;

    public boolean is_push_open;

    public boolean is_has_couple;

    public String living_city_str;

    public int living_country_id;

    public int living_city_id;

    public String from_city_str;

    public int from_country_id;

    public int from_city_id;

    public String migrate_to_city_str;

    public int migrate_to_country_id;

    public int migrate_to_city_id;

    public String speak;

    public int speak_id;

    public String second_language;//string 当前用户的第二语言,不同语言版本展示不同文字
    public int second_language_id;// int 当前用户的第二语言id,对应README.md中的get_configure_json方法中的language的Id
    public String learning_language;// stirng 学习语言learning_Language=English
    public String learning_Language;//detail 接口容错
    public int learning_language_id;//int 当期学习语言

    public int status_id;

    public String status;

}
