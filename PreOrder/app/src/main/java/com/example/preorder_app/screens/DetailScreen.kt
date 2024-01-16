package com.example.preorder_app.screens

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.example.preorder_app.AppToolbar
import com.example.preorder_app.ButtonComponent
import com.example.preorder_app.HeadingTextComponent
import com.example.preorder_app.R
import com.example.preorder_app.nav.Screens
import com.example.preorder_app.ui.theme.Primary
import com.example.preorder_app.ui.theme.Secondary
import com.example.preorder_app.viewmodels.Home.HomeViewModel
import com.example.preorder_app.viewmodels.Login.LoginViewModel
import com.example.preorder_app.viewmodels.SignUp.SignupViewModel
import kotlinx.coroutines.launch

@Composable
fun DetailScreen(navController: NavController,homeViewModel: HomeViewModel,loginViewModel: LoginViewModel,signupViewModel: SignupViewModel,navBackStackEntry: NavBackStackEntry) {
    val name = navBackStackEntry.arguments?.getString("name") ?: ""
    val price = navBackStackEntry.arguments?.getString("price") ?: ""
    val id = navBackStackEntry.arguments?.getString("id") ?: ""

    Scaffold(
        topBar = {
            AppToolbar(
                toolbarTitle = stringResource(id = R.string.Detail),
                logoutButtonClicked = {
                    homeViewModel.logout(navController, loginViewModel = loginViewModel, signupViewModel = signupViewModel)
                },
                navigationIconClicked = {
                    navController.popBackStack()
                }
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                HeadingTextComponent(value = name)
                Spacer(modifier = Modifier.height(40.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Product ID",
                        style = TextStyle(
                            fontSize = 17.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = id,
                        style = TextStyle(
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Normal,
                        ),

                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Price",
                        style = TextStyle(
                            fontSize = 17.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "Rs. "+price,
                        style = TextStyle(
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Normal,

                        ),

                        )
                }
                Spacer(modifier = Modifier.height(30.dp))
                Button(
                    modifier = Modifier
                        .padding(40.dp)
                        .fillMaxWidth()
                        .heightIn(48.dp),
                    onClick = {
                       navController.navigate(Screens.HomeScreen.name)
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
                            text = "Done",
                            fontSize = 18.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )

                    }

                }


            }
        }
    }
}