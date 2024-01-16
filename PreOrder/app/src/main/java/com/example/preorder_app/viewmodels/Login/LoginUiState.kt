package com.example.preorder_app.viewmodels.Login

data class LoginUiState(
    var email  :String = "",
    var password  :String = "",

    var emailError :Boolean = false,
    var passwordError : Boolean = false
)
