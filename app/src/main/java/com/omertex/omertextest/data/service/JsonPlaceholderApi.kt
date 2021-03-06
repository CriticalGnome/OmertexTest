package com.omertex.omertextest.data.service

import com.omertex.omertextest.data.model.responce.PostResponse
import com.omertex.omertextest.util.AppConstants
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface JsonPlaceholderApi {

    @GET("/posts")
    fun getPosts() : Observable<List<PostResponse>>

    companion object {
        fun create(): JsonPlaceholderApi {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(AppConstants.TEXT_DATA_URL)
                    .build()
            return retrofit.create(JsonPlaceholderApi::class.java)
        }
    }

}