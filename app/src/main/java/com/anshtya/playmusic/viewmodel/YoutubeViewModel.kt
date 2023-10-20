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
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _playlist = MutableStateFlow(Playlist())
    val playlist = _playlist.asStateFlow()

    fun searchPlaylist() {
        viewModelScope.launch {
            val playlistId = _searchText.value
                .substringAfter(Constants.YOUTUBE_PLAYLIST_BASE_URL)
                .substringBefore(Constants.YOUTUBE_PLAYLIST_SI)
            val playlist = youtubeApi.getPlaylist(playlistId)
            _playlist.update { playlist }
        }
    }

    fun updateText(newText: String) {
        _searchText.update { newText }
    }
}