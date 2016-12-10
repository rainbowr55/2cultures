package com.twoculture.twoculture.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by liangcaihong on 2016/12/9.
 */

public class Country implements Serializable{
    public String name;

    public int id;

    public ArrayList<City> cities;
}
