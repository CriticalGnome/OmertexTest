package com.omertex.omertextest.ui.details

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.arellomobile.mvp.presenter.ProvidePresenterTag
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.omertex.omertextest.R
import com.omertex.omertextest.data.model.entity.MainItemVO
import com.omertex.omertextest.util.GlideApp


class DetailsActivity : MvpAppCompatActivity(), DetailsView {

    @InjectPresenter(type = PresenterType.GLOBAL)
    lateinit var presenter: DetailsPresenter

    @BindView(R.id.image)           lateinit var image: ImageView
    @BindView(R.id.textHeader)      lateinit var textHeader: TextView
    @BindView(R.id.textDescription) lateinit var textDescription: TextView
    @BindView(R.id.progressBar)     lateinit var progressBar: ProgressBar

    @ProvidePresenterTag(presenterClass = DetailsPresenter::class, type = PresenterType.GLOBAL)
    fun provideDialogPresenterTag(): String = "Details"

    @ProvidePresenter(type = PresenterType.GLOBAL)
    fun provideDialogPresenter() = DetailsPresenter()

    companion object {
        private const val ITEM_EXTRA_NAME = "ITEM"
        fun getCallingIntent(context: Context, item: MainItemVO): Intent = Intent(context, DetailsActivity::class.java).putExtra(ITEM_EXTRA_NAME, item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val item = intent.getSerializableExtra(ITEM_EXTRA_NAME) as MainItemVO
        ButterKnife.bind(this)

        GlideApp
                .with(this)
                .load(item.photoData.sizes.last().source)
                .listener(object : RequestListener<Drawable> {
                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        progressBarVisibility(false)
                        return false
                    }

                    override fun onLoadFailed(e: GlideException?, model: Any?, target: com.bumptech.glide.request.target.Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        progressBarVisibility(false)
                        return false
                    }

                })
                .into(image)
        progressBarVisibility(true)
        textHeader.text = item.photoData.title
        textDescription.text = item.textData.body
    }

    override fun progressBarVisibility(isVisible: Boolean) {
        progressBar.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}