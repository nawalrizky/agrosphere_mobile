package com.olivia.plant.data.network.state

import com.google.gson.annotations.SerializedName
import com.zero.zerobase.data.model.dynamic.DevResponseDynamicInterface

data class DevResponseBase<T>(
    @SerializedName("success")
    override val success: Int?,
    @SerializedName("message")
    override val message: String?,
    @SerializedName("data")
    override val data: T?
) : DevResponseDynamicInterface<T>