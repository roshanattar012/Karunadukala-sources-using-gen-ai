package com.karunada.kala

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import androidx.hilt.navigation.compose.hiltViewModel
import com.karunada.kala.ui.screens.*
import com.karunada.kala.ui.theme.KarunadaKalaTheme
import com.karunada.kala.ui.viewmodels.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KarunadaKalaTheme {
                MainContainer()
            }
        }
    }
}

@Composable
fun MainContainer(authViewModel: AuthViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    val currentUser by authViewModel.currentUser.collectAsState()
    val startDestination = if (currentUser != null) "home" else "onboarding"

    Scaffold(
        bottomBar = {
            if (currentUser != null) {
                NavigationBar {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry?.destination?.route

                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Home, contentDescription = null) },
                        label = { Text("Home") },
                        selected = currentRoute == "home",
                        onClick = {
                            navController.navigate("home") {
                                popUpTo("home") { inclusive = true }
                            }
                        }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Star, contentDescription = null) },
                        label = { Text("Explorer") },
                        selected = currentRoute == "explorer",
                        onClick = {
                            navController.navigate("explorer") {
                                popUpTo("home")
                            }
                        }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Place, contentDescription = null) },
                        label = { Text("Map") },
                        selected = currentRoute == "map",
                        onClick = {
                            navController.navigate("map") {
                                popUpTo("home")
                            }
                        }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.List, contentDescription = null) },
                        label = { Text("Feed") },
                        selected = currentRoute == "feed",
                        onClick = {
                            navController.navigate("feed") {
                                popUpTo("home")
                            }
                        }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.ShoppingCart, contentDescription = null) },
                        label = { Text("Shop") },
                        selected = currentRoute == "shop",
                        onClick = {
                            navController.navigate("shop") {
                                popUpTo("home")
                            }
                        }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.AccountCircle, contentDescription = null) },
                        label = { Text("Profile") },
                        selected = currentRoute == "profile",
                        onClick = {
                            navController.navigate("profile") {
                                popUpTo("home")
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("onboarding") { OnboardingScreen(navController) }
            composable("login") { LoginScreen(navController) }
            composable("home") { HomeScreen(navController) }
            composable("explorer") { ArtFormExplorerScreen(navController) }
            composable("map") { MapScreen(navController) }
            composable("feed") { EventFeedScreen() }
            composable("shop") { MarketplaceScreen(navController) }
            composable("profile") { UserProfileScreen(navController) }
            composable(
                "detail/{siteId}",
                arguments = listOf(navArgument("siteId") { type = NavType.StringType })
            ) { backStackEntry ->
                DetailScreen(
                    siteId = backStackEntry.arguments?.getString("siteId") ?: "",
                    navController = navController
                )
            }
            composable(
                "artform/{artFormId}",
                arguments = listOf(navArgument("artFormId") { type = NavType.StringType })
            ) { backStackEntry ->
                ArtFormDetailScreen(
                    artFormId = backStackEntry.arguments?.getString("artFormId") ?: "",
                    navController = navController
                )
            }
            composable(
                "story/{artFormId}",
                arguments = listOf(navArgument("artFormId") { type = NavType.StringType })
            ) { backStackEntry ->
                StoryViewScreen(
                    artFormId = backStackEntry.arguments?.getString("artFormId") ?: "",
                    navController = navController
                )
            }
            composable(
                "workshop?artForm={artForm}",
                arguments = listOf(navArgument("artForm") {
                    type = NavType.StringType
                    defaultValue = ""
                })
            ) { backStackEntry ->
                WorkshopSignupScreen(
                    navController = navController,
                    preselectedArtForm = backStackEntry.arguments?.getString("artForm") ?: ""
                )
            }
        }
    }
}
