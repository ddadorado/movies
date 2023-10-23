package co.softwarejourneys.moviesmp.ui.login

import co.softwarejourneys.moviesmp.ui.movies.MoviesActivity
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import android.content.Intent
import androidx.compose.ui.platform.LocalContext
import co.softwarejourneys.moviesmp.ui.Destination
import co.softwarejourneys.movies.ui.theme.MoviesTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController:NavHostController) {
    var username by remember { mutableStateOf("Username") }
    var password by remember { mutableStateOf("Password") }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Top app bar") }
            )
        }
    ){
        innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = username,
                    onValueChange = { username = it },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    label = { Text("username") },
                    keyboardActions = KeyboardActions(
                        onNext = {
                            // Handle next action
                        }
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )

                TextField(
                    value = password,
                    onValueChange = { password = it },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            // Handle done action
                        }
                    ),
                    label = { Text("password") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )

                Spacer(modifier = Modifier.height(80.dp)) // Add a fixed 100dp spacer


                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(0.dp), // Spacing between buttons
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = { navController.navigate(Destination.ScreenFirst.route) },
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .padding(8.dp)
                    ) {
                        Text(text = "Register")
                    }
                    val context = LocalContext.current
                    Button(
                        onClick = {
                            val intent = Intent(context, MoviesActivity::class.java)
                            context.startActivity(intent)
                        },
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .padding(8.dp)
                    ) {
                        Text(text = "Login")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SecondScreenPreview() {
    MoviesTheme {
        //SecondScreen({}, {})
    }
}
