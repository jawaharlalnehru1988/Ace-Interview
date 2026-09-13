package com.example

import com.example.data.local.interview.VideoMockCatalog
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class VideoMockCatalogTest {

    @Test
    fun testAllElevenTopicsExist() {
        val topics = VideoMockCatalog.getTopics()
        val expectedTopics = listOf(
            "java",
            "spring_boot",
            "microservices",
            "full_stack",
            "hld",
            "lld",
            "system_design",
            "devops",
            "sql",
            "angular",
            "security"
        )
        assertEquals("Expected 11 curated topics", 11, topics.size)
        val topicIds = topics.map { it.id }.toSet()
        expectedTopics.forEach { expectedId ->
            assertTrue("Topic $expectedId must exist in catalog", topicIds.contains(expectedId))
        }
    }

    @Test
    fun testEachTopicHasMinimumTenVideos() {
        val topics = VideoMockCatalog.getTopics()
        val allVideos = VideoMockCatalog.getAllVideos()
        assertTrue("Total videos should be at least 110", allVideos.size >= 110)

        topics.forEach { topic ->
            val videos = VideoMockCatalog.getVideosForTopic(topic.id)
            assertTrue(
                "Topic ${topic.name} (${topic.id}) must have at least 10 videos, found ${videos.size}",
                videos.size >= 10
            )
        }
    }

    @Test
    fun testVideoIntegrityAndValidFields() {
        val allVideos = VideoMockCatalog.getAllVideos()
        val uniqueIds = mutableSetOf<String>()

        allVideos.forEach { video ->
            assertTrue("Video ID must not be empty", video.id.isNotBlank())
            assertTrue("Duplicate video ID: ${video.id}", uniqueIds.add(video.id))

            assertTrue("Title should be non-blank for ${video.id}", video.title.isNotBlank())
            assertTrue("Channel should be non-blank for ${video.id}", video.channelName.isNotBlank())
            assertTrue(
                "YouTube ID should be valid 11 chars for ${video.id}: ${video.youtubeVideoId}",
                video.youtubeVideoId.length == 11
            )
            assertTrue("Duration should be non-blank for ${video.id}", video.duration.isNotBlank())
            assertTrue("Difficulty should be non-blank for ${video.id}", video.difficulty.isNotBlank())
            assertTrue("Description should be detailed for ${video.id}", video.description.length >= 30)
            assertTrue(
                "Key takeaways should have at least 2 points for ${video.id}",
                video.keyTakeaways.size >= 2
            )
            assertTrue("Related track ID should be non-blank for ${video.id}", video.relatedTrackId.isNotBlank())
        }
    }

    @Test
    fun testAutoplayQueueProgression() {
        val javaVideos = VideoMockCatalog.getVideosForTopic("java")
        val firstVideo = javaVideos[0]
        val secondVideo = javaVideos[1]
        val lastVideo = javaVideos.last()

        val nextFromFirst = VideoMockCatalog.getNextVideo(firstVideo.id, "java")
        assertNotNull(nextFromFirst)
        assertEquals("Next from first video should be second video", secondVideo.id, nextFromFirst?.id)

        // Test loop around on last video
        val nextFromLast = VideoMockCatalog.getNextVideo(lastVideo.id, "java")
        assertNotNull(nextFromLast)
        assertEquals("Next from last video should loop back to first video", firstVideo.id, nextFromLast?.id)

        // Test previous video
        val prevFromSecond = VideoMockCatalog.getPreviousVideo(secondVideo.id, "java")
        assertNotNull(prevFromSecond)
        assertEquals("Previous from second video should be first video", firstVideo.id, prevFromSecond?.id)
    }
}
