package com.omertex.omertextest.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.omertex.omertextest.R
import com.omertex.omertextest.data.model.entity.MainItemVO
class MainAdapter(var items: List<MainItemVO>, var callback: Callback) : RecyclerView.Adapter<MainAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder
            = MainHolder(LayoutInflater.from(parent.context).inflate(R.layout.main_item, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class MainHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.itemId)      lateinit var itemId: TextView
        @BindView(R.id.itemTitle)   lateinit var itemTitle: TextView
        @BindView(R.id.itemUrl)     lateinit var itemUrl: TextView

        init {
            ButterKnife.bind(this, itemView)
        }

        fun bind(item: MainItemVO) {
            itemId.text = item.textData.id.toString()
            itemTitle.text = item.textData.title
            if (item.photoData.sizes.isNotEmpty()) itemUrl.text = item.photoData.sizes.last().source
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    callback.onItemClicked(items[adapterPosition])
                }
            }
        }
    }

    interface Callback {
        fun onItemClicked(item: MainItemVO)
    }

}
