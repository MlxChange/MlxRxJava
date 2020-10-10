package com.mlx.customrxjava.core

/**
 * Project:CustomRxJava
 * Created by MLX on 2020/10/7.
 */
class MlxMapObservable <T, R>(private val source:MlxObservableOnSubscribe<T>, private val func:((T)->R)):MlxObservableOnSubscribe<R>{

    override fun subscribe(emitter: MlxObserver<R>){
        val map=MlxMapObserver(emitter,func)
        source.subscribe(map)
    }

    class MlxMapObserver<T,R>(private val emitter:MlxObserver<R>, private val func:((T)->R)):MlxObserver<T>{

        override fun onSubscribe() {
            emitter.onSubscribe()
        }

        override fun onNext(item: T) {
            val result=func.invoke(item)
            emitter.onNext(result)
        }

        override fun onError(e: Throwable) {
            emitter.onError(e)
        }

        override fun onComplete() {
            emitter.onComplete()
        }

    }

}