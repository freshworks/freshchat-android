package com.example.demoapp.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.demoapp.R;
import com.example.demoapp.Utils;
import com.freshchat.consumer.sdk.ConversationOptions;
import com.freshchat.consumer.sdk.Freshchat;
import com.freshchat.consumer.sdk.FreshchatCallbackStatus;
import com.freshchat.consumer.sdk.FreshchatMessage;
import com.freshchat.consumer.sdk.UnreadCountCallback;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class ChannelsFragment extends Fragment implements View.OnClickListener {
    private TextView unreadCountTextView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, final ViewGroup viewGroup, Bundle savedInstanceState) {
        super.onCreateView(inflater, viewGroup, savedInstanceState);

        final View rootView = inflater.inflate(R.layout.channels_fragment, viewGroup, false);

        Button btnShowConversation = rootView.findViewById(R.id.btn_show_all_channels);
        Button btnConversationOption = rootView.findViewById(R.id.btn_show_filtered_channels);
        Button btnShowUnreadCount = rootView.findViewById(R.id.btn_unread_count);
        Button btnShowUnreadCountForConversation = rootView.findViewById(R.id.btn_unread_count_for_conversation);
        Button btnSendMessage = rootView.findViewById(R.id.btn_send_message);
        unreadCountTextView = rootView.findViewById(R.id.unread_count);

        btnShowConversation.setOnClickListener(this);
        btnConversationOption.setOnClickListener(this);
        btnShowUnreadCount.setOnClickListener(this);
        btnShowUnreadCountForConversation.setOnClickListener(this);
        btnSendMessage.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View view) {

        Context context = view.getContext();

        switch (view.getId()) {

            case R.id.btn_show_all_channels:
                Freshchat.showConversations(view.getContext());
                break;
            case R.id.btn_show_filtered_channels:
                showConversationOptionInputDialog(context);
                break;
            case R.id.btn_unread_count:
                Freshchat.getInstance(context).getUnreadCountAsync(new UnreadCountCallback() {
                    @Override
                    public void onResult(FreshchatCallbackStatus freshchatCallbackStatus, int unreadCount) {
                        unreadCountTextView.setVisibility(View.VISIBLE);
                        unreadCountTextView.setText("Total unread message count : " + unreadCount);
                    }
                });

                break;
            case R.id.btn_unread_count_for_conversation:
                showConversationTagsInputDialog(context);
                break;
            case R.id.btn_send_message:
                showSendMessageInputDialog(context);
                break;
        }
    }


    private void showSendMessageInputDialog(@NonNull final Context context) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setTitle("Send message input");
        LinearLayout layout = new LinearLayout(context);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText messageContent = new EditText(context);
        messageContent.setPadding(16, 16, 16, 16);
        messageContent.setHint("Message");
        messageContent.setBackgroundTintList(ColorStateList.
                valueOf(getResources().getColor(R.color.unity_dark_blue)));
        messageContent.setInputType(InputType.TYPE_CLASS_TEXT);
        layout.addView(messageContent);

        dialogBuilder.setView(layout);


        final EditText messageTag = new EditText(context);
        messageTag.setPadding(16, 16, 16, 16);
        messageTag.setHint("Message Tag");
        messageTag.setBackgroundTintList(ColorStateList.
                valueOf(getResources().getColor(R.color.unity_dark_blue)));
        messageTag.setInputType(InputType.TYPE_CLASS_TEXT);
        layout.addView(messageTag);
        dialogBuilder.setView(layout);


        dialogBuilder.setPositiveButton("Send Message", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                String messageStr = messageContent.getText().toString();
                final String tagStr = messageTag.getText().toString();
                FreshchatMessage freshchatMessage = new FreshchatMessage().setTag(tagStr).setMessage(messageStr);
                Freshchat.sendMessage(context, freshchatMessage);
            }
        });

        dialogBuilder.show();

    }


    private void showConversationOptionInputDialog(@NonNull final Context context) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setTitle("Enter Conversation Tags comma separated");
        LinearLayout layout = new LinearLayout(context);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText conversationTags = new EditText(context);
        conversationTags.setPadding(16, 16, 16, 16);
        conversationTags.setHint("Conversation Tags");
        conversationTags.setBackgroundTintList(ColorStateList.
                valueOf(getResources().getColor(R.color.unity_dark_blue)));
        conversationTags.setInputType(InputType.TYPE_CLASS_TEXT);
        layout.addView(conversationTags);

        dialogBuilder.setView(layout);


        final EditText channelTitle = new EditText(context);
        channelTitle.setPadding(16, 16, 16, 16);
        channelTitle.setHint("Channel Title");
        channelTitle.setBackgroundTintList(ColorStateList.
                valueOf(getResources().getColor(R.color.unity_dark_blue)));
        channelTitle.setInputType(InputType.TYPE_CLASS_TEXT);
        layout.addView(channelTitle);

        dialogBuilder.setView(layout);


        dialogBuilder.setPositiveButton("Show Selected Channels", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                String convTags = conversationTags.getText().toString();

                final List<String> convTagList = Utils.convertStringToList(convTags, ",");
                final String title = channelTitle.getText().toString();

                Freshchat.getInstance(context).getUnreadCountAsync(new UnreadCountCallback() {
                    @Override
                    public void onResult(FreshchatCallbackStatus freshchatCallbackStatus, int unreadCount) {

                        ConversationOptions lConvOptions = new ConversationOptions();
                        lConvOptions.filterByTags(convTagList, title);
                        Freshchat.showConversations(context, lConvOptions);

                    }
                }, convTagList);

            }
        });

        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled
            }
        });

        dialogBuilder.show();
    }


    private void showConversationTagsInputDialog(@NonNull final Context context) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setTitle("Enter Conversation Tags comma separated");
        LinearLayout layout = new LinearLayout(context);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText conversationTagsInput = new EditText(context);
        conversationTagsInput.setPadding(16, 16, 16, 16);
        conversationTagsInput.setHint("Conversation Tags");
        conversationTagsInput.setBackgroundTintList(ColorStateList.
                valueOf(getResources().getColor(R.color.unity_dark_blue)));
        conversationTagsInput.setInputType(InputType.TYPE_CLASS_TEXT);
        layout.addView(conversationTagsInput);

        dialogBuilder.setView(layout);

        dialogBuilder.setPositiveButton("Show Unread Count", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                String conversationTags = conversationTagsInput.getText().toString();

                List<String> conversationTagList = Utils.convertStringToList(conversationTags, ",");

                Freshchat.getInstance(context).getUnreadCountAsync(new UnreadCountCallback() {
                    @Override
                    public void onResult(FreshchatCallbackStatus freshchatCallbackStatus, int unreadCount) {
                        unreadCountTextView.setVisibility(View.VISIBLE);
                        unreadCountTextView.setText("Total unread message count : " + unreadCount);
                    }
                }, conversationTagList);

            }
        });

        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled
            }
        });

        dialogBuilder.show();
    }
}
