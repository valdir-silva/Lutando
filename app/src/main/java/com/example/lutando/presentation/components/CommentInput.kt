package com.example.lutando.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Componente para adicionar novos comentários.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentInput(
    currentUser: String,
    onAddComment: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var commentText by remember { mutableStateOf("") }
    var isExpanded by remember { mutableStateOf(false) }
    
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Campo de texto
            OutlinedTextField(
                value = commentText,
                onValueChange = { 
                    commentText = it
                    isExpanded = it.isNotEmpty()
                },
                label = { Text("Adicionar comentário...") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 1,
                maxLines = 4,
                singleLine = false
            )
            
            // Botão de enviar (aparece quando há texto)
            if (isExpanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = {
                            if (commentText.trim().isNotEmpty()) {
                                onAddComment(commentText.trim())
                                commentText = ""
                                isExpanded = false
                            }
                        },
                        enabled = commentText.trim().isNotEmpty()
                    ) {
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = "Enviar comentário"
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Enviar")
                    }
                }
            }
        }
    }
} 