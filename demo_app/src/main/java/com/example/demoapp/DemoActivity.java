package com.example.demoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.freshchat.consumer.sdk.Freshchat;
import com.freshchat.consumer.sdk.FreshchatConfig;
import com.freshchat.consumer.sdk.FreshchatUser;

public class DemoActivity extends AppCompatActivity {

    private Button btnShowConversations, btnShowFAQs;
    

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        //init
        FreshchatConfig freshchatConfig=new FreshchatConfig("<YOUR-APP-ID>","<YOUR-APP-KEY>");
        Freshchat.getInstance(getApplicationContext()).init(freshchatConfig);

        //Update user information
        FreshchatUser user = Freshchat.getInstance(getApplicationContext()).getUser();
        user.setFirstName("John").setLastName("Doe").setEmail("john@john.doe").setPhone("001", "2542542544");
        Freshchat.getInstance(getApplicationContext()).setUser(user);

        btnShowFAQs = (Button) findViewById(R.id.btnShowFAQs);
        btnShowConversations = (Button) findViewById(R.id.btnShowConversations);
        btnShowFAQs.setOnClickListener(viewClickListener);
        btnShowConversations.setOnClickListener(viewClickListener);
    }

    View.OnClickListener viewClickListener = new View.OnClickListener() {
        @Override
        public void onClick (View v) {
            if(v.getId() == R.id.btnShowFAQs) {

                Freshchat.showFAQs(DemoActivity.this);

            } else if(v.getId() == R.id.btnShowConversations) {

                Freshchat.showConversations(DemoActivity.this);

            }
        }
    };

}
