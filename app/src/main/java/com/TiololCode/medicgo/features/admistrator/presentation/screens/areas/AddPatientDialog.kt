package com.TiololCode.medicgo.features.admistrator.presentation.screens.areas

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.TiololCode.medicgo.features.admistrator.domain.entities.Area
import com.TiololCode.medicgo.features.admistrator.domain.entities.Patient

private val PrimaryText = Color(0xFF1A1A2E)
private val SubtitleText = Color(0xFF888888)
private val AccentBlue = Color(0xFF2979FF)
private val CardBackground = Color.White
private val FieldBorder = Color(0xFFE0E0E0)
private val FieldBackground = Color(0xFFF9F9F9)

@Composable
fun AddPatientDialog(
    areas: List<Area>,
    initialAreaIndex: Int = 0,
    onDismiss: () -> Unit,
    onSave: (Patient) -> Unit
) {
    var name by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var bloodType by rememberSaveable { mutableStateOf("") }
    var symptoms by rememberSaveable { mutableStateOf("") }
    var currentState by rememberSaveable { mutableStateOf("") }
    var ageText by rememberSaveable { mutableStateOf("") }
    var registrationDate by rememberSaveable { mutableStateOf("") }
    var assignedDoctorIdText by rememberSaveable { mutableStateOf("") }
    var assignedNurseIdText by rememberSaveable { mutableStateOf("") }

    var selectedAreaIndex by rememberSaveable { mutableStateOf(initialAreaIndex) }
    var areaDropdownExpanded by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    val textFieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = AccentBlue,
        unfocusedBorderColor = FieldBorder,
        focusedLabelColor = AccentBlue,
        unfocusedLabelColor = SubtitleText,
        unfocusedContainerColor = FieldBackground,
        focusedContainerColor = CardBackground
    )

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 32.dp)
                .fillMaxWidth()
                .background(CardBackground, RoundedCornerShape(20.dp))
        ) {
            Column {
                // Header
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 16.dp)
                ) {
                    Text(
                        text = "Agregar Paciente",
                        color = PrimaryText,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Completa todos los datos del paciente",
                        color = SubtitleText,
                        fontSize = 13.sp
                    )
                }

                HorizontalDivider(color = Color(0xFFF0F0F0))

                // Scrollable form content
                Column(
                    modifier = Modifier
                        .weight(1f, fill = false)
                        .verticalScroll(scrollState)
                        .padding(horizontal = 20.dp, vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Nombre
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Nombre") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = textFieldColors,
                        singleLine = true
                    )

                    // Apellidos
                    OutlinedTextField(
                        value = lastName,
                        onValueChange = { lastName = it },
                        label = { Text("Apellidos") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = textFieldColors,
                        singleLine = true
                    )

                    // Tipo de sangre + Edad
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        OutlinedTextField(
                            value = bloodType,
                            onValueChange = { bloodType = it },
                            label = { Text("Tipo de sangre") },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp),
                            colors = textFieldColors,
                            singleLine = true
                        )
                        OutlinedTextField(
                            value = ageText,
                            onValueChange = { ageText = it.filter { c -> c.isDigit() } },
                            label = { Text("Edad") },
                            modifier = Modifier.width(90.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = textFieldColors,
                            singleLine = true
                        )
                    }

                    // Síntomas
                    OutlinedTextField(
                        value = symptoms,
                        onValueChange = { symptoms = it },
                        label = { Text("Síntomas") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = textFieldColors,
                        minLines = 2,
                        maxLines = 3
                    )

                    // Estado actual
                    OutlinedTextField(
                        value = currentState,
                        onValueChange = { currentState = it },
                        label = { Text("Estado actual") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = textFieldColors,
                        singleLine = true
                    )

                    // Área asignada
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Área asignada",
                            color = SubtitleText,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(bottom = 6.dp)
                        )
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Button(
                                onClick = { areaDropdownExpanded = true },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(52.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = AccentBlue
                                ),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text(
                                    text = if (areas.isNotEmpty())
                                        areas.getOrNull(selectedAreaIndex)?.name ?: "Seleccionar área"
                                    else "Sin áreas",
                                    color = Color.White,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            DropdownMenu(
                                expanded = areaDropdownExpanded,
                                onDismissRequest = { areaDropdownExpanded = false }
                            ) {
                                areas.forEachIndexed { index, area ->
                                    DropdownMenuItem(
                                        text = { Text(area.name) },
                                        onClick = {
                                            selectedAreaIndex = index
                                            areaDropdownExpanded = false
                                        }
                                    )
                                }
                            }
                        }
                    }

                    // ID Doctor
                    OutlinedTextField(
                        value = assignedDoctorIdText,
                        onValueChange = { assignedDoctorIdText = it.filter { c -> c.isDigit() } },
                        label = { Text("ID Doctor") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = textFieldColors,
                        singleLine = true
                    )

                    // ID Enfermera
                    OutlinedTextField(
                        value = assignedNurseIdText,
                        onValueChange = { assignedNurseIdText = it.filter { c -> c.isDigit() } },
                        label = { Text("ID Enfermera") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = textFieldColors,
                        singleLine = true
                    )

                    // Fecha de registro
                    OutlinedTextField(
                        value = registrationDate,
                        onValueChange = { registrationDate = it },
                        label = { Text("Fecha de registro (YYYY-MM-DD)") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = textFieldColors,
                        singleLine = true,
                        placeholder = { Text("2024-01-01", color = Color(0xFFBBBBBB)) }
                    )
                }

                HorizontalDivider(color = Color(0xFFF0F0F0))

                // Action buttons
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 14.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(
                        onClick = onDismiss,
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = "Cancelar",
                            color = SubtitleText,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {
                            val age = ageText.toIntOrNull() ?: 0
                            val doctorId = assignedDoctorIdText.toLongOrNull() ?: 0L
                            val nurseId = assignedNurseIdText.toLongOrNull() ?: 0L
                            val areaId = if (areas.isNotEmpty()) areas.getOrNull(selectedAreaIndex)?.id ?: 0L else 0L
                            val patient = Patient(
                                id = 0L,
                                name = name.trim(),
                                lastName = lastName.trim(),
                                bloodType = bloodType.trim(),
                                symptoms = symptoms.trim(),
                                currentState = currentState.trim(),
                                age = age,
                                registrationDate = registrationDate.trim(),
                                areaId = areaId,
                                assignedDoctor = doctorId,
                                assignedNurse = nurseId
                            )
                            onSave(patient)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryText),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = "Guardar",
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}