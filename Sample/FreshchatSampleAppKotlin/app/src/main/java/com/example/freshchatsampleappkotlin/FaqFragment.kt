package com.example.freshchatsampleappkotlin

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import com.freshchat.consumer.sdk.FaqOptions
import com.freshchat.consumer.sdk.FaqOptions.FilterType
import com.freshchat.consumer.sdk.Freshchat

class FaqFragment : Fragment(), View.OnClickListener {

    private var edFAQTags: EditText? = null
    private var edFAQTitle: EditText? = null
    private var edContactUsTags: EditText? = null
    private var edContactUsTitle: EditText? = null
    private var radioFilterType: RadioGroup? = null
    private var checkBoxShowContactUs: CheckBox? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_faq, container, false)
        initializeChannelViews(view)
        return view
    }

    private fun initializeChannelViews(view: View) {
        val btnShowAllFaq: Button = view.findViewById(R.id.show_all_faq)
        val btnShowSelectedFaq: Button =
            view.findViewById(R.id.selected_faq)
        btnShowAllFaq.setOnClickListener(this)
        btnShowSelectedFaq.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        view?.apply {
            when (this.id) {
                R.id.show_all_faq -> Freshchat.showFAQs(this.context)
                R.id.selected_faq -> showCustomDialog(this.context)
            }
        }
    }

    private fun showCustomDialog(context: Context) {
        val dialogView: View = this.layoutInflater.inflate(R.layout.alert_dialog, null)
        val dialogBuilder = AlertDialog.Builder(context)
        dialogBuilder.setView(dialogView)
        val alertDialog = dialogBuilder.create()
        val btnLaunchFilteredFAQ =
            dialogView.findViewById<Button>(R.id.alert_btn_launch_with_faq_options)
        btnLaunchFilteredFAQ.setOnClickListener { view ->
            val lFaqTitle: String = edFAQTitle?.text.toString()
            val lCTitle: String = edContactUsTitle?.text.toString()
            val lFaqTags = Utils.convertStringToList(edFAQTags?.text.toString(), ",")
            val lCTags = Utils.convertStringToList(edContactUsTags?.text.toString(), ",")
            val lShowContactUs: Boolean = checkBoxShowContactUs?.isChecked == true
            val lFaqOptions = FaqOptions()
            val filterType: FilterType =
                if (radioFilterType?.checkedRadioButtonId == R.id.radioFilterTypeArticleLeft) {
                    FilterType.ARTICLE
                } else {
                    FilterType.CATEGORY
                }
            lFaqOptions.filterByTags(lFaqTags, lFaqTitle, filterType)
            lFaqOptions.filterContactUsByTags(lCTags, lCTitle)
            lFaqOptions.showContactUsOnFaqScreens(lShowContactUs)
            Freshchat.showFAQs(view.context, lFaqOptions)
            alertDialog.dismiss()
        }
        edFAQTags = dialogView.findViewById(R.id.input_faq_tags)
        edFAQTitle = dialogView.findViewById(R.id.input_faq__title)
        edContactUsTags = dialogView.findViewById(R.id.input_contact_us_tags)
        edContactUsTitle = dialogView.findViewById(R.id.input_contact_us_title)
        radioFilterType = dialogView.findViewById(R.id.radio_filter_type)
        checkBoxShowContactUs =
            dialogView.findViewById(R.id.check_box_show_contactus_left)
        alertDialog.show()
    }


}