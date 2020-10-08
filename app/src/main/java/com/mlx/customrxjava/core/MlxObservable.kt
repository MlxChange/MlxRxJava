package com.mlx.customrxjava.core

/**
 * Project:CustomRxJava
 * Created by MLX on 2020/10/7.
 */
open class MlxObservable<T>  constructor(){

    private  var source:MlxObservableOnSubscribe<T>?=null
    private var source2:((MlxObserver<T>)->Unit)?=null

    constructor(source:MlxObservableOnSubscribe<T>):this(){
        this.source=source
    }
    constructor(source:(MlxObserver<T>)->Unit):this(){
        this.source2=source
    }

    companion object{
        fun <T> create(emitter:MlxObservableOnSubscribe<T>):MlxObservable<T>{
            return MlxObservable(emitter)
        }

        fun <T> create(source2:(MlxObserver<T>)->Unit):MlxObservable<T>{
            return MlxObservable(source2)
        }

        fun <T> just(item:T):MlxObservable<T>{
            return create {
                it.onNext(item)
            }
        }
    }

    fun <R> map(func:(T)->R):MlxObservable<R>{
        val map=MlxMapObservable(this.source!!,func)
        return MlxObservable(map)
    }

    fun filter(func:(T)->Boolean):MlxObservable<T>{
        val map=MlxFilterObservable(this.source!!,func)
        return MlxObservable(map)
    }

    fun subscribeOn(scheduler:Schedulers):MlxObservable<T>{
        val subscribe=MlxSubscribeObservable(this.source!!,scheduler)
        return MlxObservable(subscribe)
    }

    fun doOnNext(func: (T) -> Unit):MlxObservable<T>{
        val subscribe=MlxDoOnNextObservable(this.source!!,func)
        return MlxObservable(subscribe)
    }

    fun observerOn(scheduler:Schedulers):MlxObservable<T>{
        val subscribe=MlxObserverObservable(this.source!!,scheduler)
        return MlxObservable(subscribe)
    }

    open fun subscribe(emitter: MlxObserver<T>){
        println("subscribe ::${this.javaClass.name}")
        emitter.onSubscribe()
        source?.subscribe(emitter)
        source2?.invoke(emitter)
    }

}