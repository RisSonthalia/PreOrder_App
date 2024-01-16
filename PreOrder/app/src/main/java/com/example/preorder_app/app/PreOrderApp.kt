package com.example.preorder_app.app

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.preorder_app.nav.Navhost

@Composable
fun PreOrderApp() {
    val systemDarkMode = isSystemInDarkTheme()
    Navhost()

    // Apply the MaterialTheme based on the system's theme

    }

