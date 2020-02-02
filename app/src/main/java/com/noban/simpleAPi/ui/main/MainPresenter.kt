package com.noban.simpleAPi.ui.main

import android.util.Log
import com.noban.simpleAPi.core.api.Repository
import com.noban.simpleAPi.model.RPData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainPresenter (val mainView: MainView,val repository: Repository) {

    fun getData(){
        repository!!.getPhotos()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::onSuccess, this::onError)
    }
    fun onSuccess(rpData: List<RPData>){
         Log.e("Data",rpData.get(0).title)
    }

    fun onError(throwable: Throwable){
        Log.e("Error",throwable.message)
    }
}