package com.olivia.plant.ui.home

import android.annotation.SuppressLint
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.olivia.plant.R
import com.olivia.plant.data.db.model.response.history.DataDetectionHistoryItem
import com.olivia.plant.databinding.ItemHistoryBinding
import com.olivia.plant.utils.toReadableDate
import com.oratakashi.viewbinding.core.tools.loadImage
import com.oratakashi.viewbinding.core.tools.onClick

class HistoryAdapter(
    private val onClick: (DataDetectionHistoryItem) -> Unit,
) : BaseQuickAdapter<DataDetectionHistoryItem, BaseDataBindingHolder<ItemHistoryBinding>>(
    R.layout.item_history
), OnItemClickListener {

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun convert(holder: BaseDataBindingHolder<ItemHistoryBinding>, item: DataDetectionHistoryItem) {
        holder.dataBinding?.let {
            it.tvCondition.text = item.condition
            it.tvPlantName.text = item.plantName
            it.tvDate.text = item.createdAt.toReadableDate()
            it.ivDetection.loadImage(it.root.context, imageUrl = item.imageUrl, errorDrawable = it.root.context.getDrawable(R.drawable.ic_broken_image))

            it.root.onClick {
                onClick(item)
            }
        }
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        onClick(getItem(position))
    }

}