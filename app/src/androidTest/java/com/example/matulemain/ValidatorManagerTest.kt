package com.example.matulemain

import com.example.matulemain.data.UserValidateManager
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ValidatorManagerTest {

    lateinit var userValidateManager: UserValidateManager

    @Before
    fun setUp(){
        userValidateManager = UserValidateManager()
    }

    @Test
    fun validateEmail(){
        Assert.assertFalse(userValidateManager.emailValidate("invalidEmail"))
    }

    @Test
    fun validatePassword(){
        Assert.assertFalse(userValidateManager.passwordValidate("short"))
    }

    @Test
    fun showDialogIncorrectEmail(){
        userValidateManager.login("invalidemail", "correctPassword")
        Assert.assertTrue(userValidateManager.isDialogShow)
    }

    @Test
    fun showDialogIncorrectPassword(){
        userValidateManager.login("valid@email.com", "123")
        Assert.assertTrue(userValidateManager.isDialogShow)
    }

    @Test
    fun successLogin(){
        Assert.assertTrue(userValidateManager.login("valid@email.com", "Password123!"))
    }

    @Test
    fun failedLogin(){
        Assert.assertFalse(userValidateManager.login("bad.com", "short"))
    }
}