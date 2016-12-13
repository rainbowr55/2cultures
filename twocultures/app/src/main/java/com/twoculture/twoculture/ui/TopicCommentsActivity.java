package com.twoculture.twoculture.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.twoculture.twoculture.R;
import com.twoculture.twoculture.adapter.TopicCommentAdapter;
import com.twoculture.twoculture.models.Comment;
import com.twoculture.twoculture.presenter.TopicCommentsPresenter;
import com.twoculture.twoculture.views.ITopicCommentsView;

import java.util.List;

import butterknife.BindView;

public class TopicCommentsActivity extends BaseActivity implements ITopicCommentsView {

    public static final String TOPICID = "topicid";

    private TopicCommentAdapter mCommentsAdapter;
    private int mTopicId;
    private TopicCommentsPresenter mCommentsPresenter;

    private static final int PAGE_SIZE = 20;

    @BindView(R.id.rv_topic_comment)
    RecyclerView mRecyclerComments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTopicId = getIntent().getIntExtra(TOPICID,0);
        mCommentsAdapter = new TopicCommentAdapter(this);
        mRecyclerComments.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerComments.setAdapter(mCommentsAdapter);
        mCommentsPresenter = new TopicCommentsPresenter(this);
        mCommentsPresenter.loadComments(mTopicId,1,PAGE_SIZE);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_topic_comments;
    }

    @Override
    public void refreshData(List<Comment> comments) {
        mCommentsAdapter.setData(comments);
        mCommentsAdapter.notifyDataSetChanged();
    }
}
