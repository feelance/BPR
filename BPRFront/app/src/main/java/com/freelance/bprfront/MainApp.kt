package com.freelance.bprfront

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.freelance.bprfront.adapter.WeekRoutineAdapter
import com.freelance.bprfront.databinding.ActivityNavigationMenuBinding
import com.freelance.bprfront.model.WeekRoutine

class MainApp : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNavigationMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navViewBottom

        val navController = findNavController(R.id.nav_host_fragment_activity_navigation_menu)


        navView.setupWithNavController(navController)
    }
}