package com.app.jetpacknavigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) { ->
        composable(Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }

        composable(Screen.DetailScreen.route + "/{name}", arguments = listOf(
            navArgument(
                name = "name"
            ) {
                type = NavType.StringType
                defaultValue = "Naveen"
                nullable = true
            }
        )
        ) { entry ->
            entry.arguments?.getString("name")?.let { DetailScreen(name = it) }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    var text by remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {

        TextField(
            value = text, onValueChange = {
                text = it
            }, modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                navController.navigate(Screen.DetailScreen.withArgs(text.ifEmpty { null }))
            }, modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(4.dp)
                .align(Alignment.End)
        ) {
            Text(text = "click to navigate")
        }
    }
}

@Composable
fun DetailScreen(name: String) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Text(text = "Hello , $name")
    }
}