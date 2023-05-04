package com.example.freshchatsampleappkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.content.Intent
import android.text.TextUtils
import android.widget.Toast

class HomeScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        initializeViews()
    }

    private fun initializeViews() {
        val appId = findViewById<EditText>(R.id.app_id)
        appId.hint = getString(R.string.app_id_hint)

        val appKey = findViewById<EditText>(R.id.app_key)
        appKey.hint = getString(R.string.app_key_hint)

        val domain = findViewById<EditText>(R.id.domain)
        domain.hint = getString(R.string.domain_hint)

        val proceed = findViewById<Button>(R.id.proceed_btn)
        proceed.setOnClickListener {
            if(TextUtils.isEmpty(appId.text) || TextUtils.isEmpty(appKey.text) || TextUtils.isEmpty(domain.text)) {
                Toast.makeText(this, "App Id, App Key or Domain can't be Empty!",
                    Toast.LENGTH_SHORT).show();
            } else {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}