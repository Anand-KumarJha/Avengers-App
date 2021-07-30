package com.example.avengers

class LoginDetail constructor(mobileNo: String, password: String) {
    private var mobileNo1 = mobileNo
    private var password1 = password
    fun getMobileNo(): String {
        return mobileNo1
    }
    fun getPassword(): String {
        return password1
    }
}