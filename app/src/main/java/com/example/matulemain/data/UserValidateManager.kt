package com.example.matulemain.data

import android.util.Patterns

class UserValidateManager {
    var isDialogShow = false

    fun emailValidate(email: String): Boolean {

        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun passwordValidate(password: String): Boolean {
        return (password.length > 6)
    }

    fun showDialog() {
        isDialogShow = true
    }

    fun login(email: String, password: String): Boolean {
            if (!emailValidate(email)){
                showDialog()
                return false
            }
            if (!passwordValidate(password)){
                showDialog()
                return false
            }


        return true
    }
}