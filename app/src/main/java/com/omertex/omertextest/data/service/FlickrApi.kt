package com.omertex.omertextest.data.service

import com.omertex.omertextest.data.model.responce.FlickrResponse
import com.omertex.omertextest.data.model.responce.FlickrSizes
import com.omertex.omertextest.util.AppConstants
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApi {

    @GET("/services/rest")
    fun getPhotos(
            @Query("method") method: String,
            @Query("api_key") api_key: String,
            @Query("format") format: String,
            @Query("per_page") per_page: String,
            @Query("nojsoncallback") nojsoncallback: Int
    ) : Observable<FlickrResponse>

    @GET("/services/rest")
    fun getSizes(
            @Query("method") method: String,
            @Query("api_key") api_key: String,
            @Query("photo_id") photo_id: String,
            @Query("format") format: String,
            @Query("nojsoncallback") nojsoncallback: Int
    ) : Observable<FlickrSizes>

    companion object {
        fun create(): FlickrApi {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(AppConstants.PHOTOS_DATA_UTL)
                    .build()
            return retrofit.create(FlickrApi::class.java)
        }
    }

}