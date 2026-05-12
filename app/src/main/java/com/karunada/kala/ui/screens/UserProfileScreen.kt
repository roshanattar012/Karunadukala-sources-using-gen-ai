package com.karunada.kala.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.karunada.kala.ui.viewmodels.AuthViewModel
import com.karunada.kala.ui.viewmodel.HeritageViewModel

@Composable
fun UserProfileScreen(
    navController: NavController, 
    viewModel: AuthViewModel = hiltViewModel(),
    heritageViewModel: HeritageViewModel = hiltViewModel()
) {
    val user by viewModel.currentUser.collectAsState()
    val savedItems by heritageViewModel.savedItems.collectAsState()
    var showLanguageDialog by remember { mutableStateOf(false) }

    if (showLanguageDialog) {
        AlertDialog(
            onDismissRequest = { showLanguageDialog = false },
            title = { Text("Select Language") },
            text = {
                Column {
                    TextButton(onClick = { showLanguageDialog = false }, modifier = Modifier.fillMaxWidth()) {
                        Text("English", textAlign = TextAlign.Start, modifier = Modifier.fillMaxWidth())
                    }
                    TextButton(onClick = { showLanguageDialog = false }, modifier = Modifier.fillMaxWidth()) {
                        Text("ಕನ್ನಡ (Kannada)", textAlign = TextAlign.Start, modifier = Modifier.fillMaxWidth())
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showLanguageDialog = false }) { Text("Close") }
            }
        )
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Profile Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(top = 48.dp, bottom = 32.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Surface(
                    shape = CircleShape,
                    shadowElevation = 8.dp,
                    modifier = Modifier.size(104.dp),
                    color = Color.White
                ) {
                    AsyncImage(
                        model = user?.photoUrl ?: "https://cdn-icons-png.flaticon.com/512/3135/3135715.png",
                        contentDescription = null,
                        modifier = Modifier.padding(2.dp).clip(CircleShape)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = user?.displayName ?: "Guest User",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = user?.email ?: "Log in to sync your progress",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(0.8f)
                )
            }
        }

        // Stats Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ProfileStat(label = "Saved", count = savedItems.size.toString())
            ProfileStat(label = "Bookings", count = "0")
            ProfileStat(label = "Points", count = "150")
        }

        Divider(modifier = Modifier.padding(horizontal = 16.dp))

        Column(modifier = Modifier.padding(16.dp)) {
            ProfileMenuItem(icon = Icons.Default.Favorite, label = "My Saved Heritage")
            ProfileMenuItem(icon = Icons.Default.DateRange, label = "My Workshop Bookings")
            ProfileMenuItem(icon = Icons.Default.Info, label = "Change Language (EN/KN)") {
                showLanguageDialog = true
            }
            ProfileMenuItem(icon = Icons.Default.Settings, label = "Account Settings")
            Spacer(modifier = Modifier.weight(1f))
            ProfileMenuItem(
                icon = Icons.Default.ExitToApp, 
                label = "Sign Out", 
                color = MaterialTheme.colorScheme.error
            ) {
                viewModel.signOut()
            }
        }
    }
}

@Composable
fun ProfileStat(label: String, count: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = count, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Text(text = label, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
    }
}

@Composable
fun ProfileMenuItem(icon: ImageVector, label: String, color: Color = Color.Unspecified, onClick: () -> Unit = {}) {
    Surface(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = null, tint = if (color == Color.Unspecified) MaterialTheme.colorScheme.primary else color)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = label, style = MaterialTheme.typography.bodyLarge, color = color)
            Spacer(modifier = Modifier.weight(1f))
            Icon(Icons.Default.KeyboardArrowRight, contentDescription = null, tint = Color.Gray)
        }
    }
}
