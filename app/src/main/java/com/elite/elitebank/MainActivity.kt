package com.elite.elitebank

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.elite.elitebank.navigation.NavigationManager
import com.elite.elitebank.ui.theme.EliteBankTheme
import com.elite.elitebank.ui.theme.component.background.EliteSurface
import com.elite.elitebank.ui.theme.component.bottombar.EliteAppContent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationManager: NavigationManager
    private lateinit var mainNavController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            mainNavController = rememberNavController()
            navigationManager.setNavController(mainNavController)

            EliteBankTheme {
                val navController = rememberNavController()
                EliteSurface (Modifier.fillMaxSize()) {
                    EliteAppContent(controller = mainNavController)
                }
            }
        }
    }
}
