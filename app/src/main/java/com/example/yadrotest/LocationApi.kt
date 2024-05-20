package com.example.yadrotest

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationApi {
    @GET("./geo/1.0/reverse?appid=${ApiKey.KEY}")
    fun getLocation(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): Single<List<Location>>
}