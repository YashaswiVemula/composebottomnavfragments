package com.example.composebottomnavfragments

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.composebottomnavfragments.compose.BottomNavigationView
import com.example.composebottomnavfragments.databinding.ContentMainBinding
import com.example.composebottomnavfragments.ui.theme.ComposeBottomNavFragmentsTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = remember { mutableStateOf<NavController?>(null) }
            ComposeBottomNavFragmentsTheme {
                Scaffold(
                    bottomBar = {
                        navController.value?.let {
                            BottomNavigationView(navController = it)
                        }
                    },
                ) {
                    Box(modifier = androidx.compose.ui.Modifier.padding(it)) {
                        AndroidViewBinding(ContentMainBinding::inflate, update = {
                            navController.value = findNavController()
                        })
                    }
                }
            }
        }
    }

    private fun findNavController(): NavController {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        return navHostFragment.navController
    }
}
