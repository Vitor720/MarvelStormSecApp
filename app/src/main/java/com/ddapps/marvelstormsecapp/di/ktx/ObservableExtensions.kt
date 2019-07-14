package com.ddapps.marvelstormsecapp.di.ktx

import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


fun <T : Any> Observable<T>.commonSubscribe(onSuccess: (T) -> Unit, onError: (Throwable) -> Unit): Disposable {
    return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe({ onSuccess(it) }, { onError(it) })
}

fun <T : Any> Single<T>.commonSubscribe(onSuccess: (T) -> Unit, onError: (Throwable) -> Unit): Disposable {
    return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe({ onSuccess(it) }, { onError(it) })
}

fun <T : Any> Maybe<T>.commonSubscribe(onSuccess: (T) -> Unit, onError: (Throwable) -> Unit): Disposable {
    return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe({ onSuccess(it) }, { onError(it) })
}

fun <T : Any> Flowable<T>.commonSubscribe(onSuccess: (T) -> Unit, onError: (Throwable) -> Unit): Disposable {
    return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe({ onSuccess(it) }, { onError(it) })
}