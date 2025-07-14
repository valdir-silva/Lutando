package com.example.lutando.data.media

import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import com.example.lutando.domain.model.MediaType
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Gerenciador responsável por captura e armazenamento de arquivos de mídia.
 */
class MediaManager(private val context: Context) {

    companion object {
        private const val AUTHORITY = "com.example.lutando.fileprovider"
        private const val MEDIA_FOLDER = "Lutando"
    }

    /**
     * Cria um arquivo temporário para captura de foto.
     */
    fun createPhotoFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("PHOTO_${timeStamp}_", ".jpg", storageDir)
    }

    /**
     * Cria um arquivo temporário para captura de vídeo.
     */
    fun createVideoFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_MOVIES)
        return File.createTempFile("VIDEO_${timeStamp}_", ".mp4", storageDir)
    }

    /**
     * Cria um arquivo temporário para gravação de áudio.
     */
    fun createAudioFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_MUSIC)
        return File.createTempFile("AUDIO_${timeStamp}_", ".mp3", storageDir)
    }

    /**
     * Converte um arquivo para URI usando FileProvider.
     */
    fun getUriForFile(file: File): Uri {
        return FileProvider.getUriForFile(context, AUTHORITY, file)
    }

    /**
     * Salva um arquivo de mídia permanentemente na pasta do aplicativo.
     */
    fun saveMediaFile(sourceUri: Uri, mediaType: MediaType): String {
        val fileName = generateFileName(mediaType)
        val destinationFile = getMediaFile(fileName, mediaType)

        context.contentResolver.openInputStream(sourceUri)?.use { input ->
            destinationFile.outputStream().use { output ->
                input.copyTo(output)
            }
        }

        return destinationFile.absolutePath
    }

    /**
     * Gera um nome de arquivo único baseado no tipo de mídia.
     */
    private fun generateFileName(mediaType: MediaType): String {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val extension = when (mediaType) {
            MediaType.PHOTO -> "jpg"
            MediaType.VIDEO -> "mp4"
            MediaType.AUDIO -> "mp3"
        }
        return "${mediaType.name}_${timeStamp}.$extension"
    }

    /**
     * Obtém o arquivo de mídia na pasta apropriada.
     */
    private fun getMediaFile(fileName: String, mediaType: MediaType): File {
        val directory = when (mediaType) {
            MediaType.PHOTO -> context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            MediaType.VIDEO -> context.getExternalFilesDir(Environment.DIRECTORY_MOVIES)
            MediaType.AUDIO -> context.getExternalFilesDir(Environment.DIRECTORY_MUSIC)
        }

        val mediaDir = File(directory, MEDIA_FOLDER)
        if (!mediaDir.exists()) {
            mediaDir.mkdirs()
        }

        return File(mediaDir, fileName)
    }

    /**
     * Verifica se um arquivo existe.
     */
    fun fileExists(filePath: String): Boolean {
        return File(filePath).exists()
    }

    /**
     * Exclui um arquivo de mídia.
     */
    fun deleteMediaFile(filePath: String): Boolean {
        val file = File(filePath)
        return if (file.exists()) {
            file.delete()
        } else {
            false
        }
    }

    /**
     * Obtém o URI de um arquivo de mídia.
     */
    fun getMediaUri(filePath: String): Uri? {
        val file = File(filePath)
        return if (file.exists()) {
            getUriForFile(file)
        } else {
            null
        }
    }
} 