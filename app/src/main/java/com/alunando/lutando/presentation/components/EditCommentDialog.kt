package com.alunando.lutando.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alunando.lutando.domain.model.Comment

/**
 * Diálogo para editar um comentário existente.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditCommentDialog(
    comment: Comment,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    var editedText by remember { mutableStateOf(comment.text) }
    
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Editar Comentário") },
        text = {
            OutlinedTextField(
                value = editedText,
                onValueChange = { editedText = it },
                label = { Text("Comentário") },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 100.dp),
                minLines = 3,
                maxLines = 5
            )
        },
        confirmButton = {
            TextButton(
                onClick = {
                    if (editedText.trim().isNotEmpty()) {
                        onConfirm(editedText.trim())
                        onDismiss()
                    }
                },
                enabled = editedText.trim().isNotEmpty()
            ) {
                Text("Salvar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
} 