package com.omertex.omertextest.data.model.mapper

import com.omertex.omertextest.data.model.entity.Post
import com.omertex.omertextest.data.model.responce.PostResponse

class PostDataMapper {

    fun map(postResponse: PostResponse) : Post {
        return Post(
                postResponse.userId,
                postResponse.id,
                postResponse.title,
                postResponse.body
        )
    }

    fun mapList(postResponseList: List<PostResponse>) : List<Post> {
        val postList = ArrayList<Post>()
        for (postResponse in postResponseList) {
            postList.add(map(postResponse))
        }
        return postList
    }

}