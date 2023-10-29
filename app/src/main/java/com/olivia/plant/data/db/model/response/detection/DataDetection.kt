package com.olivia.plant.data.db.model.response.detection


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class DataDetection(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("leafs_disease")
    val dataLeafsDisease: List<DataLeafsDisease>
) : Parcelable