package com.olivia.plant.data.network

import com.olivia.plant.data.db.model.request.detect.DataParamDetect
import com.olivia.plant.data.db.model.request.login.DataParamLogin
import com.olivia.plant.data.db.model.request.register.DataParamRegister
import com.olivia.plant.data.db.model.response.detection.DataDetection
import com.olivia.plant.data.db.model.response.notification.DataNotification
import com.olivia.plant.data.db.model.response.user.DataUser
import com.olivia.plant.data.network.state.DevResponseBase
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiEndpoint {

    /**
     * Login User
     */
    @POST("login/")
    fun login(
        @Body requestModel: DataParamLogin
    ): Single<DevResponseBase<DataUser>>

    /**
     * Register
     */
    @POST("register/")
    fun register(
        @Body requestModel: DataParamRegister
    ): Single<DevResponseBase<Any>>

    @POST("plants/detect")
    fun detectLeaf(
        @Body requestModel: DataParamDetect
    ): Single<DevResponseBase<DataDetection>>

    @GET("notification/history")
    fun notificationHistory(
    ): Single<DevResponseBase<List<DataNotification>>>
}