package com.socialnetwork.mentis.navigation

sealed class Screen(val route: String) {
    object Feed : Screen("feed")
}