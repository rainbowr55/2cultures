package com.twoculture.twoculture.models;

/**
 * Created by rainbow on 16/12/11.
 */

public class UploadProfile {
    public int gender;//用户的性别 0 为female,1为male
    public int from_country_id;//int 当前用户的从哪来的国家的id,对应README.md中的get_configure_json方法中的country的Id
    public int from_city_id;// int 当前用户的从哪来的城市的id,对应README.md中的get_configure_json方法中的city的Id
    public int mgirate_to_country_id;//int 当前用户意图前往的国家的id,对应README.md中的get_configure_json方法中的country的Id
    public int migrate_to_city_id;//int 当前用户意图前往的城市的id,对应README.md中的get_configure_json方法中的city的Id
    public int living_country_id;//int 当前用户居住的国家的id，对应README.md中的get_configure_json方法中的country的Id
    public int living_city_id;//  int 当前用户居住的城市id,对应README.md中的get_configure_json方法中的city的Id
    public int second_language;//int 当前用户的第二语言id,对应README.md中的get_configure_json方法中的language的Id
    public int speak;//int 当前用户所说的语言的id，对应README.md中的get_configure_json方法中的language的Id
    public int learning_language;// int 学习语言

}
