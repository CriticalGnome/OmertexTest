package com.omertex.omertextest.ui

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.omertex.omertextest.data.model.entity.Photo
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
                        { it ->
                            viewState.addTextData(it)
                            viewState.createItemsList()
                        },
                        { error -> Timber.e(error.message) }
                )
    }

    fun onPhotosRequested() {
        dataRepository.getPhotos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { viewState.progressBarVisibility(true) }
                .doOnTerminate { viewState.progressBarVisibility(false) }
                .subscribe(
                        {
                            viewState.addPhotoData(it)
                            viewState.createItemsList()
                        },
                        { error -> Timber.e(error.message) }
                )
    }

    fun onSizesRequested(photo: Photo) {
        dataRepository.getSizes(photo.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            photo.sizes = it
                            viewState.addSizesData(photo)
                            viewState.updateList()
                        },
                        { error -> Timber.e(error.message) }
                )
    }

}