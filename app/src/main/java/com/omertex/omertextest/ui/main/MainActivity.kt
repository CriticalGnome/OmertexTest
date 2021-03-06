package com.omertex.omertextest.ui.main

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import butterknife.BindView
import butterknife.ButterKnife
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.arellomobile.mvp.presenter.ProvidePresenterTag
import com.omertex.omertextest.R
import com.omertex.omertextest.data.model.entity.MainItemVO
import com.omertex.omertextest.data.model.entity.Photo
import com.omertex.omertextest.data.model.entity.Post
import com.omertex.omertextest.data.repository.DataRepository
import com.omertex.omertextest.ui.details.DetailsActivity
import com.omertex.omertextest.util.AppConstants
import org.koin.android.ext.android.inject

class MainActivity : MvpAppCompatActivity(), MainView {

    @BindView(R.id.progressBar)     lateinit var progressBar: ProgressBar
    @BindView(R.id.refresh)         lateinit var refresh: SwipeRefreshLayout
    @BindView(R.id.mainRecycler)    lateinit var recycler: RecyclerView

    @InjectPresenter(type = PresenterType.GLOBAL)
    lateinit var presenter: MainPresenter

    private val dataRepository: DataRepository by inject()

    private var textData: List<Post> = ArrayList()
    private var photoData: List<Photo> = ArrayList()
    private var items: MutableList<MainItemVO> = ArrayList()

    private var adapter: MainAdapter = MainAdapter(items, object : MainAdapter.Callback {
        override fun onItemClicked(item: MainItemVO) {
            val bundle = Bundle()
            bundle.putSerializable("ITEM", item)
            startActivity(DetailsActivity.getCallingIntent(this@MainActivity, item))
        }
    })

    @ProvidePresenterTag(presenterClass = MainPresenter::class, type = PresenterType.GLOBAL)
    fun provideDialogPresenterTag(): String = "Main"

    @ProvidePresenter(type = PresenterType.GLOBAL)
    fun provideDialogPresenter() = MainPresenter(dataRepository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        refresh.setOnRefreshListener { requestData() }
        requestData()

        recycler.adapter = adapter
        adapter.items = ArrayList()
    }

    private fun requestData() {
        presenter.onTextRequested()
        presenter.onPhotosRequested()
    }

    override fun addTextData(textData: List<Post>) {
        this.textData = textData
    }

    override fun addSizesData(photo: Photo) {
        photoData.forEach {
            if (it.id == photo.id) it.sizes = photo.sizes
        }
    }

    override fun addPhotoData(photoData: List<Photo>) {
        this.photoData = photoData
        this.photoData.forEach { presenter.onSizesRequested(it) }
    }

    override fun createItemsList() {
        if (textData.isEmpty() || photoData.isEmpty()) return
        for (i: Int in 0 until AppConstants.ITEMS_COUNT.toInt()) {
            items.add(MainItemVO(textData[i], photoData[i]))
        }
        updateList()
    }

    override fun updateList() {
        adapter.items = items
        adapter.notifyDataSetChanged()
    }

    override fun progressBarVisibility(isVisible: Boolean) {
        progressBar.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    override fun refreshProgressVisibility(isVisible: Boolean) {
        refresh.isRefreshing = isVisible
    }
}
