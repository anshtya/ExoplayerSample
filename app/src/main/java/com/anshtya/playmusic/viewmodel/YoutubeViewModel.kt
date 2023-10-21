package com.anshtya.playmusic.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anshtya.playmusic.Constants
import com.anshtya.playmusic.data.model.Playlist
import com.anshtya.playmusic.data.network.YoutubeApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YoutubeViewModel @Inject constructor(
    private val youtubeApi: YoutubeApi
) : ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _errorText = MutableStateFlow<String?>("")
    val errorText = _errorText.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _playlist = MutableStateFlow(Playlist())
    val playlist = _playlist.asStateFlow()

//    init {
//        _searchQuery.value = "https://youtube.com/playlist?list=PLDzeHZWIZsTryvtXdMr6rPh4IDexB5NIA&si=-5nMWJhdQAb767Ae"
//        searchPlaylist()
//    }

    fun searchPlaylist() {
        viewModelScope.launch {
            _errorText.update { null }
            _isLoading.update { true }
            try {
                val playlistId = _searchQuery.value
                    .substringAfter(Constants.YOUTUBE_PLAYLIST_BASE_URL)
                    .substringBefore(Constants.YOUTUBE_PLAYLIST_SI)
                val playlist = youtubeApi.getPlaylist(playlistId = playlistId)
                _playlist.update { playlist }
            } catch (e: Exception) {
                _errorText.update { e.message }
            } finally {
                _isLoading.update { false }
            }
        }
    }

    fun updateText(newSearchQuery: String) {
        _searchQuery.update { newSearchQuery }
    }
}