package com.example.lutando.domain.repository

import android.net.Uri
import com.example.lutando.domain.model.MediaType

/**
 * Interface do repositório de mídia.
 */
interface MediaRepository {

    /**
     * Salva um arquivo de mídia permanentemente.
     *
     * @param sourceUri URI do arquivo de origem
     * @param mediaType Tipo de mídia
     * @return Result contendo o caminho do arquivo salvo ou erro
     */
    suspend fun saveMediaFile(sourceUri: Uri, mediaType: MediaType): Result<String>

    /**
     * Exclui um arquivo de mídia.
     *
     * @param filePath Caminho do arquivo a ser excluído
     * @return Result contendo true se excluído com sucesso ou erro
     */
    suspend fun deleteMediaFile(filePath: String): Result<Boolean>

    /**
     * Obtém o URI de um arquivo de mídia.
     *
     * @param filePath Caminho do arquivo
     * @return Result contendo o URI do arquivo ou null se não existir
     */
    suspend fun getMediaUri(filePath: String): Result<Uri?>

    /**
     * Verifica se um arquivo existe.
     *
     * @param filePath Caminho do arquivo
     * @return Result contendo true se o arquivo existe
     */
    suspend fun fileExists(filePath: String): Result<Boolean>

    /**
     * Verifica se as permissões necessárias para um tipo de mídia estão concedidas.
     *
     * @param mediaType Tipo de mídia
     * @return true se todas as permissões necessárias estão concedidas
     */
    fun hasRequiredPermissions(mediaType: MediaType): Boolean

    /**
     * Obtém as permissões necessárias para um tipo de mídia.
     *
     * @param mediaType Tipo de mídia
     * @return Array com as permissões necessárias
     */
    fun getRequiredPermissions(mediaType: MediaType): Array<String>
} 