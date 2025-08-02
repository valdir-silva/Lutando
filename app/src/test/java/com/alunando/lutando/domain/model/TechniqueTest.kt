package com.alunando.lutando.domain.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class TechniqueTest {

    @Test
    fun `deve criar Technique com valores padrão`() {
        // Given
        val name = "Kimura"
        val martialArtId = 1L

        // When
        val technique = Technique(name = name, martialArtId = martialArtId)

        // Then
        assertEquals(0L, technique.id)
        assertEquals(name, technique.name)
        assertEquals("", technique.description)
        assertEquals(martialArtId, technique.martialArtId)
        assertFalse(technique.hasVideo)
        assertFalse(technique.hasPhoto)
        assertFalse(technique.hasAudio)
        assertEquals("", technique.videoPath)
        assertEquals("", technique.photoPath)
        assertEquals("", technique.audioPath)
        assertEquals("", technique.createdAt)
        assertEquals("", technique.updatedAt)
    }

    @Test
    fun `deve criar Technique com todos os valores customizados`() {
        // Given
        val id = 1L
        val name = "Armbar"
        val description = "Chave de braço"
        val martialArtId = 2L
        val hasVideo = true
        val hasPhoto = true
        val hasAudio = false
        val videoPath = "/videos/armbar.mp4"
        val photoPath = "/photos/armbar.jpg"
        val audioPath = ""
        val createdAt = "2024-01-01"
        val updatedAt = "2024-01-02"

        // When
        val technique = Technique(
            id = id,
            name = name,
            description = description,
            martialArtId = martialArtId,
            hasVideo = hasVideo,
            hasPhoto = hasPhoto,
            hasAudio = hasAudio,
            videoPath = videoPath,
            photoPath = photoPath,
            audioPath = audioPath,
            createdAt = createdAt,
            updatedAt = updatedAt
        )

        // Then
        assertEquals(id, technique.id)
        assertEquals(name, technique.name)
        assertEquals(description, technique.description)
        assertEquals(martialArtId, technique.martialArtId)
        assertEquals(hasVideo, technique.hasVideo)
        assertEquals(hasPhoto, technique.hasPhoto)
        assertEquals(hasAudio, technique.hasAudio)
        assertEquals(videoPath, technique.videoPath)
        assertEquals(photoPath, technique.photoPath)
        assertEquals(audioPath, technique.audioPath)
        assertEquals(createdAt, technique.createdAt)
        assertEquals(updatedAt, technique.updatedAt)
    }

    @Test
    fun `deve copiar Technique com novos valores`() {
        // Given
        val original = Technique(name = "Triangle", martialArtId = 1L)

        // When
        val copy = original.copy(
            name = "Omoplata",
            description = "Chave de ombro",
            hasVideo = true,
            videoPath = "/videos/omoplata.mp4"
        )

        // Then
        assertEquals(original.id, copy.id)
        assertEquals("Omoplata", copy.name)
        assertEquals("Chave de ombro", copy.description)
        assertEquals(original.martialArtId, copy.martialArtId)
        assertTrue(copy.hasVideo)
        assertEquals("/videos/omoplata.mp4", copy.videoPath)
        assertEquals(original.hasPhoto, copy.hasPhoto)
        assertEquals(original.hasAudio, copy.hasAudio)
    }
}

class MediaFileTest {

    @Test
    fun `deve criar MediaFile com valores padrão`() {
        // Given
        val id = "media_1"
        val type = MediaType.VIDEO
        val uri = "content://media/video.mp4"

        // When
        val mediaFile = MediaFile(id = id, type = type, uri = uri)

        // Then
        assertEquals(id, mediaFile.id)
        assertEquals(type, mediaFile.type)
        assertEquals(uri, mediaFile.uri)
        assertEquals("", mediaFile.description)
        assertTrue(mediaFile.createdAt > 0)
    }

    @Test
    fun `deve criar MediaFile com todos os valores customizados`() {
        // Given
        val id = "media_2"
        val type = MediaType.PHOTO
        val uri = "content://media/photo.jpg"
        val description = "Foto da técnica"
        val createdAt = 1000L

        // When
        val mediaFile = MediaFile(
            id = id,
            type = type,
            uri = uri,
            description = description,
            createdAt = createdAt
        )

        // Then
        assertEquals(id, mediaFile.id)
        assertEquals(type, mediaFile.type)
        assertEquals(uri, mediaFile.uri)
        assertEquals(description, mediaFile.description)
        assertEquals(createdAt, mediaFile.createdAt)
    }

    @Test
    fun `deve copiar MediaFile com novos valores`() {
        // Given
        val original = MediaFile(
            id = "media_3",
            type = MediaType.AUDIO,
            uri = "content://media/audio.mp3"
        )

        // When
        val copy = original.copy(
            type = MediaType.VIDEO,
            uri = "content://media/video.mp4",
            description = "Vídeo da técnica"
        )

        // Then
        assertEquals(original.id, copy.id)
        assertEquals(MediaType.VIDEO, copy.type)
        assertEquals("content://media/video.mp4", copy.uri)
        assertEquals("Vídeo da técnica", copy.description)
        assertEquals(original.createdAt, copy.createdAt)
    }
}

class MediaTypeTest {

    @Test
    fun `deve ter todos os tipos de mídia corretos`() {
        // When & Then
        assertEquals(3, MediaType.values().size)
        assertTrue(MediaType.values().contains(MediaType.PHOTO))
        assertTrue(MediaType.values().contains(MediaType.VIDEO))
        assertTrue(MediaType.values().contains(MediaType.AUDIO))
    }

    @Test
    fun `deve comparar tipos de mídia corretamente`() {
        // Given
        val photo = MediaType.PHOTO
        val video = MediaType.VIDEO
        val audio = MediaType.AUDIO

        // When & Then
        assertEquals(MediaType.PHOTO, photo)
        assertEquals(MediaType.VIDEO, video)
        assertEquals(MediaType.AUDIO, audio)
        assertNotEquals(photo, video)
        assertNotEquals(video, audio)
        assertNotEquals(audio, photo)
    }
} 