package com.example.finalassignment

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.api_project_aurelio.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val hostFragment = supportFragmentManager.findFragmentById(R.id.navhost) as NavHostFragment
        val navController = hostFragment.navController
        val navbar = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        navbar.setOnApplyWindowInsetsListener(null)
        navbar.setupWithNavController(navController)

        navbar.setOnItemSelectedListener { item ->
            if (item.itemId != navbar.selectedItemId) {
                val fragmentId = when(item.itemId) {
                    R.id.Login -> R.id.loginFrag
                    R.id.Dashboard -> R.id.dashboardFrag
                    else -> R.id.fragmentLogin
                }
                navController.navigate(fragmentId)
            }
            true
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
