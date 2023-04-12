package com.example.composebottomnavfragments.compose

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.composebottomnavfragments.R

sealed class Screen(val id: Int, val label: String) {
    object Fragment1 : Screen(R.id.nav_fragment1, "Fragment1")
    object Fragment2 : Screen(R.id.nav_fragment2, "Fragment2")
}

@Composable
fun BottomNavigationView(
    navController: NavController,
) {
    val items = listOf(
        Screen.Fragment1,
        Screen.Fragment2,
    )
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryFlow.collectAsState(initial = false)
        val currentDestination = (navBackStackEntry as? NavBackStackEntry)?.destination
        items.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
                label = { Text(screen.label) },
                selected = currentDestination?.hierarchy?.any { it.id == screen.id } == true,
                onClick = {
                    navController.navigate(screen.id)
                },
            )
        }
    }
}
