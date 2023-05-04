package com.example.freshchatsampleappkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment(ChannelFragment())
        bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.channel -> {
                    loadFragment(ChannelFragment())
                    true
                }
                R.id.faq -> {
                    loadFragment(FaqFragment())
                    true
                }
                R.id.user -> {
                    loadFragment(UserFragment())
                    true
                }
                else -> {
                    loadFragment(ChannelFragment())
                    true
                }
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.home_page_container, fragment)
        transaction.commit()
    }

}