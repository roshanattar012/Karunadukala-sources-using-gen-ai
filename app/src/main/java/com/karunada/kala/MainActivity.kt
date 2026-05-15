package com.karunada.kala

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
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

                    val items = listOf(
                        "home" to Icons.Default.Home,
                        "explorer" to Icons.Default.Star,
                        "map" to Icons.Default.Place,
                        "feed" to Icons.Default.List,
                        "shop" to Icons.Default.ShoppingCart,
                        "profile" to Icons.Default.AccountCircle
                    )

                    items.forEach { (route, icon) ->
                        NavigationBarItem(
                            icon = { Icon(icon, contentDescription = null) },
                            label = { Text(route.replaceFirstChar { it.uppercase() }) },
                            selected = currentRoute == route,
                            onClick = {
                                if (currentRoute != route) {
                                    navController.navigate(route) {
                                        popUpTo("home") { saveState = true }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding),
            enterTransition = { fadeIn(animationSpec = tween(300)) + slideInHorizontally { it / 2 } },
            exitTransition = { fadeOut(animationSpec = tween(300)) + slideOutHorizontally { -it / 2 } },
            popEnterTransition = { fadeIn(animationSpec = tween(300)) + slideInHorizontally { -it / 2 } },
            popExitTransition = { fadeOut(animationSpec = tween(300)) + slideOutHorizontally { it / 2 } }
        ) {
            composable("onboarding") { OnboardingScreen(navController) }
            composable("login") { LoginScreen(navController) }
            composable("home") { HomeScreen(navController) }
            composable("explorer") { ArtFormExplorerScreen(navController) }
            composable("map") { MapScreen(navController) }
            composable("feed") { EventFeedScreen() }
            composable("shop") { MarketplaceScreen(navController) }
            composable("profile") { UserProfileScreen(navController) }
            composable("saved") { SavedHeritageScreen(navController) }
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
