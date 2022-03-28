package com.dmm.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.dmm.todoapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(setOf(R.id.fragmentTodoList, R.id.fragmentTodoListDone, R.id.fragmentAbout))
        binding.materialToolbar.setupWithNavController(navController, appBarConfiguration)

        binding.bottomNavigation.setupWithNavController(navController)



        navController.addOnDestinationChangedListener{ controller, destination, arguments ->
            when(destination.id) {
                R.id.fragmentTodoList -> binding.materialToolbar.title = getString(R.string.fragment_list)
                R.id.fragmentTodoListDone -> binding.materialToolbar.title = getString(R.string.fragment_done)
                R.id.fragmentAddTodo -> binding.materialToolbar.title = getString(R.string.fragment_add)
                R.id.fragmentAbout -> binding.materialToolbar.title = getString(R.string.fragment_about)
                R.id.fragmentDetailTodo -> binding.materialToolbar.title = getString(R.string.fragment_detail)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() ||super.onSupportNavigateUp()
    }

}