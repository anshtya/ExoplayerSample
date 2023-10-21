package com.anshtya.playmusic.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.anshtya.playmusic.data.model.Playlist
import com.anshtya.playmusic.viewmodel.YoutubeViewModel

@Composable
fun HomeRoute(
    youtubeViewModel: YoutubeViewModel = hiltViewModel()
) {
    val playlist by youtubeViewModel.playlist.collectAsStateWithLifecycle()
    HomeScreen(playlist)
}

@Composable
fun HomeScreen(
    playlist: Playlist,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text("$playlist")
    }
}