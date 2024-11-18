package com.flare.analytics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flare.analytics.ui.theme.FLARE_AnalyticsTheme
import com.google.firebase.Firebase
import com.google.firebase.analytics.analytics
import com.google.firebase.analytics.logEvent

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FLARE_AnalyticsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(name = "Android", modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Greeting(modifier: Modifier = Modifier, name: String = "Android") {

    var somethingForCrash: String? = null

    Column(modifier = modifier.fillMaxSize()) {

        Button(onClick = {
            println(somethingForCrash!!.length)
        }, modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(top = 100.dp))
        { Text(text = "Null Pointer Exception") }

        Button(onClick = {
            throw RuntimeException("Flare Custom Crash")
        }, modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(top = 10.dp))
        { Text(text = "Custom Exception") }

        Button(onClick = {
            logEvent()
        }, modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 10.dp))
        { Text(text = "Track Login Click") }

    }
}

fun logEvent() {
    val firebaseAnalytics = Firebase.analytics
    firebaseAnalytics.logEvent("FlareLogin") {
        param("UserName", "Test User")
    }

}