package com.olivia.plant.ui.augmented_reality

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.olivia.plant.databinding.ItemArObjectBinding

data class ARObject(val name: String, val modelPath: String)

class ObjectAdapter(private val objects: List<ARObject>, private val onClick: (ARObject) -> Unit) : RecyclerView.Adapter<ObjectAdapter.ObjectViewHolder>() {

    class ObjectViewHolder(val binding: ItemArObjectBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObjectViewHolder {
        val binding = ItemArObjectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ObjectViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ObjectViewHolder, position: Int) {
        val arObject = objects[position]
        holder.binding.tvObjectName.text = arObject.name
        holder.binding.root.setOnClickListener { onClick(arObject) }
    }

    override fun getItemCount(): Int = objects.size
}
