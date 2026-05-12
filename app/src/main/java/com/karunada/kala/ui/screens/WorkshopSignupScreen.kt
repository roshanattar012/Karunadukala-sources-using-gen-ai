package com.karunada.kala.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.karunada.kala.data.MockData
import com.karunada.kala.ui.viewmodels.WorkshopViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkshopSignupScreen(
    navController: NavController,
    preselectedArtForm: String = "",
    viewModel: WorkshopViewModel = hiltViewModel()
) {
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var selectedArtForm by remember { mutableStateOf(preselectedArtForm) }
    var message by remember { mutableStateOf("") }
    var dropdownExpanded by remember { mutableStateOf(false) }

    val submitted by viewModel.submissionSuccess.collectAsState()
    val isSubmitting by viewModel.isSubmitting.collectAsState()

    // Validation states
    var nameError by remember { mutableStateOf(false) }
    var phoneError by remember { mutableStateOf(false) }
    var artFormError by remember { mutableStateOf(false) }

    if (submitted) {
        SuccessScreen(artForm = selectedArtForm, onBack = { navController.popBackStack() })
        return
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "Workshop Sign-up",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Register your interest in learning",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.85f)
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Name
            OutlinedTextField(
                value = name,
                onValueChange = { name = it; nameError = false },
                label = { Text("Full Name *") },
                modifier = Modifier.fillMaxWidth(),
                isError = nameError,
                supportingText = { if (nameError) Text("Name is required") },
                singleLine = true
            )

            // Phone
            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it; phoneError = false },
                label = { Text("Phone Number *") },
                modifier = Modifier.fillMaxWidth(),
                isError = phoneError,
                supportingText = { if (phoneError) Text("Valid phone number required") },
                singleLine = true
            )

            // Email
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email (optional)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            // Art Form Dropdown
            ExposedDropdownMenuBox(
                expanded = dropdownExpanded,
                onExpandedChange = { dropdownExpanded = !dropdownExpanded }
            ) {
                OutlinedTextField(
                    value = selectedArtForm,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Art Form *") },
                    trailingIcon = {
                        Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    isError = artFormError,
                    supportingText = { if (artFormError) Text("Please select an art form") }
                )
                ExposedDropdownMenu(
                    expanded = dropdownExpanded,
                    onDismissRequest = { dropdownExpanded = false }
                ) {
                    MockData.artFormNames.forEach { artFormName ->
                        DropdownMenuItem(
                            text = { Text(artFormName) },
                            onClick = {
                                selectedArtForm = artFormName
                                artFormError = false
                                dropdownExpanded = false
                            }
                        )
                    }
                }
            }

            // Message
            OutlinedTextField(
                value = message,
                onValueChange = { message = it },
                label = { Text("Message / Prior Experience (optional)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                maxLines = 4
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Info card
            Surface(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "ℹ️ The artisan will contact you within 2-3 days to confirm your workshop slot.",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(12.dp),
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            // Submit button
            Button(
                onClick = {
                    nameError = name.isBlank()
                    phoneError = phone.isBlank() || phone.length < 10
                    artFormError = selectedArtForm.isBlank()
                    if (!nameError && !phoneError && !artFormError) {
                        viewModel.registerForWorkshop(name, phone, email, selectedArtForm, message)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                enabled = !isSubmitting,
                shape = RoundedCornerShape(10.dp)
            ) {
                if (isSubmitting) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp), color = Color.White)
                } else {
                    Text("Submit Registration", style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }
}

@Composable
private fun SuccessScreen(artForm: String, onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("🎉", style = MaterialTheme.typography.displayLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Registration Successful!",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "You've registered interest for a $artForm workshop. The artisan will reach out to you soon.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
            Text("Back to App")
        }
    }
}
