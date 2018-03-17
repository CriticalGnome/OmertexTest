package com.omertex.omertextest.data.model.entity

data class MainItemVO(
        val id: Long,
        val title: String,
        val url: String
) {
    constructor(post: Post, picture: Picture) : this(post.id, post.title, picture.post_url)
}