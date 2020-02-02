package com.noban.simpleAPi.core.api

import com.noban.simpleAPi.model.RPData
import io.reactivex.Flowable
import retrofit2.http.GET

interface APIService {

    @GET("photos")
    abstract fun getPhotos(): Flowable<List<RPData>>
}