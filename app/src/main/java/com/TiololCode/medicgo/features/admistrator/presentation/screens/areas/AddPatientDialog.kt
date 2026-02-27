package com.TiololCode.medicgo.features.admistrator.presentation.screens.areas

import androidx.compose.foundation.background
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
import com.TiololCode.medicgo.features.admistrator.domain.entities.HealthProfessional
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
    doctors: List<HealthProfessional> = emptyList(),
    nurses: List<HealthProfessional> = emptyList(),
    initialAreaIndex: Int = 0,
    onDismiss: () -> Unit,
    onSave: (Patient) -> Unit
) {
    var name by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var bloodType by rememberSaveable { mutableStateOf("") }
    var symptoms by rememberSaveable { mutableStateOf("") }
    var notaCondicion by rememberSaveable { mutableStateOf("") }
    var selectedEstadoIndex by rememberSaveable { mutableStateOf(-1) }
    var ageText by rememberSaveable { mutableStateOf("") }
    var registrationDate by rememberSaveable { mutableStateOf("") }

    var selectedAreaIndex by rememberSaveable { mutableStateOf(initialAreaIndex) }
    var selectedDoctorIndex by rememberSaveable { mutableStateOf(-1) }
    var selectedNurseIndex by rememberSaveable { mutableStateOf(-1) }

    var areaDropdownExpanded by remember { mutableStateOf(false) }
    var doctorDropdownExpanded by remember { mutableStateOf(false) }
    var nurseDropdownExpanded by remember { mutableStateOf(false) }
    var estadoDropdownExpanded by remember { mutableStateOf(false) }

    val estadosActuales = listOf("Estable", "Crítico", "Observación")

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
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Estado actual",
                            color = SubtitleText,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(bottom = 6.dp)
                        )
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Button(
                                onClick = { estadoDropdownExpanded = true },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(52.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = when (estadosActuales.getOrNull(selectedEstadoIndex)) {
                                        "Estable" -> Color(0xFF4CAF50)
                                        "Cr\u00edtico" -> Color(0xFFE53935)
                                        "Observaci\u00f3n" -> Color(0xFFFF9800)
                                        else -> Color(0xFFE0E0E0)
                                    }
                                ),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text(
                                    text = estadosActuales.getOrNull(selectedEstadoIndex) ?: "Seleccionar estado",
                                    color = if (selectedEstadoIndex >= 0) Color.White else Color(0xFF888888),
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            DropdownMenu(
                                expanded = estadoDropdownExpanded,
                                onDismissRequest = { estadoDropdownExpanded = false }
                            ) {
                                estadosActuales.forEachIndexed { index, estado ->
                                    DropdownMenuItem(
                                        text = {
                                            Text(
                                                text = estado,
                                                color = when (estado) {
                                                    "Estable" -> Color(0xFF4CAF50)
                                                    "Cr\u00edtico" -> Color(0xFFE53935)
                                                    "Observaci\u00f3n" -> Color(0xFFFF9800)
                                                    else -> PrimaryText
                                                },
                                                fontWeight = FontWeight.SemiBold
                                            )
                                        },
                                        onClick = {
                                            selectedEstadoIndex = index
                                            estadoDropdownExpanded = false
                                        }
                                    )
                                }
                            }
                        }
                    }

                    // Nota de condición (opcional)
                    OutlinedTextField(
                        value = notaCondicion,
                        onValueChange = { notaCondicion = it },
                        label = { Text("Nota de condición (opcional)") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = textFieldColors,
                        minLines = 2,
                        maxLines = 3
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

                    // Doctor asignado
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Doctor asignado",
                            color = SubtitleText,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(bottom = 6.dp)
                        )
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Button(
                                onClick = { doctorDropdownExpanded = true },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(52.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (selectedDoctorIndex >= 0) Color(0xFF1A1A2E) else Color(0xFFE0E0E0)
                                ),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text(
                                    text = doctors.getOrNull(selectedDoctorIndex)?.let {
                                        if (it.name.isNotEmpty()) it.name else "Doctor #${selectedDoctorIndex + 1}"
                                    } ?: "Seleccionar doctor",
                                    color = if (selectedDoctorIndex >= 0) Color.White else Color(0xFF888888),
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            DropdownMenu(
                                expanded = doctorDropdownExpanded,
                                onDismissRequest = { doctorDropdownExpanded = false }
                            ) {
                                if (doctors.isEmpty()) {
                                    DropdownMenuItem(
                                        text = { Text("No hay doctores disponibles", color = Color(0xFF888888)) },
                                        onClick = { doctorDropdownExpanded = false }
                                    )
                                } else {
                                    doctors.forEachIndexed { index, doctor ->
                                        DropdownMenuItem(
                                            text = {
                                                Column {
                                                    Text(
                                                        text = doctor.name.ifEmpty { "Doctor ${index + 1}" },
                                                        fontWeight = FontWeight.SemiBold,
                                                        fontSize = 14.sp
                                                    )
                                                    if (doctor.specialty.isNotEmpty()) {
                                                        Text(
                                                            text = doctor.specialty,
                                                            fontSize = 12.sp,
                                                            color = Color(0xFF2979FF)
                                                        )
                                                    }
                                                    if (doctor.licenseNumber.isNotEmpty()) {
                                                        Text(
                                                            text = "Lic: ${doctor.licenseNumber}",
                                                            fontSize = 11.sp,
                                                            color = Color(0xFF888888)
                                                        )
                                                    }
                                                }
                                            },
                                            onClick = {
                                                selectedDoctorIndex = index
                                                doctorDropdownExpanded = false
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // Enfermera asignada
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Enfermera asignada",
                            color = SubtitleText,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(bottom = 6.dp)
                        )
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Button(
                                onClick = { nurseDropdownExpanded = true },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(52.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (selectedNurseIndex >= 0) Color(0xFFE91E8C) else Color(0xFFE0E0E0)
                                ),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text(
                                    text = nurses.getOrNull(selectedNurseIndex)?.let {
                                        if (it.name.isNotEmpty()) it.name else "Enfermera #${selectedNurseIndex + 1}"
                                    } ?: "Seleccionar enfermera",
                                    color = if (selectedNurseIndex >= 0) Color.White else Color(0xFF888888),
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            DropdownMenu(
                                expanded = nurseDropdownExpanded,
                                onDismissRequest = { nurseDropdownExpanded = false }
                            ) {
                                if (nurses.isEmpty()) {
                                    DropdownMenuItem(
                                        text = { Text("No hay enfermeras disponibles", color = Color(0xFF888888)) },
                                        onClick = { nurseDropdownExpanded = false }
                                    )
                                } else {
                                    nurses.forEachIndexed { index, nurse ->
                                        DropdownMenuItem(
                                            text = {
                                                Column {
                                                    Text(
                                                        text = nurse.name.ifEmpty { "Enfermera ${index + 1}" },
                                                        fontWeight = FontWeight.SemiBold,
                                                        fontSize = 14.sp
                                                    )
                                                    if (nurse.licenseNumber.isNotEmpty()) {
                                                        Text(
                                                            text = "Lic: ${nurse.licenseNumber}",
                                                            fontSize = 11.sp,
                                                            color = Color(0xFF888888)
                                                        )
                                                    }
                                                }
                                            },
                                            onClick = {
                                                selectedNurseIndex = index
                                                nurseDropdownExpanded = false
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }

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
                            val doctorId = doctors.getOrNull(selectedDoctorIndex)?.id.orEmpty()
                            val nurseId = nurses.getOrNull(selectedNurseIndex)?.id.orEmpty()
                            val nurseName = nurses.getOrNull(selectedNurseIndex)?.name.orEmpty()
                            val areaNombre = if (areas.isNotEmpty()) areas.getOrNull(selectedAreaIndex)?.name.orEmpty() else ""
                            val estado = estadosActuales.getOrNull(selectedEstadoIndex).orEmpty()
                            val patient = Patient(
                                id = "",
                                name = name.trim(),
                                lastName = lastName.trim(),
                                bloodType = bloodType.trim(),
                                symptoms = symptoms.trim(),
                                currentState = estado,
                                age = age,
                                registrationDate = registrationDate.trim(),
                                areaNombre = areaNombre,
                                notaCondicion = notaCondicion.trim(),
                                assignedDoctor = doctorId,
                                assignedNurse = nurseId,
                                nombreEnfermero = nurseName
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