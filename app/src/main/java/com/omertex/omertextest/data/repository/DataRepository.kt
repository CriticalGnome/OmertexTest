package com.omertex.omertextest.data.repository

import com.omertex.omertextest.data.model.entity.Picture
import com.omertex.omertextest.data.model.entity.Post
import com.omertex.omertextest.data.model.responce.PictureResponse
import io.reactivex.Observable

interface DataRepository {

    fun getPosts() : Observable<List<Post>>
    fun getPics() : Observable<List<Picture>>

}