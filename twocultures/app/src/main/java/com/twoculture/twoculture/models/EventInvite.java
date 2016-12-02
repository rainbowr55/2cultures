package com.twoculture.twoculture.models;

/**
 * Created by liangcaihong on 2016/12/2.
 */

public class EventInvite {
    public Event event;
    public int authority;//  活动权限是否只允许gsg会员，与event_helper.md中的authority对应
    public Message message;
}
