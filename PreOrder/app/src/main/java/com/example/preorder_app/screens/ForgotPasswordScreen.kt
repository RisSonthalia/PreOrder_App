package com.example.preorder_app.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.preorder_app.HeadingTextComponent
import com.example.preorder_app.MyTextFieldComponent
import com.example.preorder_app.R
import com.example.preorder_app.nav.Screens
import com.example.preorder_app.ui.theme.Primary
import com.example.preorder_app.ui.theme.Secondary
import com.example.preorder_app.viewmodels.Login.LoginUIEvent
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ForgotPassword(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp),
        contentAlignment = Alignment.Center
    ) {
        var email:String by remember{ mutableStateOf("") }
        var isEnabled:Boolean by remember{ mutableStateOf(false) }
        val context = LocalContext.current
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            HeadingTextComponent(value = "Forgot Password")
            Spacer(modifier = Modifier.height(30.dp))
            MyTextFieldComponent(labelValue = stringResource(id = R.string.email),
                painterResource(id = R.drawable.message),
                onTextChanged = {
                    email=it
                    if(it.length>0){
                        isEnabled=true
                    }else {
                        isEnabled = false
                    }

                }
            )

            Button(
                modifier = Modifier
                    .padding(40.dp)
                    .fillMaxWidth()
                    .heightIn(48.dp),
                onClick = {
                    val auth = FirebaseAuth.getInstance()

                    auth.sendPasswordResetEmail(email)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(context, "Mail Sent", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(context, "Error in sending mail", Toast.LENGTH_SHORT).show()
                            }
                        }
                },
                contentPadding = PaddingValues(),
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                shape = RoundedCornerShape(50.dp),
                enabled = isEnabled
            ) {
                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .heightIn(48.dp)
                        .background(
                            brush = Brush.horizontalGradient(listOf(Secondary, Primary)),
                            shape = RoundedCornerShape(50.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Reset",
                        fontSize = 18.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )

                }

            }
            Spacer(modifier = Modifier.height(5.dp))
            Button(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
                    .heightIn(48.dp),
                onClick = {
                   navController.navigate(Screens.LoginScreen.name)
                },
                contentPadding = PaddingValues(),
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                shape = RoundedCornerShape(50.dp),
                enabled = true
            ) {
                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .heightIn(48.dp)
                        .background(
                            brush = Brush.horizontalGradient(listOf(Secondary, Primary)),
                            shape = RoundedCornerShape(50.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Go Back to Login",
                        fontSize = 18.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )

                }

            }



        }
    }
}