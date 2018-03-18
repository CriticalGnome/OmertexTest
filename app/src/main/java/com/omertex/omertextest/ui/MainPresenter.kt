package com.omertex.omertextest.ui

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.omertex.omertextest.data.repository.DataRepository
import com.omertex.omertextest.di.AppInjector
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    init {
        AppInjector.appComponent.inject(this)
    }

    @Inject
    lateinit var dataRepository: DataRepository

    fun onTextRequested() {
        dataRepository.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { viewState.progressBarVisibility(true) }
                .doOnTerminate { viewState.progressBarVisibility(false) }
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
                .doOnSubscribe { viewState.progressBarVisibility(true) }
                .doOnTerminate { viewState.progressBarVisibility(false) }
                .subscribe(
                        {
                            it -> viewState.addImagesData(it)
                            viewState.updateView()
                        },
                        { error -> Timber.e(error.message) }
                )
    }

    fun onPhotosRequested() {
        dataRepository.getPhotos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { it -> viewState.addPhotoData(it) }
                )
    }

}