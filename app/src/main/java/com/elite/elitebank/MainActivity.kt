package com.elite.elitebank

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.elite.elitebank.navigation.NavigationManager
import com.elite.elitebank.security.SecurityUtils
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

    private var isCameraInUse by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            mainNavController = rememberNavController()
            navigationManager.setNavController(mainNavController)
            val navController = rememberNavController()
            EliteBankTheme {
                EliteSurface(Modifier.fillMaxSize()) {
                    EliteAppContent(controller = mainNavController)
                }// المحتوى الحالي للشاشة
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Cleanup camera detector
        SecurityUtils.release()
    }
}
