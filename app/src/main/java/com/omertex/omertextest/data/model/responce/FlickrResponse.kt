package com.omertex.omertextest.data.model.responce

import com.google.gson.annotations.SerializedName

data class FlickrResponse(
    @SerializedName("photos") val photos: Photos
) {

    data class Photos(
            val page: Int,
            val pages: String,
            val perpage: Int,
            val total: String,
            val photo: ArrayList<Photo>,
            val stat: String
    )

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
    )
}