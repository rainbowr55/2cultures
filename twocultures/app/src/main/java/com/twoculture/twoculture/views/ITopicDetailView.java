package com.twoculture.twoculture.views;

import com.twoculture.twoculture.models.TopicItem;

/**
 * Created by Kevin Song on 14/12/2016.
 * Copyright (c) 2016 Woolworths. All rights reserved.
 */

public interface ITopicDetailView {
    void setLikeResult(boolean bResult, String msg);

    void setUnikeResult(boolean bResult, String msg);

    void setFavouriteResult(boolean bResult, String msg);

    void setUnfavouriteResult(boolean bResult, String msg);

    void setTopicDetailResult(TopicItem topicItem);
}
