package com.example.avengers

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class Login : AppCompatActivity(), View.OnClickListener {

    override fun onClick(v: View?) {

    }

    lateinit var username: String
    lateinit var etPhoneNo: EditText
    lateinit var etPassword: EditText
    lateinit var btnLogin: Button
    lateinit var txtForgot_password:TextView
    lateinit var txtRegister: TextView
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        title = "LOGIN"

        sharedPreferences = getSharedPreferences("Avengers Preference", MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn",false)

        val loginDetails = ArrayList<LoginDetail>();
        loginDetails.add(LoginDetail("8375020857", "password"))
        loginDetails.add(LoginDetail("8588980403", "kumarankit"))

        btnLogin = findViewById(R.id.login_btn)
        etPhoneNo = findViewById(R.id.mobile_no)
        etPassword = findViewById(R.id.password)
        txtForgot_password = findViewById(R.id.forgot_password)
        txtRegister = findViewById(R.id.register)

        val intent = Intent(this@Login,AvengersList::class.java)

        if(isLoggedIn){
            startActivity(intent)
            finish()
        }

        btnLogin.setOnClickListener {
            var inputMobile = etPhoneNo.text.toString()
            var inputPassword = etPassword.text.toString()

            var done = false
            for(i in loginDetails) {
                if (inputMobile.equals(i.getMobileNo()) && inputPassword.equals(i.getPassword())) {
                    savedPreference(inputMobile)
                    startActivity(intent)
                    finish()
                    done = true
                    break
                }
            }
            if(done == false){
                Toast.makeText(this@Login, "Enter Valid Login Details!", Toast.LENGTH_LONG).show()
            }

        }

        txtForgot_password.setOnClickListener {
            Toast.makeText(this@Login, "Relax! And try to remember your password", Toast.LENGTH_LONG).show()
        }

        txtRegister.setOnClickListener {


            btnLogin.setText("Register")
            txtRegister.setText("Login")
            txtForgot_password.setText(null)

            btnLogin.setOnClickListener {

                var inputMobile = etPhoneNo.text.toString()
                var inputPassword = etPassword.text.toString()

                if (inputMobile.length == 10 && inputPassword.length > 7){
                    loginDetails.add(LoginDetail(inputMobile, inputPassword))
                    savedPreference(inputMobile)
                    finish()
                    startActivity(intent)
                }else {
                    Toast.makeText(
                        this@Login,
                        "Phone No. must contain 10 digits and Password must contain at least 8 characters ",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            txtRegister.setOnClickListener{
                val intent = getIntent()
                finish()
                startActivity(intent)
            }

        }

    }

    fun savedPreference(title: String){
        sharedPreferences.edit().putBoolean("isLoggedIn",true).apply()
        sharedPreferences.edit().putString("title", title).apply()
    }
}