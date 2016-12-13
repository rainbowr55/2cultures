package com.twoculture.twoculture.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.twoculture.twoculture.R;
import com.twoculture.twoculture.adapter.PicturesViewpagerAdapter;
import com.twoculture.twoculture.models.TopicItem;

import butterknife.BindView;

public class TopicDetailActivity extends BaseActivity implements View.OnClickListener {
    public static final String TOPIC_ITEM = "topicitem";

    @BindView(R.id.tv_details_user_name)
    TextView mTextUserName;

    @BindView(R.id.tv_details_create_at)
    TextView mTextCreateDate;

    @BindView(R.id.iv_details_user_gravatar)
    ImageView mImageGravatar;

    @BindView(R.id.tv_details_title)
    TextView mTextTitle;


    @BindView(R.id.tv_details_translate_number)
    TextView mTextNumber;

    @BindView(R.id.tv_details_content)
    TextView mTextContent;

    @BindView(R.id.vp_pictures)
    ViewPager mViewPagerPictures;

    @BindView(R.id.ll_comment)
    LinearLayout ll_comment;

    @BindView(R.id.tv_like)
    TextView tv_like;

    @BindView(R.id.iv_share)
    ImageView iv_share;

    @BindView(R.id.iv_favourite)
    ImageView iv_favourite;

    TopicItem mTopicItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_topic_detail;
    }

    public void initView(){
        iv_favourite.setOnClickListener(this);
        iv_share.setOnClickListener(this);
        tv_like.setOnClickListener(this);
        ll_comment.setOnClickListener(this);
    }

    private void initData() {
        String topicString = getIntent().getStringExtra(TOPIC_ITEM);
        Gson gson = new Gson();
        mTopicItem = gson.fromJson(topicString, TopicItem.class);
        mTextUserName.setText(mTopicItem.author.name);
        mTextCreateDate.setText(mTopicItem.topic.create_at);
        Picasso.with(this).load(mTopicItem.author.user_header_image).placeholder(R.drawable.default_gravatar).into(mImageGravatar);
        mTextTitle.setText(mTopicItem.topic.topic_title);
        mTextNumber.setText(mTopicItem.topic.translate_count + "");
        mTextContent.setText(mTopicItem.topic.text);
        PicturesViewpagerAdapter adapter = new PicturesViewpagerAdapter(this, mTopicItem.topic_photos);
        mViewPagerPictures.setAdapter(adapter);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_favourite:

                break;
            case R.id.iv_share:
                break;
            case R.id.ll_comment:
                Intent intent = new Intent(this,TopicCommentsActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_like:
                break;
        }
    }
}
