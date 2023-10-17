package com.olivia.plant.data.network

import com.olivia.plant.data.db.model.image.ResponseImage
import com.olivia.plant.data.db.model.user.DataUser
import com.olivia.plant.data.network.state.DevResponseBase
import com.zero.zerobase.data.model.DevResponse
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiEndpoint {

    /**
     * Login User
     */
    @FormUrlEncoded
    @POST("mobilelogin")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ): Single<DevResponseBase<DataUser>>

    @FormUrlEncoded
    @POST("data")
    fun postImage(
        @Field("plant_img") plant_img: String,
        @Field("plant_name") plant_name: String,
        @Field("condition") condition: String,
        @Field("disease") disease: String,
    ): Single<ResponseImage>
}