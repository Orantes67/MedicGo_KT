package com.TiololCode.medicgo.features.register.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.ui.res.painterResource
import com.TiololCode.medicgo.R
import com.TiololCode.medicgo.features.register.presentation.components.EmailInputField
import com.TiololCode.medicgo.features.register.presentation.components.FullNameInputField
import com.TiololCode.medicgo.features.register.presentation.components.LicenseNumberInputField
import com.TiololCode.medicgo.features.register.presentation.components.RegisterPasswordInputField
import com.TiololCode.medicgo.features.register.presentation.components.RoleDropdownField
import com.TiololCode.medicgo.features.register.presentation.components.SpecialtyDropdownField
import com.TiololCode.medicgo.features.register.presentation.viewmodels.RegisterViewModel
import androidx.compose.foundation.layout.safeDrawingPadding

private val ScreenBackground = Color(0xFF407899)
private val AccessButtonColor = Color(0xFF366681)
private val RegisterButtonBorder = Color(0xFF004A99)
private val RegisterButtonTextColor = Color(0xFF4888AD)

@Composable
fun RegisterScreen(
    onNavigateBack: () -> Unit = {},
    onRegisterSuccess: () -> Unit = {},
    onNavigateToLogin: () -> Unit = {},
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.registerResult) {
        if (uiState.registerResult != null) {
            onRegisterSuccess()
            viewModel.clearRegisterResult()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .background(ScreenBackground)
    ) {
        // Back button
        IconButton(
            onClick = onNavigateBack,
            modifier = Modifier
                .padding(top = 16.dp, start = 8.dp)
                .align(Alignment.TopStart)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 56.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            androidx.compose.material3.Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                shape = RoundedCornerShape(20.dp),
                colors = androidx.compose.material3.CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = androidx.compose.material3.CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 28.dp, vertical = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Brain / Logo image
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.brain),
                            contentDescription = "Brain Logo",
                            modifier = Modifier.size(100.dp),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Register MedicGo",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1A1A2E)
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Verify your credentials to start managing\nyour practice efficiently.",
                        fontSize = 13.sp,
                        color = Color(0xFF6B7280),
                        textAlign = TextAlign.Center,
                        lineHeight = 18.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Blue divider line
                    Box(
                        modifier = Modifier
                            .width(48.dp)
                            .height(3.dp)
                            .background(
                                color = AccessButtonColor,
                                shape = RoundedCornerShape(50)
                            )
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Full Name
                    FullNameInputField(
                        value = uiState.name,
                        onValueChange = viewModel::onNameChange
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Medical License Number
                    LicenseNumberInputField(
                        value = uiState.licenseNumber,
                        onValueChange = viewModel::onLicenseNumberChange
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Specialty dropdown
                    SpecialtyDropdownField(
                        value = uiState.specialty,
                        expanded = uiState.specialtyExpanded,
                        onExpandedChange = viewModel::onSpecialtyExpandedChange,
                        onSpecialtySelected = viewModel::onSpecialtyChange
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Email
                    EmailInputField(
                        value = uiState.email,
                        onValueChange = viewModel::onEmailChange
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Password
                    RegisterPasswordInputField(
                        value = uiState.password,
                        onValueChange = viewModel::onPasswordChange
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Role dropdown
                    RoleDropdownField(
                        value = uiState.role,
                        expanded = uiState.roleExpanded,
                        onExpandedChange = viewModel::onRoleExpandedChange,
                        onRoleSelected = viewModel::onRoleChange
                    )

                    // Error message
                    if (uiState.error != null) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = uiState.error!!,
                            color = MaterialTheme.colorScheme.error,
                            fontSize = 13.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Create Account button
                    Button(
                        onClick = viewModel::onRegister,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        enabled = !uiState.isLoading,
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AccessButtonColor,
                            contentColor = Color.White
                        )
                    ) {
                        if (uiState.isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(22.dp),
                                color = Color.White,
                                strokeWidth = 2.dp
                            )
                        } else {
                            Text(
                                text = "Create Account →",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Already have an account? Log In
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = Color(0xFF6B7280),
                                    fontSize = 13.sp
                                )
                            ) {
                                append("Already have an account? ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = RegisterButtonTextColor,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp
                                )
                            ) {
                                append("Log In")
                            }
                        },
                        modifier = Modifier
                            .padding(bottom = 4.dp)
                            .clickable { onNavigateToLogin() }
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

