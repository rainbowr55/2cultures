package com.twoculture.twoculture.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.twoculture.twoculture.R;

import butterknife.BindView;

public class PostTopicActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.btn_add_picture)
    ImageView mImageAddPicture;

    @BindView(R.id.rv_pictures)
    RecyclerView mRecyclerViewPictures;

    @BindView(R.id.et_content)
    EditText mEditContent;

    @BindView(R.id.et_text_header)
    EditText mEditHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_post_topic;
    }

    public void initView(){
        mImageAddPicture.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add_picture:
                break;
        }
    }
}
