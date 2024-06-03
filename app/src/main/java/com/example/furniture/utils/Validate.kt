package com.example.furniture.utils

object  Validate {
    fun validateEmail(email: String): String {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
        return if (email.trim().isEmpty()) {
            "Không được bỏ trống email."
        } else if (!email.trim().matches(emailRegex)) {
            "Email không đúng định dạng."
        } else {
            ""
        }
    }

    fun validatePassword(pass: String): String {
        return if (pass.isEmpty()) {
            "Không được bỏ trống mật khẩu."
        } else if (pass.length < 6 || pass.length > 20) {
            "Mật khẩu từ 6 - 12 ký tự"
        } else {
            ""
        }
    }
}