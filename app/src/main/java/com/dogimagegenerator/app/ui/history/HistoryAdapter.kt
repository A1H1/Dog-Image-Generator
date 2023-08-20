package com.dogimagegenerator.app.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dogimagegenerator.app.base.BaseAdapter
import com.dogimagegenerator.app.base.BaseViewHolder
import com.dogimagegenerator.app.databinding.ItemDogBinding
import com.dogimagegenerator.app.utils.loadImage
import kotlinx.coroutines.CoroutineScope

class HistoryAdapter(coroutineScope: CoroutineScope) :
    BaseAdapter<String, ItemDogBinding>(coroutineScope) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemDogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    inner class ViewHolder(private val binding: ItemDogBinding) :
        BaseViewHolder<String, ItemDogBinding>(binding) {

        override fun bind(data: String) {
            binding.root.loadImage(data)
        }
    }
}