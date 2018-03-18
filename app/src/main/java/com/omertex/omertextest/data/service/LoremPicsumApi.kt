package com.omertex.omertextest.data.service

import com.omertex.omertextest.data.model.responce.PictureResponse
import com.omertex.omertextest.util.AppConstants
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface LoremPicsumApi {

    @GET("/list")
    fun getPics() : Observable<List<PictureResponse>>

    companion object {
        fun create(): LoremPicsumApi {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(AppConstants.IMAGES_DATA_UTL)
                    .build()
            return retrofit.create(LoremPicsumApi::class.java)
        }
    }

}