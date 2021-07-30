package com.example.avengers

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AvengersList : AppCompatActivity() {

    lateinit var logout_btn: Button
    var user_mobile_no : String? = "Avengers"
    lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.avengers_list)

        logout_btn = findViewById(R.id.logout_btn)

        sharedPreferences = getSharedPreferences("Avengers Preference", Context.MODE_PRIVATE)
        user_mobile_no = sharedPreferences.getString("title","The Avengers")

        title = user_mobile_no

        logout_btn.setOnClickListener{
            sharedPreferences.edit().clear().apply()
            val intent = Intent(this@AvengersList,Login :: class.java)
            startActivity(intent)
            finish()
        }
    }
}