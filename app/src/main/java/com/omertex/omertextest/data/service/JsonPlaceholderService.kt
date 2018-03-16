package com.omertex.omertextest.data.service

import com.omertex.omertextest.data.model.responce.PostResponse
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface JsonPlaceholderService {

    @GET("/posts")
    fun getPosts() : Observable<List<PostResponse>>

    companion object {
        fun create(): JsonPlaceholderService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://jsonplaceholder.typicode.com")
                    .build()
            return retrofit.create(JsonPlaceholderService::class.java)
        }
    }

}