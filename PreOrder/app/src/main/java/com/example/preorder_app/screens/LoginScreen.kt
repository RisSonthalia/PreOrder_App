package com.example.preorder_app.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.preorder_app.ButtonComponent
import com.example.preorder_app.ClickableLoginTextComponent
import com.example.preorder_app.DividerTextComponent
import com.example.preorder_app.ForgotPasswordText
import com.example.preorder_app.GoogleSignIn
import com.example.preorder_app.HeadingTextComponent
import com.example.preorder_app.MyTextFieldComponent
import com.example.preorder_app.NormalTextComponents
import com.example.preorder_app.PasswordTextFieldComponent
import com.example.preorder_app.R
import com.example.preorder_app.nav.Screens
import com.example.preorder_app.viewmodels.Login.LoginUIEvent
import com.example.preorder_app.viewmodels.Login.LoginViewModel
import com.example.preorder_app.viewmodels.SignUp.SignupViewModel


@Composable
fun LoginScreen(navController: NavController,signupViewModel: SignupViewModel,loginViewModel: LoginViewModel) {


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp),
        contentAlignment = Alignment.Center
    ) {
        val Context= LocalContext.current
         Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                NormalTextComponents(value = stringResource(id = R.string.welcome))
                HeadingTextComponent(value = stringResource(id = R.string.signin))
                Spacer(modifier = Modifier.height(20.dp))

                MyTextFieldComponent(labelValue = stringResource(id = R.string.email),
                    painterResource(id = R.drawable.message),
                    onTextChanged = {
                        loginViewModel.onEvent(LoginUIEvent.EmailChanged(it), navController = navController, context = Context)
                        Log.d("dedd","Inside firat ame")
                    },
                    errorStatus = loginViewModel.loginUIState.value.emailError
                )

                PasswordTextFieldComponent(
                    labelValue = stringResource(id = R.string.password),
                    painterResource(id = R.drawable.lock),
                    onTextSelected = {
                        loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it), navController = navController, context =Context )

                    },
                    errorStatus = loginViewModel.loginUIState.value.passwordError
                )

                Spacer(modifier = Modifier.height(40.dp))

                ButtonComponent(
                    value = stringResource(id = R.string.login),
                    onButtonClicked = {
                        loginViewModel.onEvent(LoginUIEvent.LoginButtonClicked, navController = navController,context=Context)
                    },
                    isEnabled = loginViewModel.allValidationsPassed.value
                )

                Spacer(modifier = Modifier.height(20.dp))
             ForgotPasswordText(){
                 navController.navigate(Screens.ForgotPassWordScreen.name)
             }
             Spacer(modifier = Modifier.height(20.dp))
                DividerTextComponent()



                ClickableLoginTextComponent(tryingToLogin = false, onTextSelected = {
                    navController.navigate(Screens.SignUpScreen.name)
                }
                )
             Spacer(modifier = Modifier.height(10.dp))
             Text(
                 text = "Or",
                 modifier = Modifier
                     .fillMaxWidth()
                     .heightIn(min = 20.dp),
                 style = TextStyle(
                     fontSize = 22.sp,
                     fontWeight = FontWeight.Bold,
                     fontStyle = FontStyle.Normal
                 ),
                 textAlign = TextAlign.Center
             )

             GoogleSignIn(navController=navController)

            }
        if(loginViewModel.loginInProgress.value) {
            CircularProgressIndicator()
        }
        }

    }

