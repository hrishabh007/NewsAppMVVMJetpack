package com.app.newsappmvvmjetpack.presentation.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.newsappmvvmjetpack.R
import com.app.newsappmvvmjetpack.presentation.bottomnavigation.MainActivity
import dagger.hilt.android.AndroidEntryPoint

import kotlinx.coroutines.delay

@AndroidEntryPoint
class SplashActivity() : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
            ) {
                SplashScreen {
                    Log.e("apikey ", " >>>>> " + it.state.value.coins?.post?.apiKey)
                    if (it.state.value.coins?.post?.apiKey?.isNullOrEmpty() != true) {
                        var intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }

                }
            }
        }
    }
}

@Composable
fun SplashScreen(viewModel: SplashScreenViewModel = hiltViewModel(), onTimeout: (SplashScreenViewModel) -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {
        val state = viewModel.state.value
        if (state.isLoading == false) {
            Image(
                painter = painterResource(id = R.drawable.bg_splash_default),
                contentDescription = null, // set to null if not using accessibility
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                style = TextStyle(color = MaterialTheme.colorScheme.error),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        // CircularProgressIndicator(modifier = Modifier.align(Alignment.BottomCenter), color = Color.Red)
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 20.dp),
            color = Color.Red
        )
    }

    LaunchedEffect(true) {
        // Introduce a delay before navigating to the main content
        delay(3000) // Adjust the delay as needed

        // Navigate to the main content
        onTimeout(viewModel)
    }
}
