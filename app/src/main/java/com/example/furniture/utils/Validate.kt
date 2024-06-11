package com.example.furniture.utils

object  Validate {
    fun validateEmail(email: String): String {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
        return if (email.trim().isEmpty()) {
            "Email is required"
        } else if (!email.trim().matches(emailRegex)) {
            "The email has the wrong format."
        } else {
            ""
        }
    }

    fun validatePassword(pass: String): String {
        return if (pass.isEmpty()) {
            "Password is required"
        } else if (pass.length < 6 || pass.length > 20) {
            "Password from 6 - 20 characters"
        } else {
            ""
        }
    }
    fun validateConfirmPassWord(pass: String, confirmPass: String) : String{
        return if (confirmPass.isEmpty()) {
            "You must confirm the password"
        }else if (confirmPass.length < 6 || confirmPass.length > 20) {
            "Password from 6 - 20 characters"
        }else if (pass != confirmPass) {
            "Confirm password is not matches"
        }else{
            ""
        }
    }

}