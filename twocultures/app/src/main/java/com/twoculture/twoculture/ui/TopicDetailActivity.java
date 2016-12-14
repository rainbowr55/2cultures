package com.twoculture.twoculture.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.twoculture.twoculture.R;
import com.twoculture.twoculture.adapter.PicturesViewpagerAdapter;
import com.twoculture.twoculture.models.TopicItem;
import com.twoculture.twoculture.presenter.TopicDetailPresenter;
import com.twoculture.twoculture.views.ITopicDetailView;

import butterknife.BindView;

public class TopicDetailActivity extends BaseActivity implements View.OnClickListener, ITopicDetailView {
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
    private int mTopicId;

    @BindView(R.id.ll_comment)
    LinearLayout ll_comment;

    @BindView(R.id.tv_comment)
    TextView tv_comment;

    @BindView(R.id.tv_like)
    TextView tv_like;

    @BindView(R.id.iv_share)
    ImageView iv_share;

    @BindView(R.id.iv_favourite)
    ImageView iv_favourite;

    @BindView(R.id.ll_like)
    LinearLayout ll_like;

    @BindView(R.id.iv_like)
    ImageView iv_like;

    private TopicDetailPresenter mTopicDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTopicDetailPresenter = new TopicDetailPresenter(this);
        initView();
        initData();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_topic_detail;
    }

    public void initView() {
        iv_favourite.setOnClickListener(this);
        iv_share.setOnClickListener(this);
        ll_like.setOnClickListener(this);
        ll_comment.setOnClickListener(this);
    }

    private void initData() {
        String topicString = getIntent().getStringExtra(TOPIC_ITEM);
        Gson gson = new Gson();
        mTopicItem = gson.fromJson(topicString, TopicItem.class);
        mTopicId = mTopicItem.topic.topic_id;
        mTextUserName.setText(mTopicItem.author.name);
        mTextCreateDate.setText(mTopicItem.topic.create_at);
        Picasso.with(this).load(mTopicItem.author.user_header_image).placeholder(R.drawable.default_gravatar).into(mImageGravatar);
        mTextTitle.setText(mTopicItem.topic.topic_title);
        mTextNumber.setText(mTopicItem.topic.translate_count + "");
        mTextContent.setText(mTopicItem.topic.text);
        tv_comment.setText(mTopicItem.topic.comment_num + "");
        tv_like.setText(mTopicItem.topic.like_num + "");
        PicturesViewpagerAdapter adapter = new PicturesViewpagerAdapter(this, mTopicItem.topic_photos);
        mViewPagerPictures.setAdapter(adapter);
        if(mTopicItem.topic.is_favorite){
            iv_favourite.setSelected(true);
        }
        if(mTopicItem.topic.is_like){
            iv_like.setSelected(true);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_favourite:
                if(mTopicItem.topic.is_favorite){
                    mTopicDetailPresenter.favouriteTopic(mTopicId);
                }else{
                    mTopicDetailPresenter.unfavouriteTopic(mTopicId);
                }
                break;
            case R.id.iv_share:
                break;
            case R.id.ll_comment:
                Intent intent = new Intent(this, TopicCommentsActivity.class);
                intent.putExtra(TopicCommentsActivity.TOPICID, mTopicId);
                startActivity(intent);
                break;
            case R.id.ll_like:
                if (mTopicItem.topic.is_like) {
                    mTopicDetailPresenter.unlikeTopic(mTopicId);
                } else {

                    mTopicDetailPresenter.likeTopic(mTopicId);
                }

                break;
        }
    }

    @Override
    public void setLikeResult(boolean bResult, String msg) {
        if (bResult) {
            mTopicItem.topic.like_num = mTopicItem.topic.like_num + 1;
            tv_like.setText(mTopicItem.topic.like_num + "");
           // Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            mTopicItem.topic.is_like = true;
            iv_like.setSelected(true);
        } else {
           // Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setUnikeResult(boolean bResult, String msg) {
        if (bResult) {
            mTopicItem.topic.like_num = mTopicItem.topic.like_num - 1;
            tv_like.setText(mTopicItem.topic.like_num + "");
            mTopicItem.topic.is_like = false;
            iv_like.setSelected(false);
           // Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        } else {
           // Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setFavouriteResult(boolean bResult, String msg) {
        if(bResult){
            iv_favourite.setSelected(true);
            mTopicItem.topic.is_favorite = true;
        }
    }

    @Override
    public void setUnfavouriteResult(boolean bResult, String msg) {
        if(bResult){
            iv_favourite.setSelected(false);
            mTopicItem.topic.is_favorite = false;
        }
    }
}
