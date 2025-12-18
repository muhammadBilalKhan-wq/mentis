package com.socialnetwork.mentis

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.socialnetwork.mentis.navigation.Screen
import com.socialnetwork.mentis.presentation.ViewModelFactory
import com.socialnetwork.mentis.presentation.feed.FeedScreen
import com.socialnetwork.mentis.ui.theme.MentisTheme

class MainActivity : ComponentActivity() {

    private val viewModelFactory by lazy { ViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MentisTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screen.Feed.route) {
                        composable(Screen.Feed.route) {
                            FeedScreen(factory = viewModelFactory)
                        }
                    }
                }
            }
        }
    }
}
