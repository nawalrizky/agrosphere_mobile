package com.olivia.plant.data.db.model.response.detection


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class DataLeafsDisease(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("condition")
    val condition: String,
    @SerializedName("image_uri")
    val imageUri: String,
    @SerializedName("recomendation")
    val dataRecomendation: DataRecomendation
) : Parcelable