package com.twoculture.twoculture.models;

import java.io.Serializable;

/**
 * Created by liangcaihong on 2016/12/14.
 */

public class SingleProfile implements Serializable {

    public String header_image_url;

    public int age;

    public int have_children;

    public int my_children;

    public int religion_id;

    public String religion_str;

    public int intention_id;

    public String intention_str;

    public LifeStyle life_style;

    public Physical physical;

    public Identity identity;

    public CareerAndEducation career_and_education;


}
