package com.omertex.omertextest.data.model.entity

import java.io.Serializable

data class MainItemVO(
        val textData: Post,
        val photoData: Photo
) : Serializable