package com.omertex.omertextest.data.repository

import com.omertex.omertextest.data.model.entity.Photo
import com.omertex.omertextest.data.model.entity.Post
import io.reactivex.Observable

interface DataRepository {

    fun getPosts() : Observable<List<Post>>
    fun getPhotos(): Observable<List<Photo>>
    fun getSizes(id: String): Observable<List<Photo.Size>>

}