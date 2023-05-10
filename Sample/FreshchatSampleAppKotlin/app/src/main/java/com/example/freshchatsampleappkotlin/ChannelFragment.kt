package com.example.freshchatsampleappkotlin

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.freshchat.consumer.sdk.ConversationOptions
import com.freshchat.consumer.sdk.Freshchat

class ChannelFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_channel, container, false)
        initializeChannelViews(view)
        return view
    }

    private fun initializeChannelViews(view: View) {
        val btnShowConversation: Button = view.findViewById(R.id.show_all_channels)
        val btnShowSelectedChannels: Button =
            view.findViewById(R.id.selected_channels)
        btnShowConversation.setOnClickListener(this)
        btnShowSelectedChannels.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        view?.apply {
            when (this.id) {
                R.id.show_all_channels -> Freshchat.showConversations(this.context)
                R.id.selected_channels -> showConversationOptionInputDialog(this.context)
            }
        }
    }

    private fun showConversationOptionInputDialog(context: Context) {
        val dialogBuilder = AlertDialog.Builder(context)
        val dialogView =
            this.layoutInflater.inflate(
                R.layout.show_selected_channel_dialog,
                null, false
            )
        dialogBuilder.setView(dialogView)

        val conversationTags = dialogView.findViewById<EditText>(R.id.conversation_tags_input)
        val channelTitle = dialogView.findViewById<EditText>(R.id.channel_title_input)
        val positiveButton = dialogView.findViewById<Button>(R.id.show_selected_channels_button)
        val negativeButton = dialogView.findViewById<Button>(R.id.cancel_button)

        val dialog = dialogBuilder.create()
        dialog.show()

        positiveButton.setOnClickListener {
            val convTags = conversationTags.text.toString()
            val convTagList: List<String> =
                Utils.convertStringToList(convTags, ",")
            val title = channelTitle.text.toString()
            Freshchat.getInstance(context)
                .getUnreadCountAsync({ freshchatCallbackStatus, unreadCount ->
                    val lConvOptions = ConversationOptions()
                    lConvOptions.filterByTags(convTagList, title)
                    Freshchat.showConversations(context, lConvOptions)
                }, convTagList)
            dialog.dismiss()
        }
        negativeButton.setOnClickListener {
            dialog.dismiss()
        }
    }
}