package com.example.demoapp.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.demoapp.R;
import com.freshchat.consumer.sdk.Freshchat;
import com.freshchat.consumer.sdk.FreshchatUser;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Freshchat freshchat;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freshchat_demo);
        Button btnIdentifyUser = findViewById(R.id.reset_user);
        Button btnRestoreUser = findViewById(R.id.restore_user);
        Button btnShowConversation = findViewById(R.id.show_conversation);

        btnShowConversation.setOnClickListener(this);
        btnIdentifyUser.setOnClickListener(this);
        btnRestoreUser.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.restore_user:
                showUserRestoreDialog();
                break;
            case R.id.reset_user:
                Freshchat.resetUser(view.getContext());
                break;
            case R.id.show_conversation:
                Freshchat.showConversations(view.getContext());
                break;
        }
    }


    private Freshchat getFreshchatInstance() {
        if (freshchat == null) {
            freshchat = Freshchat.getInstance(this);
        }
        return freshchat;
    }


    private void showUserRestoreDialog() {
        final FreshchatUser freshchatUser = getFreshchatInstance().getUser();
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Ext Id & Restore Id");
        LinearLayout layout = new LinearLayout(this);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText extIdText = new EditText(this);
        extIdText.setPadding(16, 16, 16, 16);
        extIdText.setHint("External Id");
        extIdText.setBackgroundTintList(ColorStateList.
                valueOf(getResources().getColor(R.color.colorPrimary)));
        extIdText.setText(freshchatUser.getExternalId());
        extIdText.setInputType(InputType.TYPE_CLASS_TEXT);
        layout.addView(extIdText);

        final EditText restoreIdText = new EditText(this);
        restoreIdText.setPadding(16, 16, 16, 16);
        restoreIdText.setHint("Restore Id");
        restoreIdText.setBackgroundTintList(ColorStateList.
                valueOf(getResources().getColor(R.color.colorPrimary)));
        restoreIdText.setText(freshchatUser.getRestoreId());
        restoreIdText.setInputType(InputType.TYPE_CLASS_TEXT);
        layout.addView(restoreIdText);

        dialogBuilder.setView(layout);

        dialogBuilder.setPositiveButton("Identify/Restore", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                try {
                    String externalId = extIdText.getText().toString(); // TODO: You app's external id
                    String restoreId = restoreIdText.getText().toString(); // TODO: Get restore id from app's backend
                    if (restoreId == null || restoreId.length() == 0) {
                        getFreshchatInstance().identifyUser(externalId, null);
                    } else {
                        getFreshchatInstance().identifyUser(externalId, restoreId);
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled
            }
        });


        String existingExternalId = getFreshchatInstance().getUser().getExternalId();
        String existingRestoreId = getFreshchatInstance().getUser().getRestoreId();

        extIdText.setText(existingExternalId);
        restoreIdText.setText(existingRestoreId);


        dialogBuilder.show();
    }


}
