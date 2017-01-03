package com.twoculture.twoculture.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.twoculture.twoculture.R;
import com.twoculture.twoculture.models.EventItem;
import com.twoculture.twoculture.presenter.EventDetailPresenter;
import com.twoculture.twoculture.presenter.IEventDetailPresenter;
import com.twoculture.twoculture.views.IEventDetailView;

import butterknife.BindView;

public class EventDetailActivity extends BaseActivity implements IEventDetailView, View.OnClickListener {
    public static final String EVENT_DATA = "event_data";
    public static final String EVENT_ID = "event_id";
    @BindView(R.id.tv_title)
    TextView mTextTitle;
    @BindView(R.id.tv_activity_time)
    TextView mTextTime;
    @BindView(R.id.tv_registration_deadline)
    TextView mTextDeadline;
    @BindView(R.id.tv_joined_number)
    TextView mTextJoinedNumber;
    @BindView(R.id.tv_desc)
    TextView mTextDesc;


    private int mEventId;

    private IEventDetailPresenter eventDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_event_detail;
    }

    private void initData() {
        String eventString = getIntent().getStringExtra(EVENT_DATA);
        if (!TextUtils.isEmpty(eventString)) {
            Gson gson = new Gson();
            EventItem event = gson.fromJson(eventString, EventItem.class);
            mEventId = event.event_id;
            fillEventDetailData(event);
        } else {
            eventDetailPresenter = new EventDetailPresenter(this);
            mEventId = getIntent().getIntExtra(EVENT_ID, -1);
            if (mEventId > 0) {
                eventDetailPresenter.getEventDetail(mEventId);
            }
        }

    }

    private void fillEventDetailData(EventItem event) {
        mTextTitle.setText(event.title);
        mTextTime.setText("Event Time: " + event.activity_time);
        mTextDeadline.setText("Deadline: " + event.registration_deadline);
        mTextJoinedNumber.setText("Joined number: " + event.join_num);
        mTextDesc.setText(event.description);
    }

    private void initView() {
        mTextJoinedNumber.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_joined_number:
                Intent intent = new Intent(this, JoinedUserActivity.class);
                intent.putExtra(JoinedUserActivity.EVENT_ID, mEventId);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onLoadSuccess(EventItem eventItem) {
        if (eventItem != null) {
            fillEventDetailData(eventItem);
        } else {
            showToast(this.getString(R.string.no_data_hint));
        }
    }


    @Override
    public void onLoadingShow() {
        showLoading();
    }


    @Override
    public void setMessage(String msg) {
        showToast(msg);
        dismissLoadding();
    }

}
