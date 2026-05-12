package com.karunada.kala.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

data class OnboardingPage(
    val title: String,
    val description: String,
    val icon: String,
    val color: Color
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(navController: NavController) {
    val pages = listOf(
        OnboardingPage(
            "Discover Heritage",
            "Explore the rich cultural tapestry of Karnataka, from Yakshagana to Bidriware.",
            "🎭",
            MaterialTheme.colorScheme.primary
        ),
        OnboardingPage(
            "Locate Artisans",
            "Find authentic makers and training centers on our interactive heritage map.",
            "📍",
            MaterialTheme.colorScheme.secondary
        ),
        OnboardingPage(
            "Preserve Art",
            "Join workshops and buy authentic products directly from the rural artists.",
            "🎨",
            MaterialTheme.colorScheme.tertiary
        )
    )

    val pagerState = rememberPagerState(pageCount = { pages.size })
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { position ->
            val page = pages[position]
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Surface(
                    modifier = Modifier.size(160.dp),
                    shape = CircleShape,
                    color = page.color.copy(alpha = 0.1f)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(text = page.icon, fontSize = 80.sp)
                    }
                }
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    text = page.title,
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = page.description,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                    lineHeight = 24.sp
                )
            }
        }

        // Bottom Controls
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(32.dp)
                .fillMaxWidth()
        ) {
            // Pager Indicator
            Row(
                modifier = Modifier.align(Alignment.CenterStart),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                repeat(pages.size) { iteration ->
                    val color = if (pagerState.currentPage == iteration) 
                        MaterialTheme.colorScheme.primary 
                    else 
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
                    Box(
                        modifier = Modifier
                            .size(if (pagerState.currentPage == iteration) 24.dp else 10.dp, 10.dp)
                            .clip(CircleShape)
                            .background(color)
                    )
                }
            }

            // Next/Get Started Button
            Button(
                onClick = {
                    if (pagerState.currentPage < pages.size - 1) {
                        scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
                    } else {
                        navController.navigate("login") {
                            popUpTo("onboarding") { inclusive = true }
                        }
                    }
                },
                modifier = Modifier.align(Alignment.CenterEnd),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(if (pagerState.currentPage == pages.size - 1) "Get Started" else "Next")
            }
        }
    }
}
