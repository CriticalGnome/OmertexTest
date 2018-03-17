package com.omertex.omertextest.data.repository

import com.omertex.omertextest.data.model.entity.Picture
import com.omertex.omertextest.data.model.mapper.PostDataMapper
import com.omertex.omertextest.data.model.entity.Post
import com.omertex.omertextest.data.model.mapper.PictureDataMapper
import com.omertex.omertextest.data.model.responce.PictureResponse
import com.omertex.omertextest.data.service.JsonPlaceholderService
import com.omertex.omertextest.data.service.LoremPicsumService
import io.reactivex.Observable

class DataRepositoryImpl : DataRepository {

    private val jsonPlaceholderService by lazy {
        JsonPlaceholderService.create()
    }

    private val loremPicsumService by lazy {
        LoremPicsumService.create()
    }

    private val postDataMapper = PostDataMapper()
    private val pictureDataMapper = PictureDataMapper()

    override fun getPosts(): Observable<List<Post>> {
        return jsonPlaceholderService.getPosts()
                .flatMapIterable { it -> it }
                .take(50)
                .map { postDataMapper.map(it) }
                .toList()
                .toObservable()
    }

    override fun getPics(): Observable<List<Picture>> {
        return loremPicsumService.getPics()
                .flatMapIterable { it -> it }
                .take(50)
                .map { pictureDataMapper.map(it) }
                .toList()
                .toObservable()
    }

}
