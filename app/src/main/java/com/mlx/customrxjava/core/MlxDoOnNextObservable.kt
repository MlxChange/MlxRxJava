package com.mlx.customrxjava.core

/**
 * Project:CustomRxJava
 * Created by MLX on 2020/10/7.
 */

/**
 * Project:CustomRxJava
 * Created by MLX on 2020/10/7.
 */
class MlxDoOnNextObservable <T>(val source:MlxObservableOnSubscribe<T>, val func:((T)->Unit)):MlxObservableOnSubscribe<T>{

    override fun subscribe(emitter: MlxObserver<T>){
        val map=MlxDoOnNextObserver(emitter,func)
        source.subscribe(map)
    }

    class MlxDoOnNextObserver<T>(val emitter:MlxObserver<T>,val func:((T)->Unit)):MlxObserver<T>{

        override fun onSubscribe() {
            emitter.onSubscribe()
        }

        override fun onNext(item: T) {
            val result=func.invoke(item)
            emitter.onNext(item)
        }

        override fun onError(e: Throwable) {
            emitter.onError(e)
        }

        override fun onComplete() {
            emitter.onComplete()
        }

    }

}