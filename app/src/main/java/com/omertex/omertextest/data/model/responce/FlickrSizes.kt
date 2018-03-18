package com.omertex.omertextest.data.model.responce

import com.google.gson.annotations.SerializedName

data class FlickrSizes(
        @SerializedName("sizes") val sizes: Sizes
) {

    data class Sizes(
            val canblog: Int,
            val canprint: Int,
            val candownload: Int,
            val size: ArrayList<Size>,
            val stat: String
    )

    data class Size(
            val label: String,
            val width: Int,
            val height: Int,
            val source: String,
            val url: String,
            val media: String
    )

}