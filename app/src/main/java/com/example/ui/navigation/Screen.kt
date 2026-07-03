package com.example.ui.navigation

sealed class Screen {
    object Dashboard : Screen()
    object List : Screen()
    object Stats : Screen()
    data class Detail(val positionId: String) : Screen()
}
