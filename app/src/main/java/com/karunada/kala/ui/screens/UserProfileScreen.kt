package com.karunada.kala.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import kotlinx.coroutines.launch
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
    val bookings by heritageViewModel.bookings.collectAsState()
    
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    
    var showLanguageDialog by remember { mutableStateOf(false) }
    var showEditProfile by remember { mutableStateOf(false) }
    var newName by remember { mutableStateOf(user?.displayName ?: "") }

    if (showEditProfile) {
        AlertDialog(
            onDismissRequest = { showEditProfile = false },
            title = { Text("Edit Profile") },
            text = {
                OutlinedTextField(
                    value = newName,
                    onValueChange = { newName = it },
                    label = { Text("Full Name") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            confirmButton = {
                Button(onClick = {
                    viewModel.updateProfile(newName)
                    showEditProfile = false
                }) { Text("Save") }
            },
            dismissButton = {
                TextButton(onClick = { showEditProfile = false }) { Text("Cancel") }
            }
        )
    }

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

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
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
                        text = if (user?.displayName.isNullOrBlank()) "Karunada Artist" else user?.displayName!!,
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
                ProfileStat(label = "Bookings", count = bookings.size.toString())
                ProfileStat(label = "Points", count = (bookings.size * 50 + 150).toString())
            }

            Divider(modifier = Modifier.padding(horizontal = 16.dp))

            Column(modifier = Modifier.padding(16.dp)) {
                ProfileMenuItem(icon = Icons.Default.Favorite, label = "My Saved Heritage") {
                    navController.navigate("saved")
                }
                ProfileMenuItem(icon = Icons.Default.DateRange, label = "My Workshop Bookings") {
                    scope.launch {
                        snackbarHostState.showSnackbar("Workshops history coming soon!")
                    }
                }
                ProfileMenuItem(icon = Icons.Default.Edit, label = "Edit Profile Name") {
                    showEditProfile = true
                }
                ProfileMenuItem(icon = Icons.Default.Info, label = "Change Language (EN/KN)") {
                    showLanguageDialog = true
                }
                ProfileMenuItem(icon = Icons.Default.Settings, label = "Account Settings") {
                    scope.launch {
                        snackbarHostState.showSnackbar("Account settings will be available in the next update.")
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
                ProfileMenuItem(
                    icon = Icons.Default.ExitToApp, 
                    label = "Sign Out", 
                    color = MaterialTheme.colorScheme.error
                ) {
                    viewModel.signOut()
                }
            }
            Spacer(modifier = Modifier.height(80.dp))
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
