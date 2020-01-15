package com.example.demoapp.activity;

import com.example.demoapp.R;
import com.freshchat.consumer.sdk.Freshchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freshchat_demo);

        Button btnShowConversation = findViewById(R.id.btn_show_all_channels);
        Button btnShowAllFAQ = findViewById(R.id.btnLaunchFAQ);

        btnShowConversation.setOnClickListener(this);
        btnShowAllFAQ.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        final Context context = view.getContext();

        switch (view.getId()) {

            case R.id.btn_show_all_channels:
                Freshchat.showConversations(context);
                break;
            case R.id.btnLaunchFAQ:
                Freshchat.showFAQs(context);
                break;
        }
    }

}
