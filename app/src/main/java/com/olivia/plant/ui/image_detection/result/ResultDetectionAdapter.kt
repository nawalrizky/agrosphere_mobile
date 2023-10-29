package com.olivia.plant.ui.image_detection.result

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.olivia.plant.R
import com.olivia.plant.data.db.model.response.detection.DataLeafsDisease
import com.olivia.plant.databinding.ItemDetectionBinding
import com.oratakashi.viewbinding.core.tools.onClick

class ResultDetectionAdapter (
    private val onClick: (DataLeafsDisease) -> Unit,
) : BaseQuickAdapter<DataLeafsDisease, BaseDataBindingHolder<ItemDetectionBinding>>(
    R.layout.item_detection
), OnItemClickListener {
    override fun convert(holder: BaseDataBindingHolder<ItemDetectionBinding>, item: DataLeafsDisease) {
        holder.dataBinding?.let {
            it.tvName.text = item.condition
            it.tvGejala.text = item.dataRecomendation.symptoms
            it.tvRekomendasi.text = item.dataRecomendation.recomendation

            it.root.onClick {
                onClick(item)
            }
        }
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        onClick(getItem(position))
    }

}