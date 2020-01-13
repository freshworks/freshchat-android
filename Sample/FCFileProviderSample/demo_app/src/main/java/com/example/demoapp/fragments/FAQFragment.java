package com.example.demoapp.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.demoapp.R;
import com.example.demoapp.Utils;
import com.freshchat.consumer.sdk.FaqOptions;
import com.freshchat.consumer.sdk.Freshchat;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class FAQFragment extends Fragment implements View.OnClickListener {

    private EditText edFAQTags;
    private EditText edFAQTitle;
    private EditText edContactUsTags;
    private EditText edContactUsTitle;
    private RadioGroup radioFilterType;
    private CheckBox checkBoxShowContactUs;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.faq_fragment, viewGroup, false);

        Button btnLaunchWithFAQOptions = rootView.findViewById(R.id.btnLaunchWithFAQOptionsLeft);
        Button launchAllFAQ = rootView.findViewById(R.id.btnLaunchFAQ);

        launchAllFAQ.setOnClickListener(this);
        btnLaunchWithFAQOptions.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View view) {

        Context context = view.getContext();

        switch (view.getId()) {
            case R.id.btnLaunchFAQ:
                Freshchat.showFAQs(context);
                break;
            case R.id.btnLaunchWithFAQOptionsLeft:
                showCustomDialog(view.getContext());
                break;
        }
    }


    private void showCustomDialog(@NonNull Context context) {

        View dialogView = LayoutInflater.from(context).inflate(R.layout.alert_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();

        Button btnLaunchFilteredFAQ = dialogView.findViewById(R.id.alert_btn_launch_with_faq_options);

        btnLaunchFilteredFAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lFaqTitle = edFAQTitle.getText().toString();
                String lCTitle = edContactUsTitle.getText().toString();
                List<String> lFaqTags = Utils.convertStringToList(edFAQTags.getText().toString(), ",");
                List<String> lCTags = Utils.convertStringToList(edContactUsTags.getText().toString(), ",");
                boolean lShowContactUs = checkBoxShowContactUs.isChecked();

                FaqOptions lFaqOptions = new FaqOptions();
                FaqOptions.FilterType filterType;

                if (radioFilterType.getCheckedRadioButtonId() == R.id.radioFilterTypeArticleLeft) {
                    filterType = FaqOptions.FilterType.ARTICLE;
                } else {
                    filterType = FaqOptions.FilterType.CATEGORY;
                }

                lFaqOptions.filterByTags(lFaqTags, lFaqTitle, filterType);
                lFaqOptions.filterContactUsByTags(lCTags, lCTitle);
                lFaqOptions.showContactUsOnFaqScreens(lShowContactUs);
                Freshchat.showFAQs(view.getContext(), lFaqOptions);
            }
        });

        edFAQTags = dialogView.findViewById(R.id.input_faq_tags);

        edFAQTitle = dialogView.findViewById(R.id.input_faq__title);

        edContactUsTags = dialogView.findViewById(R.id.input_contact_us_tags);

        edContactUsTitle = dialogView.findViewById(R.id.input_contact_us_title);

        radioFilterType = dialogView.findViewById(R.id.radio_filter_type);

        checkBoxShowContactUs = dialogView.findViewById(R.id.check_box_show_contactus_left);

        alertDialog.show();
    }
}
