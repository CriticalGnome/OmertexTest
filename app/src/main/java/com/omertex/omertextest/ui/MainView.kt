package com.omertex.omertextest.ui

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.omertex.omertextest.data.model.entity.Picture
import com.omertex.omertextest.data.model.entity.Post
import com.omertex.omertextest.data.model.responce.PictureResponse

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : MvpView {

    fun addTextData(textData: List<Post>)
    fun addImagesData(imagesData: List<Picture>)
    fun updateView()

}