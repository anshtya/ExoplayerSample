package com.anshtya.playmusic.data.network

import com.anshtya.playmusic.BuildConfig
import com.anshtya.playmusic.Constants
import com.anshtya.playmusic.data.model.Playlist
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApi {
    @GET("/playlistItems")
    suspend fun getPlaylist(
        @Query("playlistId") playlistId: String,
        @Query("part") part: String = Constants.PART,
        @Query("maxResults") maxResults: Int = Constants.MAX_RESULTS,
        @Query("key") key: String = BuildConfig.API_KEY
    ): Playlist
}