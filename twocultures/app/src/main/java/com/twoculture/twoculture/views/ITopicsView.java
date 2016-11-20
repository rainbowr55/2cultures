package com.twoculture.twoculture.views;

import com.twoculture.twoculture.models.TopicItem;

import java.util.List;

/**
 * Created by songxingchao on 28/10/2016.
 */

public interface ITopicsView {
    void showRefreshing(boolean refreshing);

    void addTopics(List<TopicItem> topics);
}
