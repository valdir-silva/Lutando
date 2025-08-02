package com.alunando.lutando.data.local

import com.alunando.lutando.domain.model.MediaFile
import com.alunando.lutando.domain.model.MediaType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ConvertersTest {

    private lateinit var converters: Converters

    @Before
    fun setup() {
        converters = Converters()
    }

    @Test
    fun `deve converter lista de MediaFile para JSON e vice-versa`() {
        // Given
        val mediaFiles = listOf(
            MediaFile(
                id = "media_1",
                type = MediaType.VIDEO,
                uri = "content://media/video.mp4",
                description = "Vídeo da técnica"
            ),
            MediaFile(
                id = "media_2",
                type = MediaType.PHOTO,
                uri = "content://media/photo.jpg",
                description = "Foto da técnica"
            )
        )

        // When
        val json = converters.fromMediaFileList(mediaFiles)
        val convertedList = converters.toMediaFileList(json)

        // Then
        assertEquals(2, convertedList.size)
        assertEquals("media_1", convertedList[0].id)
        assertEquals(MediaType.VIDEO, convertedList[0].type)
        assertEquals("content://media/video.mp4", convertedList[0].uri)
        assertEquals("Vídeo da técnica", convertedList[0].description)

        assertEquals("media_2", convertedList[1].id)
        assertEquals(MediaType.PHOTO, convertedList[1].type)
        assertEquals("content://media/photo.jpg", convertedList[1].uri)
        assertEquals("Foto da técnica", convertedList[1].description)
    }

    @Test
    fun `deve converter lista vazia`() {
        // Given
        val emptyList = emptyList<MediaFile>()

        // When
        val json = converters.fromMediaFileList(emptyList)
        val convertedList = converters.toMediaFileList(json)

        // Then
        assertTrue(convertedList.isEmpty())
    }

    @Test
    fun `deve converter lista nula para lista vazia`() {
        // Given
        val nullList: List<MediaFile>? = null

        // When
        val json = converters.fromMediaFileList(nullList)
        val convertedList = converters.toMediaFileList(json)

        // Then
        assertTrue(convertedList.isEmpty())
    }

    @Test
    fun `deve converter lista com um item`() {
        // Given
        val mediaFile = MediaFile(
            id = "media_single",
            type = MediaType.AUDIO,
            uri = "content://media/audio.mp3",
            description = "Áudio da técnica"
        )
        val mediaFiles = listOf(mediaFile)

        // When
        val json = converters.fromMediaFileList(mediaFiles)
        val convertedList = converters.toMediaFileList(json)

        // Then
        assertEquals(1, convertedList.size)
        assertEquals("media_single", convertedList[0].id)
        assertEquals(MediaType.AUDIO, convertedList[0].type)
        assertEquals("content://media/audio.mp3", convertedList[0].uri)
        assertEquals("Áudio da técnica", convertedList[0].description)
    }

    @Test
    fun `deve converter lista com múltiplos tipos de mídia`() {
        // Given
        val mediaFiles = listOf(
            MediaFile(id = "1", type = MediaType.VIDEO, uri = "video.mp4"),
            MediaFile(id = "2", type = MediaType.PHOTO, uri = "photo.jpg"),
            MediaFile(id = "3", type = MediaType.AUDIO, uri = "audio.mp3")
        )

        // When
        val json = converters.fromMediaFileList(mediaFiles)
        val convertedList = converters.toMediaFileList(json)

        // Then
        assertEquals(3, convertedList.size)
        assertEquals(MediaType.VIDEO, convertedList[0].type)
        assertEquals(MediaType.PHOTO, convertedList[1].type)
        assertEquals(MediaType.AUDIO, convertedList[2].type)
    }

    @Test
    fun `deve converter JSON vazio para lista vazia`() {
        // Given
        val emptyJson = "[]"

        // When
        val convertedList = converters.toMediaFileList(emptyJson)

        // Then
        assertTrue(convertedList.isEmpty())
    }

    @Test
    fun `deve converter JSON inválido para lista vazia`() {
        // Given
        val invalidJson = "invalid json"

        // When
        val convertedList = converters.toMediaFileList(invalidJson)

        // Then
        assertTrue(convertedList.isEmpty())
    }
} 