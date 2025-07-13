package com.example.lutando.data.media

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat

/**
 * Gerenciador de permissões para funcionalidades de mídia.
 */
class PermissionManager(private val context: Context) {
    
    /**
     * Verifica se as permissões de câmera estão concedidas.
     */
    fun hasCameraPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }
    
    /**
     * Verifica se as permissões de áudio estão concedidas.
     */
    fun hasAudioPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.RECORD_AUDIO
        ) == PackageManager.PERMISSION_GRANTED
    }
    
    /**
     * Verifica se as permissões de armazenamento estão concedidas.
     */
    fun hasStoragePermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // Android 13+ usa permissões granulares
            hasMediaPermissions()
        } else {
            // Android 12 e abaixo usa READ_EXTERNAL_STORAGE
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        }
    }
    
    /**
     * Verifica se as permissões de mídia granulares estão concedidas (Android 13+).
     */
    private fun hasMediaPermissions(): Boolean {
        val imagePermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.READ_MEDIA_IMAGES
        ) == PackageManager.PERMISSION_GRANTED
        
        val videoPermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.READ_MEDIA_VIDEO
        ) == PackageManager.PERMISSION_GRANTED
        
        val audioPermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.READ_MEDIA_AUDIO
        ) == PackageManager.PERMISSION_GRANTED
        
        return imagePermission && videoPermission && audioPermission
    }
    
    /**
     * Obtém as permissões necessárias para um tipo de mídia específico.
     */
    fun getRequiredPermissions(mediaType: com.example.lutando.domain.model.MediaType): Array<String> {
        val permissions = mutableListOf<String>()
        
        when (mediaType) {
            com.example.lutando.domain.model.MediaType.PHOTO -> {
                permissions.add(Manifest.permission.CAMERA)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    permissions.add(Manifest.permission.READ_MEDIA_IMAGES)
                } else {
                    permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE)
                }
            }
            com.example.lutando.domain.model.MediaType.VIDEO -> {
                permissions.add(Manifest.permission.CAMERA)
                permissions.add(Manifest.permission.RECORD_AUDIO)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    permissions.add(Manifest.permission.READ_MEDIA_VIDEO)
                } else {
                    permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE)
                }
            }
            com.example.lutando.domain.model.MediaType.AUDIO -> {
                permissions.add(Manifest.permission.RECORD_AUDIO)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    permissions.add(Manifest.permission.READ_MEDIA_AUDIO)
                } else {
                    permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE)
                }
            }
        }
        
        return permissions.toTypedArray()
    }
    
    /**
     * Verifica se todas as permissões necessárias para um tipo de mídia estão concedidas.
     */
    fun hasAllRequiredPermissions(mediaType: com.example.lutando.domain.model.MediaType): Boolean {
        val requiredPermissions = getRequiredPermissions(mediaType)
        return requiredPermissions.all { permission ->
            ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
        }
    }
} 