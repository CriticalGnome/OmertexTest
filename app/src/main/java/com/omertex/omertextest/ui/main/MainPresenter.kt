package com.omertex.omertextest.ui.main

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.omertex.omertextest.data.model.entity.Photo
import com.omertex.omertextest.data.repository.DataRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

@InjectViewState
class MainPresenter(private val dataRepository: DataRepository) : MvpPresenter<MainView>() {

    fun onTextRequested() {
        dataRepository.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { viewState.progressBarVisibility(true) }
                .doOnTerminate {
                    viewState.progressBarVisibility(false)
                    viewState.refreshProgressVisibility(false)
                }
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
                .doOnTerminate {
                    viewState.progressBarVisibility(false)
                    viewState.refreshProgressVisibility(false)
                }
                .subscribe(
                        { it ->
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
                        { it ->
                            photo.sizes = it
                            viewState.addSizesData(photo)
                            viewState.updateList()
                        },
                        { error -> Timber.e(error.message) }
                )
    }

}