package com.twoculture.twoculture.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.twoculture.twoculture.R;
import com.twoculture.twoculture.adapter.PicturesViewpagerAdapter;
import com.twoculture.twoculture.models.TopicItem;

import butterknife.BindView;

public class TopicDetailActivity extends BaseActivity {
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

    TopicItem mTopicItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_topic_detail;
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
}
