package com.olivia.plant.ui.notification

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.olivia.plant.R
import com.olivia.plant.data.db.model.response.notification.DataNotification
import com.olivia.plant.databinding.ItemNotifikasiBinding
import com.oratakashi.viewbinding.core.tools.onClick

class NotificationAdapter(
    private val onClick: (DataNotification) -> Unit,
) : BaseQuickAdapter<DataNotification, BaseDataBindingHolder<ItemNotifikasiBinding>>(
    R.layout.item_notifikasi
), OnItemClickListener {

    override fun convert(holder: BaseDataBindingHolder<ItemNotifikasiBinding>, item: DataNotification) {
        holder.dataBinding?.let {
            it.tvName.text = item.title
            it.tvDesc.text = item.description

            it.root.onClick {
                onClick(item)
            }
        }
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        onClick(getItem(position))
    }

}