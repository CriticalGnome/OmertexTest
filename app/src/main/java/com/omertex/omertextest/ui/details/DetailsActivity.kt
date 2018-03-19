package com.omertex.omertextest.ui.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.arellomobile.mvp.presenter.ProvidePresenterTag
import com.omertex.omertextest.R
import com.omertex.omertextest.data.model.entity.MainItemVO

class DetailsActivity : MvpAppCompatActivity(), DetailsView {

    @InjectPresenter(type = PresenterType.GLOBAL)
    lateinit var presenter: DetailsPresenter

    @BindView(R.id.textDetails)     lateinit var textDetails: TextView

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

        textDetails.text = item.photoData.title
    }
}