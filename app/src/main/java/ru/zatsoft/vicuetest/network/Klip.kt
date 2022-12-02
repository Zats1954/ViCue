package ru.zatsoft.vicuetest.network

data class Klip(
    val id: String,
    val group: String,
    val color: String?,
    val file_url: String,
    val thumbnail_url: String,
    val poster_url: String,
    val small_thumbnail_url: String,
    val small_poster_url: String,
    val is_favorite: Boolean,
)
