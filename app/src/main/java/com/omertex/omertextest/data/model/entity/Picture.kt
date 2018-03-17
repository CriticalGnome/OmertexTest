package com.omertex.omertextest.data.model.entity

data class Picture(
        val format: String,
        val width: Int,
        val height: Int,
        val filename: String,
        val id: Long,
        val author: String,
        val author_url: String,
        val post_url: String
)