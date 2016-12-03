package com.twoculture.twoculture.ui;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.twoculture.twoculture.R;
import com.twoculture.twoculture.models.EventItem;
import butterknife.BindView;

public class EventDetailActivity extends BaseActivity {
    public static final String EVENT_DATA = "event_data";

    @BindView(R.id.tv_title)
    TextView mTextTitle;
    @BindView(R.id.tv_activity_time)
    private TextView mTextTime;
    @BindView(R.id.tv_registration_deadline)
    private TextView mTextDeadline;
    @BindView(R.id.tv_joined_number)
    private TextView mTextJoinedNumber;
    @BindView(R.id.tv_desc)
    private TextView mTextDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_event_detail;
    }

    private void initData() {
        String eventString = getIntent().getStringExtra(EVENT_DATA);
        Gson gson = new Gson();
        EventItem event = gson.fromJson(eventString, EventItem.class);
        mTextTitle.setText(event.title);
        mTextTime.setText("Event Time: " + event.activity_time);
        mTextDeadline.setText("Deadline: " + event.registration_deadline);
        mTextJoinedNumber.setText("Joined number: " + event.join_num);
        mTextDesc.setText(event.description);
    }
}
