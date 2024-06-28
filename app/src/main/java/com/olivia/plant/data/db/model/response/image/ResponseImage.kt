package com.olivia.plant.data.db.model.response.image

import com.google.gson.annotations.SerializedName
import com.olivia.plant.data.db.model.response.detection.DataDetection

data class ResponseImage(
    @SerializedName("data") val data: DataDetection?,
    @SerializedName("status") val status: Boolean?,
    @SerializedName("message") val message: String?
)
