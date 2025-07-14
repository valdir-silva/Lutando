package com.example.lutando.presentation.components

import android.Manifest
import android.media.MediaRecorder
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.lutando.R
import com.example.lutando.data.media.MediaManager
import com.example.lutando.data.media.PermissionManager
import kotlinx.coroutines.delay
import java.io.File

/**
 * Componente para gravação de áudio usando MediaRecorder.
 */
@Composable
fun AudioRecorder(
    onAudioRecorded: (Uri) -> Unit,
    onError: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val mediaManager = remember { MediaManager(context) }
    val permissionManager = remember { PermissionManager(context) }

    var isRecording by remember { mutableStateOf(false) }
    var recordingTime by remember { mutableStateOf(0L) }
    var mediaRecorder by remember { mutableStateOf<MediaRecorder?>(null) }
    var audioFile by remember { mutableStateOf<File?>(null) }

    // Launcher para selecionar arquivo de áudio da galeria
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let { onAudioRecorded(it) }
    }

    // Launcher para permissões
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val allGranted = permissions.values.all { it }
        if (allGranted) {
            startRecording(mediaManager, mediaRecorder) { recorder, file ->
                mediaRecorder = recorder
                audioFile = file
                isRecording = true
            }
        } else {
            onError("Permissão de microfone necessária para gravar áudio")
        }
    }

    // Timer para atualizar o tempo de gravação
    LaunchedEffect(isRecording) {
        while (isRecording) {
            delay(1000)
            recordingTime++
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Gravação de Áudio",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        // Indicador de tempo
        if (isRecording) {
            Text(
                text = formatTime(recordingTime),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }

        // Botões de ação
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Botão de gravação
            Button(
                onClick = {
                    if (isRecording) {
                        stopRecording(mediaRecorder, audioFile, onAudioRecorded, onError) {
                            isRecording = false
                            recordingTime = 0
                            mediaRecorder = null
                            audioFile = null
                        }
                    } else {
                        if (permissionManager.hasAudioPermission()) {
                            startRecording(mediaManager, mediaRecorder) { recorder, file ->
                                mediaRecorder = recorder
                                audioFile = file
                                isRecording = true
                            }
                        } else {
                            permissionLauncher.launch(arrayOf(Manifest.permission.RECORD_AUDIO))
                        }
                    }
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isRecording) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
                )
            ) {
                Icon(
                    imageVector = if (isRecording) Icons.Filled.Stop else Icons.Filled.Mic,
                    contentDescription = if (isRecording) "Parar Gravação" else "Iniciar Gravação",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(if (isRecording) "Parar" else "Gravar")
            }

            // Botão da galeria
            OutlinedButton(
                onClick = { galleryLauncher.launch("audio/*") },
                modifier = Modifier.weight(1f),
                enabled = !isRecording
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_audio_file),
                    contentDescription = "Selecionar da Galeria",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Galeria")
            }
        }

        // Texto de instrução
        Text(
            text = if (isRecording) "Gravação em andamento..." else "Grave um áudio ou selecione da galeria",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

private fun startRecording(
    mediaManager: MediaManager,
    currentRecorder: MediaRecorder?,
    onStarted: (MediaRecorder, File) -> Unit
) {
    try {
        // Parar gravação anterior se existir
        currentRecorder?.apply {
            try {
                stop()
                release()
            } catch (e: Exception) {
                // Ignorar erro se já estava parado
            }
        }

        // Criar arquivo de áudio
        val audioFile = mediaManager.createAudioFile()

        // Configurar MediaRecorder
        val recorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setOutputFile(audioFile.absolutePath)

            try {
                prepare()
                start()
                onStarted(this, audioFile)
            } catch (e: Exception) {
                release()
                throw e
            }
        }
    } catch (e: Exception) {
        throw RuntimeException("Erro ao iniciar gravação: ${e.message}")
    }
}

private fun stopRecording(
    recorder: MediaRecorder?,
    audioFile: File?,
    onSuccess: (Uri) -> Unit,
    onError: (String) -> Unit,
    onStopped: () -> Unit
) {
    try {
        recorder?.apply {
            stop()
            release()
        }

        audioFile?.let { file ->
            if (file.exists() && file.length() > 0) {
                val uri = Uri.fromFile(file)
                onSuccess(uri)
            } else {
                onError("Arquivo de áudio não foi criado corretamente")
            }
        }

        onStopped()
    } catch (e: Exception) {
        onError("Erro ao parar gravação: ${e.message}")
        onStopped()
    }
}

private fun formatTime(seconds: Long): String {
    val minutes = seconds / 60
    val remainingSeconds = seconds % 60
    return String.format("%02d:%02d", minutes, remainingSeconds)
} 