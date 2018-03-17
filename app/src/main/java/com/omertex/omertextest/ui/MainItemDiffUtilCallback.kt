package com.omertex.omertextest.ui

import android.support.v7.util.DiffUtil
import com.omertex.omertextest.data.model.entity.MainItemVO
import java.util.*

class MainItemDiffUtilCallback(private val oldList: List<MainItemVO>, private val newList: List<MainItemVO>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = Objects.equals(oldList[oldItemPosition].id, newList[newItemPosition].id)
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = Objects.equals(oldList[oldItemPosition], newList[newItemPosition])
}