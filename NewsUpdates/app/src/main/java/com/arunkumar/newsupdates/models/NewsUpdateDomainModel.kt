package com.arunkumar.newsupdates.models

data class NewsUpdateDomainModel(
    val title: String,
    val author: String,
    val description: String,
    val imageUrl: String,
    val url: String,
    val content: String,
    val articleId: String,
    val likes: Int,
    val comments: Int
)
