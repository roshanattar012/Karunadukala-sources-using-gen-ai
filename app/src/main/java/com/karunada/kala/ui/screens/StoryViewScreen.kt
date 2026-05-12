package com.karunada.kala.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.karunada.kala.data.MockData
import kotlinx.coroutines.delay

@Composable
fun StoryViewScreen(artFormId: String, navController: NavController) {
    val artForm = MockData.artForms.find { it.id == artFormId } ?: return
    
    var progress by remember { mutableStateOf(0f) }
    
    LaunchedEffect(Unit) {
        val duration = 5000L
        val interval = 50L
        val steps = duration / interval
        for (i in 1..steps) {
            delay(interval)
            progress = i.toFloat() / steps
        }
        navController.popBackStack()
    }

    Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        AsyncImage(
            model = artForm.imageUrl,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Gradient overlay for text readability
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(Color.Black.copy(0.6f), Color.Transparent, Color.Black.copy(0.6f))
                    )
                )
        )

        // Progress Bar
        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 48.dp)
                .height(4.dp)
                .clip(CircleShape),
            color = Color.White,
            trackColor = Color.White.copy(alpha = 0.3f)
        )

        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 64.dp, start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = artForm.imageUrl,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp).clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(artForm.name, color = Color.White, fontWeight = FontWeight.Bold)
                    Text(artForm.category, color = Color.White.copy(0.8f), style = MaterialTheme.typography.bodySmall)
                }
            }
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.White)
            }
        }

        // Bottom Info
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(24.dp)
        ) {
            Text(
                text = artForm.shortDesc,
                color = Color.White,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { 
                    navController.popBackStack()
                    navController.navigate("artform/${artForm.id}") 
                },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Explore Full History")
            }
        }
    }
}
