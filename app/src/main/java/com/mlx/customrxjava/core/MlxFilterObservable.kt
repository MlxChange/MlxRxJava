package com.mlx.customrxjava.core

/**
 * Project:CustomRxJava
 * Created by MLX on 2020/10/7.
 */

/**
 * Project:CustomRxJava
 * Created by MLX on 2020/10/7.
 */
class MlxFilterObservable <T>(val source:MlxObservableOnSubscribe<T>,val func:((T)->Boolean)):MlxObservableOnSubscribe<T>{

    override fun subscribe(emitter: MlxObserver<T>){
        val map=MlxFilterObserver(emitter,func)
        source.subscribe(map)
    }

    class MlxFilterObserver<T>(val emitter:MlxObserver<T>,val func:((T)->Boolean)):MlxObserver<T>{

        override fun onSubscribe() {
            emitter.onSubscribe()
        }

        override fun onNext(item: T) {
            val result=func.invoke(item)
            if(result){
                emitter.onNext(item)
            }
        }

        override fun onError(e: Throwable) {
            emitter.onError(e)
        }

        override fun onComplete() {
            emitter.onComplete()
        }

    }

}