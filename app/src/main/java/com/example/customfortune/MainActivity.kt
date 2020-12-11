package com.example.customfortune

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.customfortune.database.FortuneDatabase
import com.example.customfortune.databinding.ActivityMainBinding
import com.example.customfortune.utils.DependencyService
import com.example.customfortune.viewmodels.CardViewModel
import com.example.customfortune.viewmodels.ColorViewModel
import com.example.customfortune.viewmodels.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration : AppBarConfiguration
    private val applicationScope = CoroutineScope(SupervisorJob())
    lateinit var cardViewModel: CardViewModel
    lateinit var colorViewModel: ColorViewModel
    lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupViewModels()
        setupNavigation()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment)

        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

    private fun setupNavigation() {
        val drawerLayout = binding.drawerLayout
        val navController = this.findNavController(R.id.nav_host_fragment)

        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)

        navController.addOnDestinationChangedListener { nc: NavController, nd: NavDestination, bundle: Bundle? ->
            if (nd.id == nc.graph.startDestination) {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            } else {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
        }

        NavigationUI.setupWithNavController(binding.navView, navController)
    }

    private fun setupViewModels() {
        val database = FortuneDatabase.getDatabase(this, applicationScope)
        cardViewModel = DependencyService.serveCardViewModel(database,this)
        colorViewModel = DependencyService.serveColorViewModel(database, this)
        userViewModel = DependencyService.serveUserViewModel(database, this)
    }
}