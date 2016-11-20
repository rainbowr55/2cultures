package com.twoculture.twoculture.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.twoculture.twoculture.R;
import com.twoculture.twoculture.models.EventItem;

public class EventDetailActivity extends AppCompatActivity {
    public static final String EVENT_DATA = "event_data";
    private TextView tv_title;
    private TextView tv_activity_time;
    private TextView tv_registration_deadline;
    private TextView tv_joined_number;
    private TextView tv_desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        initView();
        initData();
    }

    private void initView() {
        tv_title = (TextView) this.findViewById(R.id.tv_title);
        tv_activity_time = (TextView) this.findViewById(R.id.tv_activity_time);
        tv_registration_deadline = (TextView) this.findViewById(R.id.tv_registration_deadline);
        tv_joined_number = (TextView) this.findViewById(R.id.tv_joined_number);
        tv_desc = (TextView) this.findViewById(R.id.tv_desc);
    }

    private void initData() {
        String eventString = getIntent().getStringExtra(EVENT_DATA);
        Gson gson = new Gson();
        EventItem event = gson.fromJson(eventString, EventItem.class);
        tv_title.setText(event.title);
        tv_activity_time.setText("Event Time: " + event.activity_time);
        tv_registration_deadline.setText("Deadline: " + event.registration_deadline);
        tv_joined_number.setText("Joined number: " + event.join_num);
        tv_desc.setText(event.description);
    }
}
