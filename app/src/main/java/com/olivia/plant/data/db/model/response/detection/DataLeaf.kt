package com.olivia.plant.data.db.model.response.detection


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class DataLeaf(
    @SerializedName("condition")
    val condition: String,
    @SerializedName("image_64")
    val image64: String
) : Parcelable