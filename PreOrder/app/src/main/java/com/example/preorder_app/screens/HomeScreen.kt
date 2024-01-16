package com.example.preorder_app.screens

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.preorder_app.AppToolbar
import com.example.preorder_app.HeadingTextComponent
import com.example.preorder_app.NormalTextComponents
import com.example.preorder_app.R
import com.example.preorder_app.viewmodels.GetData.GetDataViewModel
import com.example.preorder_app.viewmodels.Home.HomeViewModel
import com.example.preorder_app.viewmodels.Login.LoginViewModel
import com.example.preorder_app.viewmodels.SignUp.SignupViewModel
import kotlinx.coroutines.launch
import org.checkerframework.checker.units.qual.A

fun itemlistid():List<String>{
    return listOf("Bkbld6zM0DVBaum82huU","M18DirFFczgTvisNIaDB","MdYlINQly6Yxf65exmJ8","kX4dwpINjgt9Xb6GjoVs","zLQ9NQ57T1KIL1mkknXV")
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = viewModel(),
    loginViewModel: LoginViewModel,
    signupViewModel: SignupViewModel,
    getDataViewModel: GetDataViewModel
) {
    val scaffoldState = rememberBottomSheetScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    homeViewModel.getUserData()

    Scaffold(
        topBar = {
            AppToolbar(
                toolbarTitle = stringResource(id = R.string.home),
                logoutButtonClicked = {
                    homeViewModel.logout(
                        navController,
                        loginViewModel = loginViewModel,
                        signupViewModel = signupViewModel
                    )
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
            var list = itemlistid()
            val context = LocalContext.current

            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                item{
                    Spacer(modifier = Modifier.height(10.dp))
                    HeadingTextComponent(value = "Shopping List")
                    Spacer(modifier = Modifier.height(20.dp))
                }
                items(list) { itemId ->

                    var itemName by remember { mutableStateOf("") }
                    var itemPrice by remember { mutableStateOf("") }
                    var itemIdLocal by remember { mutableStateOf("") }

                    LaunchedEffect(itemId) {
                        getDataViewModel.retrieveData(
                            id = itemId,
                            context = context,

                            ) { data ->
                            itemName = data.Name
                            itemPrice = data.Price
                            itemIdLocal = data.id
                        }
                    }
                    if (getDataViewModel.inload.value) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(30.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    } else {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = itemName,
                                style = TextStyle(
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.Normal
                                )
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = "PreOrder",
                                style = TextStyle(
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Blue
                                ),
                                modifier = Modifier.clickable {
                                    navController.navigate(
                                        "detailScreen/${Uri.encode(itemName)}/${Uri.encode(itemPrice)}/${
                                            Uri.encode(
                                                itemId
                                            )
                                        }"
                                    )
                                }
                            )
                        }

                    }
                }
            }
        }
    }
}
