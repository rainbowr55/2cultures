package com.twoculture.twoculture.views;

import com.twoculture.twoculture.models.Comment;

import java.util.List;

/**
 * Created by Kevin Song on 13/12/2016.
 * Copyright (c) 2016 Woolworths. All rights reserved.
 */

public interface ITopicCommentsView {
    void refreshData(List<Comment> comments);
    void postResult(boolean bResult,String msg);
}
