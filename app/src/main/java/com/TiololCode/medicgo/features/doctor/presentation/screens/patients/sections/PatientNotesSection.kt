package com.TiololCode.medicgo.features.doctor.presentation.screens.patients.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.TiololCode.medicgo.features.doctor.domain.entities.PatientNote

private val PrimaryText = Color(0xFF1A1A2E)
private val SubtitleText = Color(0xFF888888)
private val AccentBlue = Color(0xFF2979FF)
private val CardBackground = Color.White
private val ScreenBackground = Color(0xFFF5F7FA)
private val FieldBorder = Color(0xFFE0E0E0)
private val FieldBackground = Color(0xFFF9F9F9)

@Composable
fun PatientNotesSection(
    notes: List<PatientNote>,
    newNoteContent: String,
    isAddingNote: Boolean,
    onNoteContentChange: (String) -> Unit,
    onAddNoteClick: () -> Unit
) {
    val textFieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = AccentBlue,
        unfocusedBorderColor = FieldBorder,
        focusedLabelColor = AccentBlue,
        unfocusedLabelColor = SubtitleText,
        unfocusedContainerColor = FieldBackground,
        focusedContainerColor = CardBackground
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(CardBackground, RoundedCornerShape(16.dp))
            .border(1.dp, Color(0xFFEEEEEE), RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Text(text = "Notas del Paciente", fontSize = 15.sp, color = PrimaryText, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(12.dp))

        if (notes.isEmpty()) {
            EmptyNotesPlaceholder()
        } else {
            notes.forEach { note ->
                NoteItem(note = note)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = newNoteContent,
            onValueChange = onNoteContentChange,
            label = { Text("Agregar nueva nota") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = textFieldColors,
            maxLines = 3
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = onAddNoteClick,
            enabled = !isAddingNote,
            colors = ButtonDefaults.buttonColors(containerColor = AccentBlue),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                if (isAddingNote) "Agregando..." else "Agregar Nota",
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun EmptyNotesPlaceholder() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(ScreenBackground, RoundedCornerShape(10.dp))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Sin notas registradas", fontSize = 13.sp, color = SubtitleText)
    }
}

@Composable
internal fun NoteItem(note: PatientNote) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(ScreenBackground, RoundedCornerShape(10.dp))
            .border(1.dp, Color(0xFFEEEEEE), RoundedCornerShape(10.dp))
            .padding(12.dp)
    ) {
        Column {
            Text(text = note.content, fontSize = 13.sp, color = PrimaryText, lineHeight = 18.sp)
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = "${note.author} • ${note.date}", fontSize = 11.sp, color = SubtitleText)
        }
    }
}

