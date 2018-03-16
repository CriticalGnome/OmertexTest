package com.omertex.omertextest.ui

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.omertex.omertextest.data.model.entity.MainItemVO
import com.omertex.omertextest.data.repository.DataRepository
import com.omertex.omertextest.data.repository.DataRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    private val dataRepository: DataRepository = DataRepositoryImpl()

    fun onTextRequested() {
        dataRepository.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            it -> viewState.addTextData(it)
                            viewState.updateView()
                        },
                        { error -> Timber.e(error.message) }
                )
    }

    fun onPicturesRequested() {
        dataRepository.getPics()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            it -> viewState.addImagesData(it)
                            viewState.updateView()
                        },
                        { error -> Timber.e(error.message) }
                )
    }

}