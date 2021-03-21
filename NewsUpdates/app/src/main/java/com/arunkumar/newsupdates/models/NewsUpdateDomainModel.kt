package com.arunkumar.newsupdates.models

data class NewsUpdateDomainModel(
    val author: String,
    val description: String,
    val imageUrl: String,
    val url: String,
    val articleId: String,
    val likes: Int,
    val comments: Int
)
