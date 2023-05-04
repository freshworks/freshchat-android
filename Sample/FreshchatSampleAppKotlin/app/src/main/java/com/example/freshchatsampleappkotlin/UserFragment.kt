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
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import com.freshchat.consumer.sdk.Freshchat
import com.freshchat.consumer.sdk.FreshchatUser
import java.lang.Exception
import java.util.HashMap

class UserFragment : Fragment(), View.OnClickListener {
    private var freshchat: Freshchat? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user, container, false)
        initializeChannelViews(view)
        return view
    }


    private fun initializeChannelViews(view: View) {
        val btnResetUser: Button = view.findViewById(R.id.reset_user)
        val btnUpdateUser: Button =
            view.findViewById(R.id.update_user)
        val btnUpdateUserProperties = view.findViewById<Button>(R.id.update_user_properties)
        val btnRestoreUser = view.findViewById<Button>(R.id.restore_user)

        btnResetUser.setOnClickListener(this)
        btnUpdateUser.setOnClickListener(this)
        btnUpdateUserProperties.setOnClickListener(this)
        btnRestoreUser.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        view?.apply {
            when (this.id) {
                R.id.reset_user -> Freshchat.resetUser(this.context)
                R.id.update_user -> showUserInfoInputDialog(this.context)
                R.id.update_user_properties -> showUserPropertiesInputDialog(this.context)
                R.id.restore_user -> showUserRestoreDialog(this.context)
            }
        }
    }

    private fun showUserPropertiesInputDialog(@NonNull context: Context) {
        val dialogBuilder = AlertDialog.Builder(context)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.alert_dialog_user, null, false)
        dialogBuilder.setView(dialogView)

        val userPropertiesDialogTitle = dialogView.findViewById<TextView>(R.id.alert_dialog_title)
        userPropertiesDialogTitle.text = getString(R.string.user_properties_dialog_title)

        val keyInput = dialogView.findViewById<EditText>(R.id.first_input_box)
        keyInput.hint = getString(R.string.key_hint)

        val valueInput = dialogView.findViewById<EditText>(R.id.second_input_box)
        valueInput.hint = getString(R.string.value_hint)

        val positiveButton = dialogView.findViewById<Button>(R.id.positive_button)
        positiveButton.text = getString(R.string.ok)

        val negativeButton = dialogView.findViewById<Button>(R.id.negative_button)
        negativeButton.text = getString(R.string.cancel)

        val dialog = dialogBuilder.create()
        dialog.show()

        positiveButton.setOnClickListener {
            val key: String = keyInput.text.toString()
            val value: String = valueInput.text.toString()

            val map: MutableMap<String, String> = HashMap()
            map[key] = value

            try {
                Freshchat.getInstance(context).setUserProperties(map)
            } catch (e: Exception) {
                Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show()
            }
            dialog.dismiss()
        }

        negativeButton.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun showUserRestoreDialog(context: Context) {
        val freshchatUser: FreshchatUser? = getFreshchatInstance(context)?.user
        val dialogBuilder = AlertDialog.Builder(context)
        val dialogView = this.layoutInflater.inflate(R.layout.alert_dialog_user, null, false)
        dialogBuilder.setView(dialogView)

        val title = dialogView.findViewById<TextView>(R.id.alert_dialog_title)
        title.text = getString(R.string.external_restore_dialog_title)

        val externalIdInput = dialogView.findViewById<EditText>(R.id.first_input_box)
        externalIdInput.hint = getString(R.string.externalId)
        externalIdInput.setText(freshchatUser?.externalId)

        val restoreIdInput = dialogView.findViewById<EditText>(R.id.second_input_box)
        restoreIdInput.hint = getString(R.string.restoreId)
        restoreIdInput.setText(freshchatUser?.restoreId)

        val positiveButton = dialogView.findViewById<Button>(R.id.positive_button)
        positiveButton.text = getString(R.string.identify_restore_positive_btn)

        val negativeButton = dialogView.findViewById<Button>(R.id.negative_button)
        negativeButton.text = getString(R.string.cancel)

        val dialog = dialogBuilder.create()
        dialog.show()
        positiveButton.setOnClickListener {
            try {
                val externalId: String =
                    externalIdInput.text.toString()
                val restoreId: String =
                    restoreIdInput.text.toString()
                if (restoreId.isEmpty()) {
                    getFreshchatInstance(context)!!.identifyUser(externalId, null)
                } else {
                    getFreshchatInstance(context)!!.identifyUser(externalId, restoreId)
                }
            } catch (e: Exception) {
                Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show()
            }
            dialog.dismiss()
        }
        negativeButton.setOnClickListener {
            dialog.dismiss()
        }
    }
    
    private fun showUserInfoInputDialog(context: Context) {
        val freshchatUser: FreshchatUser? = getFreshchatInstance(context)?.user
        val dialogBuilder = AlertDialog.Builder(context)
        val dialogView = this.layoutInflater.inflate(R.layout.update_user_dialog, null, false)
        dialogBuilder.setView(dialogView)

        val firstName = dialogView.findViewById<EditText>(R.id.first_name_input)
        firstName.setText(freshchatUser?.firstName)

        val lastName = dialogView.findViewById<EditText>(R.id.last_name_input)
        lastName.setText(freshchatUser?.lastName)

        val emailText = dialogView.findViewById<EditText>(R.id.email_input)
        emailText.setText(freshchatUser?.email)

        val phoneNoCountryCode = dialogView.findViewById<EditText>(R.id.phone_country_code_input)
        phoneNoCountryCode.setText(freshchatUser?.phoneCountryCode)

        val phoneNo = dialogView.findViewById<EditText>(R.id.phone_no_input)
        phoneNo.setText(freshchatUser?.phone)

        val positiveButton = dialogView.findViewById<Button>(R.id.update_user_button)
        val negativeButton = dialogView.findViewById<Button>(R.id.cancel_button_update_user_dialog)

        val dialog = dialogBuilder.create()
        dialog.show()

        positiveButton.setOnClickListener {
            val fName: String = firstName.text.toString()
            val lName: String = lastName.text.toString()
            val email: String = emailText.text.toString()
            val phoneCountryCode: String = phoneNoCountryCode.text.toString()
            val phone: String = phoneNo.text.toString()

            freshchatUser?.apply {
                if (fName.isNotEmpty()) {
                    this.firstName = fName
                }
                if (lName.isNotEmpty()) {
                    this.lastName = lName
                }
                if (email.isNotEmpty()) {
                    this.email = email
                }
                if (phone.isNotEmpty()) {
                    this.setPhone(phoneCountryCode, phone)
                }
                try {
                    getFreshchatInstance(context)!!.user = this
                } catch (e: Exception) {
                    Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show()
                }
            }
            dialog.dismiss()
        }
        negativeButton.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun getFreshchatInstance(context: Context): Freshchat? {
        if (freshchat == null) {
            freshchat = Freshchat.getInstance(context)
        }
        return freshchat
    }
}


