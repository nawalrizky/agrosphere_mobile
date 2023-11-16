package com.olivia.plant.data.db.model.response.history


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class DataDetectionHistoryItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("source")
    val source: String,
    @SerializedName("plant_img")
    val plantImg: String,
    @SerializedName("plant_name")
    val plantName: String,
    @SerializedName("condition")
    val condition: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("recommendation")
    val recommendation: Recommendation,
    @SerializedName("image_url")
    val imageUrl: String
) : Parcelable