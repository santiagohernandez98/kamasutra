package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.data.db.AppDatabase
import com.example.data.repository.PositionRepository
import com.example.ui.navigation.Screen
import com.example.ui.screens.DashboardScreen
import com.example.ui.screens.PositionDetailScreen
import com.example.ui.screens.PositionListScreen
import com.example.ui.screens.StatsScreen
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.viewmodel.KamaViewModel
import com.example.ui.viewmodel.KamaViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize SQLite Database via Room and construct Repository
        val database = AppDatabase.getDatabase(applicationContext)
        val repository = PositionRepository(database.positionProgressDao())

        // Initialize state holder ViewModel with factory
        val viewModel = ViewModelProvider(
            this,
            KamaViewModelFactory(repository)
        )[KamaViewModel::class.java]

        setContent {
            MyApplicationTheme {
                var currentScreen by remember { mutableStateOf<Screen>(Screen.Dashboard) }
                var lastMainScreen by remember { mutableStateOf<Screen>(Screen.Dashboard) }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        // Bottom Navigation bar is shown on primary screens (Dashboard, List, Stats)
                        if (currentScreen !is Screen.Detail) {
                            NavigationBar(
                                containerColor = MaterialTheme.colorScheme.surface,
                                contentColor = MaterialTheme.colorScheme.onSurface
                            ) {
                                NavigationBarItem(
                                    selected = currentScreen is Screen.Dashboard,
                                    onClick = {
                                        currentScreen = Screen.Dashboard
                                        lastMainScreen = Screen.Dashboard
                                    },
                                    label = { Text("Inicio") },
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Filled.Home,
                                            contentDescription = "Inicio"
                                        )
                                    },
                                    colors = NavigationBarItemDefaults.colors(
                                        selectedIconColor = MaterialTheme.colorScheme.primary,
                                        selectedTextColor = MaterialTheme.colorScheme.primary,
                                        indicatorColor = MaterialTheme.colorScheme.primaryContainer
                                    )
                                )

                                NavigationBarItem(
                                    selected = currentScreen is Screen.List,
                                    onClick = {
                                        currentScreen = Screen.List
                                        lastMainScreen = Screen.List
                                    },
                                    label = { Text("Posiciones") },
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Filled.List,
                                            contentDescription = "Posiciones"
                                        )
                                    },
                                    colors = NavigationBarItemDefaults.colors(
                                        selectedIconColor = MaterialTheme.colorScheme.primary,
                                        selectedTextColor = MaterialTheme.colorScheme.primary,
                                        indicatorColor = MaterialTheme.colorScheme.primaryContainer
                                    )
                                )

                                NavigationBarItem(
                                    selected = currentScreen is Screen.Stats,
                                    onClick = {
                                        currentScreen = Screen.Stats
                                        lastMainScreen = Screen.Stats
                                    },
                                    label = { Text("Bitácora") },
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Filled.MenuBook,
                                            contentDescription = "Bitácora"
                                        )
                                    },
                                    colors = NavigationBarItemDefaults.colors(
                                        selectedIconColor = MaterialTheme.colorScheme.primary,
                                        selectedTextColor = MaterialTheme.colorScheme.primary,
                                        indicatorColor = MaterialTheme.colorScheme.primaryContainer
                                    )
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        when (val screen = currentScreen) {
                            is Screen.Dashboard -> {
                                DashboardScreen(
                                    viewModel = viewModel,
                                    onNavigateToDetail = { id -> currentScreen = Screen.Detail(id) },
                                    onNavigateToList = {
                                        currentScreen = Screen.List
                                        lastMainScreen = Screen.List
                                    }
                                )
                            }
                            is Screen.List -> {
                                PositionListScreen(
                                    viewModel = viewModel,
                                    onNavigateToDetail = { id -> currentScreen = Screen.Detail(id) }
                                )
                            }
                            is Screen.Stats -> {
                                StatsScreen(
                                    viewModel = viewModel,
                                    onNavigateToDetail = { id -> currentScreen = Screen.Detail(id) }
                                )
                            }
                            is Screen.Detail -> {
                                PositionDetailScreen(
                                    positionId = screen.positionId,
                                    viewModel = viewModel,
                                    onBack = { currentScreen = lastMainScreen }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
