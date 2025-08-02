package com.alunando.lutando.data.repository

import android.net.Uri
import com.alunando.lutando.data.media.MediaManager
import com.alunando.lutando.data.media.PermissionManager
import com.alunando.lutando.domain.model.MediaType
import com.alunando.lutando.domain.repository.MediaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Implementação do repositório de mídia.
 */
class MediaRepositoryImpl(
    private val mediaManager: MediaManager,
    private val permissionManager: PermissionManager
) : MediaRepository {

    override suspend fun saveMediaFile(sourceUri: Uri, mediaType: MediaType): Result<String> {
        return withContext(Dispatchers.IO) {
            try {
                if (!permissionManager.hasAllRequiredPermissions(mediaType)) {
                    return@withContext Result.failure(
                        SecurityException("Permissões necessárias não concedidas")
                    )
                }

                val filePath = mediaManager.saveMediaFile(sourceUri, mediaType)
                Result.success(filePath)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    override suspend fun deleteMediaFile(filePath: String): Result<Boolean> {
        return withContext(Dispatchers.IO) {
            try {
                val deleted = mediaManager.deleteMediaFile(filePath)
                Result.success(deleted)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    override suspend fun getMediaUri(filePath: String): Result<Uri?> {
        return withContext(Dispatchers.IO) {
            try {
                val uri = mediaManager.getMediaUri(filePath)
                Result.success(uri)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    override suspend fun fileExists(filePath: String): Result<Boolean> {
        return withContext(Dispatchers.IO) {
            try {
                val exists = mediaManager.fileExists(filePath)
                Result.success(exists)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    override fun hasRequiredPermissions(mediaType: MediaType): Boolean {
        return permissionManager.hasAllRequiredPermissions(mediaType)
    }

    override fun getRequiredPermissions(mediaType: MediaType): Array<String> {
        return permissionManager.getRequiredPermissions(mediaType)
    }
} 