package com.example.navegation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.navegation.ui.theme.NavegationTheme
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}


@Composable
fun App() {
    val controller = rememberNavController()
    NavHost(controller, startDestination = HomeRoute) {
        composable<HomeRoute> { HomeScreen(controller) }
        composable<CityRoute> { CityScreen() }
    }
}

@Serializable
object HomeRoute

@Composable
fun HomeScreen(controller: NavController) {

    Column {
        Text("Home")
        Button(onClick = {controller.navigate(CityRoute)}) {
            Text("City")
        }
    }
}

@Serializable
object CityRoute

@Composable
fun CityScreen() {
    Text("Barcelona")
}