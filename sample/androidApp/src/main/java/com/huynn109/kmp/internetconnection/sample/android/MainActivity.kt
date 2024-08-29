package com.huynn109.kmp.internetconnection.sample.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.huynn109.kmp.internetconnection.sample.Greeting
import com.huynn109.kmp.internetconnection.sample.InternetStatus

class MainActivity : ComponentActivity() {
    var internetStatus: InternetStatus? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        internetStatus = InternetStatus(this)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GreetingView(Greeting().greet(), internetStatus)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        internetStatus?.start()
    }

    override fun onPause() {
        super.onPause()
        internetStatus?.stop()
    }
}

@Composable
fun GreetingView(text: String, internetStatus: InternetStatus?) {
    val status = internetStatus?.current?.collectAsState()
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = text)
        Text(text = "Connected Internet: ${status?.value}")
    }
}
