package com.omertex.omertextest.data.model.entity

data class Photo(
        val id: String,
        val owner: String,
        val secret: String,
        val server: String,
        val farm: Int,
        val title: String,
        val ispublic: Int,
        val isfriend: Int,
        val isfamily: Int
) {
    var sizes: List<Size> = ArrayList()

    data class Size(
            val label: String,
            val width: Int,
            val height: Int,
            val source: String,
            val url: String,
            val media: String
    )
}