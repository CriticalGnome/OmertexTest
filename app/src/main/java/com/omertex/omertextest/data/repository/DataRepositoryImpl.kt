package com.omertex.omertextest.data.repository

import com.omertex.omertextest.data.model.entity.Photo
import com.omertex.omertextest.data.model.entity.Picture
import com.omertex.omertextest.data.model.entity.Post
import com.omertex.omertextest.data.model.mapper.PhotoDataMapper
import com.omertex.omertextest.data.model.mapper.PictureDataMapper
import com.omertex.omertextest.data.model.mapper.PostDataMapper
import com.omertex.omertextest.data.service.FlickrApi
import com.omertex.omertextest.data.service.JsonPlaceholderApi
import com.omertex.omertextest.data.service.LoremPicsumApi
import com.omertex.omertextest.util.AppConstants
import io.reactivex.Observable

class DataRepositoryImpl : DataRepository {

    private val jsonPlaceholderApi by lazy { JsonPlaceholderApi.create() }
    private val loremPicsumApi by lazy { LoremPicsumApi.create() }
    private val flickrApi by lazy { FlickrApi.create() }

    private val postDataMapper = PostDataMapper()
    private val pictureDataMapper = PictureDataMapper()
    private val photoDataMapper = PhotoDataMapper()

    override fun getPosts(): Observable<List<Post>> {
        return jsonPlaceholderApi.getPosts()
                .flatMapIterable { it -> it }
                .take(AppConstants.ITEMS_COUNT)
                .map { postDataMapper.map(it) }
                .toList()
                .toObservable()
    }

    override fun getPics(): Observable<List<Picture>> {
        return loremPicsumApi.getPics()
                .flatMapIterable { it -> it }
                .take(AppConstants.ITEMS_COUNT)
                .map { pictureDataMapper.map(it) }
                .toList()
                .toObservable()
    }

    override fun getPhotos(): Observable<List<Photo>> {
        return flickrApi.getPhotos(
                method = AppConstants.FLICKR_GET_PHOTOS_METHOD,
                api_key = AppConstants.FLICKR_API_KEY,
                format = AppConstants.FLICKR_RESPONSE_FORMAT,
                per_page = AppConstants.ITEMS_COUNT.toString(),
                nojsoncallback = 1
        )
                .flatMapIterable { it.photos.photo }
                .take(AppConstants.ITEMS_COUNT)
                .map { photoDataMapper.map(it) }
                .map {
                    it.sizes = flickrApi.getSizes(
                            method = AppConstants.FLICKR_GET_SIZES_METHOD,
                            api_key = AppConstants.FLICKR_API_KEY,
                            photo_id = it.id,
                            format = AppConstants.FLICKR_RESPONSE_FORMAT,
                            nojsoncallback = 1
                    ).flatMapIterable { it.sizes.size }
                            .map { photoDataMapper.map(it) }
                }
//                .toList()
//                .toObservable()
    }
}
