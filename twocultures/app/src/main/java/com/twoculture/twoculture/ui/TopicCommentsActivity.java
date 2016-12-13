package com.twoculture.twoculture.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.twoculture.twoculture.R;
import com.twoculture.twoculture.adapter.TopicCommentAdapter;

import butterknife.BindView;

public class TopicCommentsActivity extends BaseActivity {

    private TopicCommentAdapter mCommentsAdapter;

    @BindView(R.id.rv_topic_comment)
    RecyclerView mRecyclerComments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCommentsAdapter = new TopicCommentAdapter(this);
        mRecyclerComments.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerComments.setAdapter(mCommentsAdapter);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_topic_comments;
    }
}
