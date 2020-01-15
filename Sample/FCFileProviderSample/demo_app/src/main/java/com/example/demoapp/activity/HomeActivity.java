package com.example.demoapp.activity;

import com.example.demoapp.R;
import com.freshchat.consumer.sdk.Freshchat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freshchat_demo);
        Button btnShowConversation = findViewById(R.id.btn_show_all_channels);
        btnShowConversation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Freshchat.showConversations(view.getContext());
            }
        });

    }
}