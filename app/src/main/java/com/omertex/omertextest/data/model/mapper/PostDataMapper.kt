package com.omertex.omertextest.data.model.mapper

import com.omertex.omertextest.data.model.entity.Post
import com.omertex.omertextest.data.model.responce.PostResponse

class PostDataMapper {

    fun map(postResponse: PostResponse): Post {
        return Post(
                postResponse.userId,
                postResponse.id,
                postResponse.title,
                postResponse.body
        )
    }

}