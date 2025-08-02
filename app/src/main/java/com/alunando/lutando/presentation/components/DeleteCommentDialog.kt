package com.alunando.lutando.presentation.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.alunando.lutando.domain.model.Comment

/**
 * Diálogo de confirmação para deletar um comentário.
 */
@Composable
fun DeleteCommentDialog(
    comment: Comment,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Deletar Comentário") },
        text = { 
            Text("Tem certeza que deseja deletar este comentário? Esta ação não pode ser desfeita.")
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm()
                    onDismiss()
                }
            ) {
                Text("Deletar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
} 