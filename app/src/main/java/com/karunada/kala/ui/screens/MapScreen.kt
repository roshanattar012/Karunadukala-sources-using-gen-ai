package com.karunada.kala.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.google.maps.android.compose.clustering.Clustering
import com.karunada.kala.data.HeritageSite
import com.karunada.kala.data.HeritageType
import com.karunada.kala.ui.models.SiteClusterItem
import com.karunada.kala.ui.viewmodels.MapViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    navController: NavController,
    viewModel: MapViewModel = hiltViewModel()
) {
    val sites by viewModel.sites.collectAsState()
    val karnatakaCenter = LatLng(15.3173, 75.7139)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(karnatakaCenter, 7f)
    }

    var selectedSite by remember { mutableStateOf<HeritageSite?>(null) }
    val sheetState = rememberModalBottomSheetState()
    var showSheet by remember { mutableStateOf(false) }

    val clusterItems = remember(sites) {
        sites.map { SiteClusterItem(it) }
    }

    Scaffold { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                properties = MapProperties(
                    isMyLocationEnabled = false
                ),
                uiSettings = MapUiSettings(zoomControlsEnabled = false)
            ) {
                Clustering(
                    items = clusterItems,
                    onClusterItemClick = { item ->
                        selectedSite = item.site
                        showSheet = true
                        true
                    },
                    clusterContent = { cluster ->
                        Surface(
                            modifier = Modifier.size(40.dp),
                            shape = CircleShape,
                            color = MaterialTheme.colorScheme.primary,
                            contentColor = Color.White,
                            shadowElevation = 4.dp
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = cluster.size.toString(),
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    },
                    clusterItemContent = { item ->
                        val (icon, color) = when (item.site.heritageType) {
                            HeritageType.WORKSHOP -> Icons.Default.Home to Color(0xFFFFC107)
                            HeritageType.PERFORMANCE -> Icons.Default.Star to Color(0xFFF44336)
                            else -> Icons.Default.Place to Color(0xFF2196F3)
                        }
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(color, CircleShape)
                                .padding(8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = icon,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                )
            }

            if (showSheet && selectedSite != null) {
                ModalBottomSheet(
                    onDismissRequest = { showSheet = false },
                    sheetState = sheetState
                ) {
                    SiteBottomSheetContent(
                        site = selectedSite!!,
                        onViewDetails = {
                            showSheet = false
                            navController.navigate("detail/${selectedSite!!.id}")
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun SiteBottomSheetContent(site: HeritageSite, onViewDetails: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .padding(bottom = 32.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = site.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(site.name, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Text(site.type, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.primary)
                Text(site.district, style = MaterialTheme.typography.bodySmall)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(site.description, maxLines = 2, style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onViewDetails,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("View Details")
        }
    }
}
