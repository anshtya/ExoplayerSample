package com.anshtya.playmusic.data.model

data class Playlist(
    val etag: String = "",
    val items: List<Item> = emptyList(),
    val kind: String = "",
    val nextPageToken: String = "",
    val pageInfo: PageInfo = PageInfo()
)