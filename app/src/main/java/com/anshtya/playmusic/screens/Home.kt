package com.anshtya.playmusic.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.anshtya.playmusic.R
import com.anshtya.playmusic.components.PlaylistItem
import com.anshtya.playmusic.data.model.Playlist
import com.anshtya.playmusic.viewmodel.YoutubeViewModel

@Composable
fun HomeRoute(
    youtubeViewModel: YoutubeViewModel = hiltViewModel()
) {
    val isLoading by youtubeViewModel.isLoading.collectAsStateWithLifecycle()
    val errorText by youtubeViewModel.errorText.collectAsStateWithLifecycle()
    val playlist by youtubeViewModel.playlist.collectAsStateWithLifecycle()
    val searchQuery by youtubeViewModel.searchQuery.collectAsStateWithLifecycle()
    HomeScreen(
        playlist = playlist,
        searchQuery = searchQuery,
        isLoading = isLoading,
        errorText = errorText,
        onQueryChange = { youtubeViewModel.updateText(it) },
        onSearch = { youtubeViewModel.searchPlaylist() }
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    playlist: Playlist,
    searchQuery: String,
    isLoading: Boolean,
    errorText: String?,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
    ) {
        SearchBar(
            query = searchQuery,
            onQueryChange = { onQueryChange(it) },
            onSearch = { onSearch() },
            active = false,
            onActiveChange = {},
            placeholder = { Text(stringResource(R.string.search_placeholder)) },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = null,
                    modifier = Modifier.clickable { onQueryChange("") }
                )
            },
            content = {},
            modifier = Modifier.fillMaxWidth()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            } else if (!errorText.isNullOrBlank()) {
                Text(
                    text = errorText,
                    modifier = Modifier.align(Alignment.Center)
                )
            } else if (playlist.items.isNotEmpty()) {
                LazyColumn(
                    contentPadding = PaddingValues(vertical = 5.dp),
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(playlist.items) {
                        PlaylistItem(snippet = it.snippet)
                    }
                }
            }
        }
    }
}