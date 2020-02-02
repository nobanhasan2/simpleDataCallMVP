package com.noban.simpleAPi.core.api

import android.content.Context
import com.noban.simpleAPi.model.RPData


import io.reactivex.Flowable

/**
 * Created by Noban on 30/1/20.
 * Audacity It Solution.
 */
class Repository(context: Context) {


    private val apiService: APIService

    fun getPhotos(): Flowable<List<RPData>>{
        return apiService.getPhotos()
    }


    init {
        apiService = RetrofitAPIFactory.createService(context, 30)

    }


}

