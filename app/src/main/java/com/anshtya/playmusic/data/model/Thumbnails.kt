package com.anshtya.playmusic.data.model

data class Thumbnails(
    val default: Thumbnail,
    val high: Thumbnail,
    val maxres: Thumbnail,
    val medium: Thumbnail,
    val standard: Thumbnail
)

data class Thumbnail(
    val height: Int,
    val url: String,
    val width: Int
)