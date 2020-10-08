package com.mlx.customrxjava.core

/**
 * Project:CustomRxJava
 * Created by MLX on 2020/10/7.
 */
interface MlxObservableOnSubscribe<T>{

    fun subscribe(emitter:MlxObserver<T>)

}