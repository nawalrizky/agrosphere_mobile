package com.olivia.plant.data.db.model.image

import com.google.gson.annotations.SerializedName

data class ResponseImage(
    @SerializedName("message") val message: String?,
    @SerializedName("status") val status: Boolean?,
)
