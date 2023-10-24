package com.olivia.plant.data.network

import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param
import com.olivia.plant.data.db.model.request.login.DataParamLogin
import com.olivia.plant.data.db.model.response.user.DataUser
import com.olivia.plant.data.network.state.DevResponseBase
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface EspEndpoint {

    enum class ESPACTION(val value: String) {
        FORWARD("forward"),
        BACKWARD("backward"),
        STOP("stop"),
        ON_LAMP("on_lamp"),
        OFF_LAMP("off_lamp"),
    }

    @GET("action")
    fun action(
        @Query("go") action: String
    ): Single<String>


}