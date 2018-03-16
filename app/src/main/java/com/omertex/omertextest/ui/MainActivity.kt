package com.omertex.omertextest.ui

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.arellomobile.mvp.presenter.ProvidePresenterTag
import com.omertex.omertextest.R
import com.omertex.omertextest.data.model.entity.MainItemVO
import com.omertex.omertextest.data.model.entity.Picture
import com.omertex.omertextest.data.model.entity.Post

class MainActivity : MvpAppCompatActivity(), MainView {

    @BindView(R.id.mainRecycler)    lateinit var recycler: RecyclerView

    @InjectPresenter(type = PresenterType.GLOBAL)
    lateinit var presenter: MainPresenter

    private var textData: List<Post> = ArrayList()
    private var imagesData: List<Picture> = ArrayList()
    private var items: MutableList<MainItemVO> = ArrayList()

    private var adapter: MainAdapter = MainAdapter(items, object : MainAdapter.Callback {
        override fun onItemClicked(item: MainItemVO) {
            Toast.makeText(this@MainActivity, "Clicked item #" + item.id, Toast.LENGTH_SHORT).show()
        }
    })

    @ProvidePresenterTag(presenterClass = MainPresenter::class, type = PresenterType.GLOBAL)
    fun provideDialogPresenterTag(): String = "Main"

    @ProvidePresenter(type = PresenterType.GLOBAL)
    fun provideDialogPresenter() = MainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        presenter.onTextRequested()
        presenter.onPicturesRequested()

        recycler.adapter = adapter
    }

    override fun addTextData(textData: List<Post>) {
        this.textData = textData
    }

    override fun addImagesData(imagesData: List<Picture>) {
        this.imagesData = imagesData
    }

    override fun updateView() {
        if (textData.isEmpty() || imagesData.isEmpty()) return
        for (i in 0..49) {
            items.add(MainItemVO(
                    textData[i].id.toString(),
                    textData[i].title,
                    imagesData[i].post_url
            ))
        }
        adapter.items = items
        adapter.notifyDataSetChanged()
    }

}
