package com.joaororiz.desafio.android.extension


import com.joaororiz.desafio.android.data.entities.Response
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.lang.Exception


fun <Res> Single<Response<Res>>.backgroundCall(schedulers: Scheduler = AndroidSchedulers.mainThread()): Single<Res> {
    return subscribeOn(Schedulers.io()).observeOn(schedulers).map {
       it.data
    }
}


