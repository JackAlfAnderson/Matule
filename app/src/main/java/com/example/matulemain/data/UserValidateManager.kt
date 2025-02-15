package com.example.matulemain.data

import android.util.Patterns

class UserValidateManager {
    var isDialogShow = false

    fun emailValidate(email: String): Boolean {

        return Patterns.EMAIL_ADDRESS.matcher(email).matches() // результат валидации почты возвращается сразу, что делает код более компактным
    }

    fun passwordValidate(password: String): Boolean {
        return (password.length > 6) // результат валидации пароля возвращается сразу, что делает код более компактным
    }

    fun showDialog() {
        isDialogShow = true
    }

    fun login(email: String, password: String): Boolean {

        //используются уже готовые функции для проверки на валидность почты и пароля
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