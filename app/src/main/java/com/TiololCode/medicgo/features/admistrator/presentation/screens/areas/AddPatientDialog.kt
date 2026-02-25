package com.TiololCode.medicgo.features.admistrator.presentation.screens.areas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import com.TiololCode.medicgo.features.admistrator.domain.entities.Area
import com.TiololCode.medicgo.features.admistrator.domain.entities.Patient

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

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
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
            }) { Text(text = "Guardar") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text(text = "Cancelar") }
        },
        text = {
            Column {
                OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Nombre") }, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(value = lastName, onValueChange = { lastName = it }, label = { Text("Apellidos") }, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(value = bloodType, onValueChange = { bloodType = it }, label = { Text("Tipo de sangre") }, modifier = Modifier.fillMaxWidth(0.5f))
                    OutlinedTextField(value = ageText, onValueChange = { ageText = it.filter { c -> c.isDigit() } }, label = { Text("Edad") }, modifier = Modifier.fillMaxWidth(0.5f))
                }
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(value = symptoms, onValueChange = { symptoms = it }, label = { Text("Síntomas") }, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(value = currentState, onValueChange = { currentState = it }, label = { Text("Estado actual") }, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(8.dp))

                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Área asignada", color = Color(0xFF1A1A2E), modifier = Modifier.padding(bottom = 4.dp))
                    Button(onClick = { areaDropdownExpanded = true }, modifier = Modifier.fillMaxWidth()) {
                        Text(text = if (areas.isNotEmpty()) areas.getOrNull(selectedAreaIndex)?.name ?: "Seleccionar área" else "Sin áreas")
                    }
                    DropdownMenu(expanded = areaDropdownExpanded, onDismissRequest = { areaDropdownExpanded = false }) {
                        areas.forEachIndexed { index, area ->
                            DropdownMenuItem(text = { Text(area.name) }, onClick = {
                                selectedAreaIndex = index
                                areaDropdownExpanded = false
                            })
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(value = assignedDoctorIdText, onValueChange = { assignedDoctorIdText = it.filter { c -> c.isDigit() } }, label = { Text("ID Doctor") }, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(value = assignedNurseIdText, onValueChange = { assignedNurseIdText = it.filter { c -> c.isDigit() } }, label = { Text("ID Enfermera") }, modifier = Modifier.fillMaxWidth())

                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(value = registrationDate, onValueChange = { registrationDate = it }, label = { Text("Fecha de registro (YYYY-MM-DD)") }, modifier = Modifier.fillMaxWidth())
            }
        }
    )
}
