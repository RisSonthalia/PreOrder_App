package com.example.preorder_app.viewmodels.Home

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.preorder_app.nav.Screens
import com.example.preorder_app.viewmodels.Login.LoginUiState
import com.example.preorder_app.viewmodels.Login.LoginViewModel
import com.example.preorder_app.viewmodels.NavigationItem
import com.example.preorder_app.viewmodels.RegistrationUIState
import com.example.preorder_app.viewmodels.SignUp.SignupViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class HomeViewModel : ViewModel() {

    private val TAG = HomeViewModel::class.simpleName

    val navigationItemsList = listOf<NavigationItem>(
        NavigationItem(
            title = "Home",
            icon = Icons.Default.Home,
            description = "Home Screen",
            itemId = "homeScreen"
        ),
        NavigationItem(
            title = "Settings",
            icon = Icons.Default.Settings,
            description = "Settings Screen",
            itemId = "settingsScreen"
        ),
        NavigationItem(
            title = "Favorite",
            icon = Icons.Default.Favorite,
            description = "Favorite Screen",
            itemId = "favoriteScreen"
        )
    )

    val isUserLoggedIn: MutableLiveData<Boolean> = MutableLiveData()

    fun logout(navController: NavController,loginViewModel: LoginViewModel,signupViewModel: SignupViewModel){

        val firebaseAuth = FirebaseAuth.getInstance()

        firebaseAuth.signOut()
        Firebase.auth.signOut()

        val authStateListener = FirebaseAuth.AuthStateListener {
            if (it.currentUser == null) {
                Log.d(TAG, "Inside sign outsuccess")
                loginViewModel.loginUIState.value= LoginUiState()
                signupViewModel.registrationUIState.value= RegistrationUIState()
                loginViewModel.allValidationsPassed.value=false
                signupViewModel.allValidationsPassed.value=false
                navController.navigate(Screens.LoginScreen.name)
            } else {
                Log.d(TAG, "Inside sign out is not complete")
            }
        }

        firebaseAuth.addAuthStateListener(authStateListener)

    }

    fun checkForActiveSession() {
        if (FirebaseAuth.getInstance().currentUser != null) {
            Log.d(TAG, "Valid session")
            isUserLoggedIn.value = true
        } else {
            Log.d(TAG, "User is not logged in")
            isUserLoggedIn.value = false
        }
    }


    val emailId: MutableLiveData<String> = MutableLiveData()

    fun getUserData() {
        FirebaseAuth.getInstance().currentUser?.also {
            it.email?.also { email ->
                emailId.value = email
            }
        }
    }

}