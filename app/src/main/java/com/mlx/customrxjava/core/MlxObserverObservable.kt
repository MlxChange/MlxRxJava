package com.mlx.customrxjava.core

/**
 * Project:CustomRxJava
 * Created by MLX on 2020/10/7.
 */
class MlxObserverObservable <T>(val source:MlxObservableOnSubscribe<T>, private val scheduler:Schedulers):MlxObservableOnSubscribe<T>{

    override fun subscribe(emitter: MlxObserver<T>){
        val downStream=MlxSubscribeObserver(emitter,scheduler)
        source.subscribe(downStream)
    }

    class MlxSubscribeObserver<T>(val emitter:MlxObserver<T>,val scheduler:Schedulers):MlxObserver<T>{

        override fun onSubscribe() {
            scheduler.submitObserverWork {
                emitter.onSubscribe()
            }
        }

        override fun onNext(item: T) {
            scheduler.submitObserverWork {
                emitter.onNext(item)
            }
        }

        override fun onError(e: Throwable) {
            scheduler.submitObserverWork {
                emitter.onError(e)
            }
        }

        override fun onComplete() {
            scheduler.submitObserverWork{
                emitter.onComplete()
            }
        }

    }

}