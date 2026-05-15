package com.karunada.kala.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.karunada.kala.data.ArtForm
import com.karunada.kala.data.Artisan
import com.karunada.kala.data.HeritageType
import com.karunada.kala.ui.components.ShimmerCard
import com.karunada.kala.ui.components.ShimmerItem
import com.karunada.kala.ui.viewmodel.HeritageViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HeritageViewModel = hiltViewModel()
) {
    val heritageSites by viewModel.heritageSites.collectAsState()
    val artForms by viewModel.artForms.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    val filteredSites = heritageSites.filter {
        it.name.contains(searchQuery, ignoreCase = true) || 
        it.artForm.contains(searchQuery, ignoreCase = true) ||
        it.district.contains(searchQuery, ignoreCase = true)
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {

        // Premium Header with Integrated Search
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "ಕರುನಾಡ ಕಲೆ",
                            style = MaterialTheme.typography.headlineMedium,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Discover Karnataka's Soul",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.8f)
                        )
                    }
                    IconButton(
                        onClick = { /* Notifications */ },
                        modifier = Modifier.background(Color.White.copy(0.2f), CircleShape)
                    ) {
                        Icon(Icons.Default.Notifications, contentDescription = null, tint = Color.White)
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Search Yakshagana, Bidriware...", color = Color.White.copy(0.6f)) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = Color.White) },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { searchQuery = "" }) {
                                Icon(Icons.Default.Close, contentDescription = "Clear", tint = Color.White)
                            }
                        }
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White.copy(0.5f),
                        cursorColor = Color.White,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                    singleLine = true
                )
            }
        }

        if (searchQuery.isEmpty()) {
            // Stories Section
            item {
                Column {
                    Text(
                        text = "Live Stories",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                    )
                    LazyRow(
                        modifier = Modifier.padding(bottom = 12.dp),
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(artForms) { art ->
                            StoryCircle(art = art) {
                                navController.navigate("story/${art.id}")
                            }
                        }
                    }
                }
            }

            // Quick action chips
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    QuickChip("🗺️ Map", onClick = { navController.navigate("map") })
                    QuickChip("🎭 Explorer", onClick = { navController.navigate("explorer") })
                    QuickChip("🛒 Market", onClick = { navController.navigate("shop") })
                }
            }

            // Art Forms horizontal scroll
            item {
                SectionHeader(title = "Featured Art Forms", actionLabel = "See All") {
                    navController.navigate("explorer")
                }
            }
            
            if (isRefreshing && artForms.isEmpty()) {
                item {
                    LazyRow(contentPadding = PaddingValues(horizontal = 16.dp)) {
                        items(3) {
                            ShimmerItem(modifier = Modifier.width(140.dp).height(150.dp).clip(RoundedCornerShape(12.dp)).padding(end = 12.dp))
                        }
                    }
                }
            } else {
                item {
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(artForms) { artForm ->
                            ArtFormChipCard(
                                name = artForm.name,
                                category = artForm.category,
                                imageUrl = artForm.imageUrl,
                                onClick = { navController.navigate("artform/${artForm.id}") }
                            )
                        }
                    }
                }
            }
        }

        // Artisan Directory
        item {
            SectionHeader(title = if (searchQuery.isEmpty()) "Artisans & Workshops" else "Search Results", actionLabel = null, onAction = null)
        }

        if (isRefreshing && heritageSites.isEmpty()) {
            items(5) {
                ShimmerCard()
            }
        } else if (filteredSites.isEmpty() && searchQuery.isNotEmpty()) {
            item {
                Box(modifier = Modifier.fillMaxWidth().padding(48.dp), contentAlignment = Alignment.Center) {
                    Text("No artisans found for '$searchQuery'", color = Color.Gray)
                }
            }
        } else {
            items(filteredSites) { site ->
                ArtisanCard(
                    artisan = Artisan(
                        id = site.id,
                        name = site.name,
                        artForm = site.artForm,
                        district = site.district,
                        imageUrl = site.imageUrl,
                        type = site.heritageType
                    ),
                    onClick = { navController.navigate("detail/${site.id}") }
                )
            }
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }
    }
}

@Composable
private fun StoryCircle(art: ArtForm, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(70.dp).clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .size(66.dp)
                .background(
                    Brush.sweepGradient(
                        listOf(Color(0xFFFFC107), Color(0xFFB71C1C), Color(0xFFFFC107))
                    ),
                    CircleShape
                )
                .padding(3.dp)
                .background(Color.White, CircleShape)
                .padding(2.dp)
        ) {
            AsyncImage(
                model = art.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = art.name,
            style = MaterialTheme.typography.labelSmall,
            maxLines = 1,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun QuickChip(label: String, onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        color = MaterialTheme.colorScheme.primaryContainer,
        modifier = Modifier
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
private fun SectionHeader(title: String, actionLabel: String?, onAction: (() -> Unit)?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        if (actionLabel != null && onAction != null) {
            TextButton(onClick = onAction) {
                Text(actionLabel, color = MaterialTheme.colorScheme.secondary)
            }
        }
    }
}

@Composable
private fun ArtFormChipCard(name: String, category: String, imageUrl: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(140.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        Column {
            AsyncImage(
                model = imageUrl,
                contentDescription = name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(text = name, fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyMedium)
                Text(text = category, style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.secondary)
            }
        }
    }
}

@Composable
private fun ArtisanCard(artisan: Artisan, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(3.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = artisan.imageUrl,
                contentDescription = artisan.name,
                modifier = Modifier
                    .size(72.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = artisan.name, fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium)
                Text(text = artisan.artForm,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.Place,
                        contentDescription = null,
                        modifier = Modifier.size(14.dp),
                        tint = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        text = artisan.district,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            // Badge for type
            Surface(
                color = if (artisan.type == HeritageType.WORKSHOP)
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.secondary,
                shape = RoundedCornerShape(6.dp)
            ) {
                Text(
                    text = if (artisan.type == HeritageType.WORKSHOP) "Workshop" else "Performance",
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp),
                    color = Color.White
                )
            }
        }
    }
}
