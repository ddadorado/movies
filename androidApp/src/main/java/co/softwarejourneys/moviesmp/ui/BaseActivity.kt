package co.softwarejourneys.moviesmp.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import co.softwarejourneys.moviesmp.ui.login.LoginScreen
import co.softwarejourneys.moviesmp.ui.start.StartScreen


sealed class Destination(val route: String) {
    object ScreenFirst : Destination("StartScreen")
    object ScreenSecond : Destination("LoginScreen")

}


class BaseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Destination.ScreenFirst.route) {
                composable(Destination.ScreenFirst.route) { StartScreen(navController) }
                composable(Destination.ScreenSecond.route) { LoginScreen(navController) }
            }

        }
        Log.d("ActivityLifecycle", "ActivityLifecycle:onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d("ActivityLifecycle", "ActivityLifecycle:onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("ActivityLifecycle", "ActivityLifecycle:onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("ActivityLifecycle", "ActivityLifecycle:onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("ActivityLifecycle", "ActivityLifecycle:onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ActivityLifecycle", "ActivityLifecycle:onDestroy")
    }
}

