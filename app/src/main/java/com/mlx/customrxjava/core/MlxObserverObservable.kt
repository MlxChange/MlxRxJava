package com.mlx.customrxjava.core

/**
 * Project:CustomRxJava
 * Created by MLX on 2020/10/7.
 */
class MlxObserverObservable<T>(val source: MlxObservableOnSubscribe<T>, private val thread: Int) :
    MlxObservableOnSubscribe<T> {

    override fun subscribe(emitter: MlxObserver<T>) {
        val downStream = MlxObserverObserver(emitter, thread)
        source.subscribe(downStream)
    }

    class MlxObserverObserver<T>(val emitter: MlxObserver<T>, val thread: Int) : MlxObserver<T> {

        override fun onSubscribe() {
            Schedulers.INSTANCE.submitObserverWork({
                emitter.onSubscribe()
            }, thread)
        }

        override fun onNext(item: T) {
            Schedulers.INSTANCE.submitObserverWork({
                emitter.onNext(item)
            }, thread)
        }

        override fun onError(e: Throwable) {
            Schedulers.INSTANCE.submitObserverWork({
                emitter.onError(e)
            }, thread)
        }

        override fun onComplete() {
            Schedulers.INSTANCE.submitObserverWork({
                emitter.onComplete()
            }, thread)
        }

    }

}