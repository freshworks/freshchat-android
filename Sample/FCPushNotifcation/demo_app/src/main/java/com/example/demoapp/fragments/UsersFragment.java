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
import android.widget.Toast;

import com.example.demoapp.R;
import com.freshchat.consumer.sdk.Freshchat;
import com.freshchat.consumer.sdk.FreshchatUser;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class UsersFragment extends Fragment implements View.OnClickListener {

    private Freshchat freshchat;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.user_fragment, viewGroup, false);
        Button btnUpdateUser = rootView.findViewById(R.id.update_user);
        Button btnUpdateUserProps = rootView.findViewById(R.id.update_user_props);

        btnUpdateUser.setOnClickListener(this);
        btnUpdateUserProps.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.update_user:
                showUserInfoInputDialog(view.getContext());
                break;
            case R.id.update_user_props:
                showUserPropertiesInputDialog(view.getContext());
                break;
        }
    }

    private void showUserInfoInputDialog(@NonNull final Context context) {
        final FreshchatUser freshchatUser = getFreshchatInstance(context).getUser();
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setTitle("User Details : ");
        LinearLayout layout = new LinearLayout(context);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText firstNameText = new EditText(context);
        firstNameText.setPadding(16, 16, 16, 16);
        firstNameText.setHint("First Name");
        firstNameText.setText(freshchatUser.getFirstName());
        firstNameText.setInputType(InputType.TYPE_CLASS_TEXT);
        firstNameText.setBackgroundTintList(ColorStateList.
                valueOf(getResources().getColor(R.color.colorPrimary)));

        layout.addView(firstNameText);

        final EditText lastNameText = new EditText(context);
        lastNameText.setPadding(16, 16, 16, 16);
        lastNameText.setHint("Last Name");
        lastNameText.setText(freshchatUser.getLastName());
        lastNameText.setInputType(InputType.TYPE_CLASS_TEXT);
        lastNameText.setBackgroundTintList(ColorStateList.
                valueOf(getResources().getColor(R.color.colorPrimary)));

        layout.addView(lastNameText);

        final EditText emailText = new EditText(context);
        emailText.setPadding(16, 16, 16, 16);
        emailText.setHint("Email");
        emailText.setText(freshchatUser.getEmail());
        emailText.setInputType(InputType.TYPE_CLASS_TEXT);
        emailText.setBackgroundTintList(ColorStateList.
                valueOf(getResources().getColor(R.color.colorPrimary)));

        layout.addView(emailText);

        final EditText countryCodeText = new EditText(context);
        countryCodeText.setPadding(16, 16, 16, 16);
        countryCodeText.setHint("Phone Country Code");
        countryCodeText.setText(freshchatUser.getPhoneCountryCode());
        countryCodeText.setInputType(InputType.TYPE_CLASS_PHONE);
        countryCodeText.setBackgroundTintList(ColorStateList.
                valueOf(getResources().getColor(R.color.colorPrimary)));

        layout.addView(countryCodeText);

        final EditText phoneText = new EditText(context);
        phoneText.setPadding(16, 16, 16, 16);
        phoneText.setHint("Phone No");
        phoneText.setText(freshchatUser.getPhone());
        phoneText.setInputType(InputType.TYPE_CLASS_PHONE);
        phoneText.setBackgroundTintList(ColorStateList.
                valueOf(getResources().getColor(R.color.colorPrimary)));
        layout.addView(phoneText);

        dialogBuilder.setView(layout);

        dialogBuilder.setPositiveButton("Update User", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String fName = firstNameText.getText().toString();
                String lName = lastNameText.getText().toString();
                String email = emailText.getText().toString();
                String phonecc = countryCodeText.getText().toString();
                String phone = phoneText.getText().toString();

                if (fName != null && !fName.isEmpty()) {
                    freshchatUser.setFirstName(fName);
                }
                if (lName != null && !lName.isEmpty()) {
                    freshchatUser.setLastName(lName);
                }
                if (email != null && !email.isEmpty()) {
                    freshchatUser.setEmail(email);
                }
                if (phone != null && !phone.isEmpty()) {
                    if (phonecc != null) {
                        freshchatUser.setPhone(phonecc, phone);
                    } else {
                        freshchatUser.setPhone(null, phone);
                    }
                }
                try {
                    getFreshchatInstance(context).setUser(freshchatUser);
                } catch (Exception e) {
                    Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled
            }
        });

        dialogBuilder.show();
    }

    private void showUserPropertiesInputDialog(@NonNull final Context context) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setTitle("Enter Key Value");
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(layoutParams);

        final EditText inputPropertiesKey = new EditText(context);
        inputPropertiesKey.setPadding(16, 16, 16, 16);
        inputPropertiesKey.setHint("Key");
        inputPropertiesKey.setInputType(InputType.TYPE_CLASS_TEXT);
        inputPropertiesKey.setBackgroundTintList(ColorStateList.
                valueOf(getResources().getColor(R.color.colorPrimary)));

        layout.addView(inputPropertiesKey);

        final EditText inputPropertiesValue = new EditText(context);
        inputPropertiesValue.setPadding(16, 16, 16, 16);
        inputPropertiesValue.setHint("Value");
        inputPropertiesValue.setInputType(InputType.TYPE_CLASS_TEXT);
        inputPropertiesValue.setBackgroundTintList(ColorStateList.
                valueOf(getResources().getColor(R.color.colorPrimary)));
        layout.addView(inputPropertiesValue);

        dialogBuilder.setView(layout);

        dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String key = inputPropertiesKey.getText().toString();
                String value = inputPropertiesValue.getText().toString();

                Map<String, String> map = new HashMap<>();
                map.put(key, value);

                try {
                    Freshchat.getInstance(context).setUserProperties(map);
                } catch (Exception e) {
                    Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled
            }
        });

        dialogBuilder.show();
    }

    private Freshchat getFreshchatInstance(@NonNull Context context) {
        if (freshchat == null) {
            freshchat = Freshchat.getInstance(context);
        }
        return freshchat;
    }

}
