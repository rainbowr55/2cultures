package com.twoculture.twoculture.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.twoculture.twoculture.R;
import com.twoculture.twoculture.models.TopicItem;

public class TopicDetailActivity extends AppCompatActivity {
    public static final String TOPIC_ITEM = "topicitem";
    private TextView tv_details_user_name;
    private TextView  tv_details_create_at;
    private ImageView iv_details_user_gravatar;
    private TextView  tv_details_title;
    private TopicItem mTopicItem;
    private TextView tv_details_translate_number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_detail);

        initView();
        initData();
    }


    private void initView(){
        tv_details_user_name = (TextView) this.findViewById(R.id.tv_details_user_name);
        tv_details_create_at = (TextView) this.findViewById(R.id.tv_details_create_at);
        iv_details_user_gravatar = (ImageView) this.findViewById(R.id.iv_details_user_gravatar);
        tv_details_title = (TextView) this.findViewById(R.id.tv_details_title);
        tv_details_translate_number = (TextView) this.findViewById(R.id.tv_details_translate_number);
    }

    private void initData(){
        String topicString = getIntent().getStringExtra(TOPIC_ITEM);
        Gson gson = new Gson();
        mTopicItem = gson.fromJson(topicString,TopicItem.class);
        tv_details_user_name.setText(mTopicItem.author.name);
        tv_details_create_at.setText(mTopicItem.topic.create_at);
        Picasso.with(this).load(mTopicItem.author.user_header_image).placeholder(R.drawable.default_gravatar).into(iv_details_user_gravatar);
        tv_details_title.setText(mTopicItem.topic.topic_title);
        tv_details_translate_number.setText(mTopicItem.topic.translate_count+"");
    }
}
