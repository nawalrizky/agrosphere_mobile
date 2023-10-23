package com.olivia.plant.data.db.model.response.image

import com.google.gson.annotations.SerializedName

data class ResponseImage(
    @SerializedName("message") val message: String?,
    @SerializedName("status") val status: Boolean?,
)
