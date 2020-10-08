package com.mlx.customrxjava.core

/**
 * Project:CustomRxJava
 * Created by MLX on 2020/10/7.
 */
interface MlxObserver<T> {

    fun onSubscribe()

    fun onNext(item:T)

    fun onError(e:Throwable)

    fun onComplete()

}