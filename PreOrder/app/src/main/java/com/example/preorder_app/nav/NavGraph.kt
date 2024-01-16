package com.example.preorder_app.nav

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.preorder_app.screens.DetailScreen
import com.example.preorder_app.screens.ForgotPassword
import com.example.preorder_app.screens.HomeScreen
import com.example.preorder_app.screens.LoginScreen
import com.example.preorder_app.screens.SignUpScreen
import com.example.preorder_app.viewmodels.GetData.GetDataViewModel
import com.example.preorder_app.viewmodels.Home.HomeViewModel
import com.example.preorder_app.viewmodels.Login.LoginViewModel
import com.example.preorder_app.viewmodels.SignUp.SignupViewModel

@Composable
fun Navhost() {
    val navController= rememberNavController()
    val signupViewModel:SignupViewModel= viewModel()
    val loginViewModel:LoginViewModel= viewModel()
    val homeViewModel:HomeViewModel= viewModel()
    val getDataViewModel:GetDataViewModel= viewModel()

    NavHost(
        navController = navController,
        startDestination = Screens.LoginScreen.name
    ) {
     composable(Screens.LoginScreen.name){
         LoginScreen(navController,signupViewModel,loginViewModel)
     }

     composable(Screens.SignUpScreen.name)   {
         SignUpScreen(navController,signupViewModel)
     }
        composable(Screens.HomeScreen.name)   {
            HomeScreen(navController, homeViewModel = homeViewModel,loginViewModel,signupViewModel, getDataViewModel = getDataViewModel)
        }
        composable(
            "detailScreen/{name}/{price}/{id}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("price") { type = NavType.StringType },
                navArgument("id") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            DetailScreen(navController = navController, homeViewModel = homeViewModel, signupViewModel = signupViewModel, loginViewModel = loginViewModel, navBackStackEntry = backStackEntry)
        }

        composable(Screens.ForgotPassWordScreen.name)   {
            ForgotPassword(navController = navController)
        }

    }
}