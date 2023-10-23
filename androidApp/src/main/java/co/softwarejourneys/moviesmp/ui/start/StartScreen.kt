package co.softwarejourneys.moviesmp.ui.start

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import co.softwarejourneys.moviesmp.ui.Destination
import co.softwarejourneys.movies.ui.theme.MoviesTheme

@Composable
fun StartScreen(navController: NavHostController) {
    val typography = MaterialTheme.typography
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp), // Add padding to the entire column
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome to Your App",
            fontSize = 30.sp,
            modifier = Modifier.padding(16.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth() // Button takes 100% width
                .weight(1f) // Button occupies all available vertical space
                .padding(bottom = 20.dp), // Margin on the right and bottom
            contentAlignment = Alignment.BottomCenter // Align the button to the bottom-right
        ) {
            Button(
                onClick = {navController.navigate(Destination.ScreenSecond.route)},
                modifier = Modifier
                    .fillMaxWidth() // Button takes 100% width of the Box
                    .padding(0.dp) // Remove padding inside the Button
            ) {
                Text(text = "Start")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FirstScreenPreview() {
    MoviesTheme {
        //FirstScreen {}
    }
}
