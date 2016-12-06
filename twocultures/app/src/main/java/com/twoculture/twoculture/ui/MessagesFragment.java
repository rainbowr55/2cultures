package com.twoculture.twoculture.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.easeui.model.EaseAtMessageHelper;
import com.hyphenate.util.NetUtils;
import com.twoculture.easemob.Constant;
import com.twoculture.easemob.db.InviteMessgeDao;
import com.twoculture.easemob.ui.ChatActivity;
import com.twoculture.twoculture.R;

/**
 * Created by songxingchao on 1/10/2016.
 */

public class MessagesFragment extends MessagesBaseFragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    private TextView errorText;

    private TextView tvMsgFriendRequest;
    private TextView tvMsgNotification;
    private TextView tvMsgEvents;
    private TextView tvMsgAt;

    private Context mContext;

    @Override
    protected void initView() {
        super.initView();
        View errorView = View.inflate(getActivity(), com.twoculture.easemob.R.layout.em_chat_neterror_item, null);
        errorItemContainer.addView(errorView);
        errorText = (TextView) errorView.findViewById(com.twoculture.easemob.R.id.tv_connect_errormsg);

        tvMsgFriendRequest = (TextView) getView().findViewById(R.id.tv_msg_friend_request);
        tvMsgNotification = (TextView) getView().findViewById(R.id.tv_msg_notification);
        tvMsgEvents = (TextView) getView().findViewById(R.id.tv_msg_events);
        tvMsgAt = (TextView) getView().findViewById(R.id.tv_msg_at);
    }

    @Override
    protected void setUpView() {
        super.setUpView();
        tvMsgFriendRequest.setOnClickListener(this);
        tvMsgNotification.setOnClickListener(this);
        // register context menu
        registerForContextMenu(conversationListView);
        conversationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EMConversation conversation = conversationListView.getItem(position);
                String username = conversation.getUserName();
                if (username.equals(EMClient.getInstance().getCurrentUser()))
                    Toast.makeText(getActivity(), com.twoculture.easemob.R.string.Cant_chat_with_yourself, Toast.LENGTH_SHORT).show();
                else {
                    // start chat acitivity
                    Intent intent = new Intent(mContext, ChatActivity.class);
                    if (conversation.isGroup()) {
                        if (conversation.getType() == EMConversation.EMConversationType.ChatRoom) {
                            // it's group chat
                            intent.putExtra(Constant.EXTRA_CHAT_TYPE, Constant.CHATTYPE_CHATROOM);
                        } else {
                            intent.putExtra(Constant.EXTRA_CHAT_TYPE, Constant.CHATTYPE_GROUP);
                        }

                    }
                    // it's single chat
                    intent.putExtra(Constant.EXTRA_USER_ID, username);
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    protected void onConnectionDisconnected() {
        super.onConnectionDisconnected();
        if (NetUtils.hasNetwork(getActivity())) {
            errorText.setText(com.twoculture.easemob.R.string.can_not_connect_chat_server_connection);
        } else {
            errorText.setText(com.twoculture.easemob.R.string.the_current_network);
        }
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(com.twoculture.easemob.R.menu.em_delete_message, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        boolean deleteMessage = false;
        if (item.getItemId() == com.twoculture.easemob.R.id.delete_message) {
            deleteMessage = true;
        } else if (item.getItemId() == com.twoculture.easemob.R.id.delete_conversation) {
            deleteMessage = false;
        }
        EMConversation tobeDeleteCons = conversationListView.getItem(((AdapterView.AdapterContextMenuInfo) item.getMenuInfo()).position);
        if (tobeDeleteCons == null) {
            return true;
        }
        if (tobeDeleteCons.getType() == EMConversation.EMConversationType.GroupChat) {
            EaseAtMessageHelper.get().removeAtMeGroup(tobeDeleteCons.getUserName());
        }
        try {
            // delete conversation
            EMClient.getInstance().chatManager().deleteConversation(tobeDeleteCons.getUserName(), deleteMessage);
            InviteMessgeDao inviteMessgeDao = new InviteMessgeDao(getActivity());
            inviteMessgeDao.deleteMessage(tobeDeleteCons.getUserName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        refresh();

        // update unread count
        ((com.twoculture.easemob.ui.MainActivity) mContext).updateUnreadLabel();
        return true;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.tv_msg_friend_request:
                Intent intent = new Intent();
                intent.setClass(mContext, FriendRequestActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_msg_notification:
                Intent notifyIntent = new Intent();
                notifyIntent.setClass(mContext, NotificationActivity.class);
                startActivity(notifyIntent);
                break;
        }
    }
}
