package com.mlx.customrxjava

import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable


/**
 * Project:CustomRxJava
 * Created by MLX on 2020/10/7.
 */
fun main() {


    Observable.create<Int> { emitter ->
        emitter.onNext(10)
    }.subscribe({

    })
}
/*
private fun custom() {
    MlxObservable.create(MlxObservableOnSubscribeJava<Int> {

    })

        .map {
            if (it > 300) {
                return@map "大于300"
            }
            "lll"
        }
        .subscribe(object : MlxObserver<String> {
            override fun onNext(item: String) {
                println("onNext:${item}")
            }

            override fun onSubscribe() {
                println("onSubscribe")
            }

            override fun onError(e: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onComplete() {
                TODO("Not yet implemented")
            }

        })
}

fun test() {
    Observable.create<Int> {
        it.onNext(10)

    }
        .subscribe(object :Observer<Int>{
            override fun onComplete() {
                Log.i("zzz", "onComplete:${Thread.currentThread().name}")

            }

            override fun onSubscribe(d: Disposable?) {
                Log.i("zzz", "onSubscribe:${Thread.currentThread().name}")

            }

            override fun onNext(t: Int?) {
                Log.i("zzz", "onNext:${Thread.currentThread().name}")

            }

            override fun onError(e: Throwable?) {
                Log.i("zzz", "onError:${Thread.currentThread().name}")

            }

        })
}*/