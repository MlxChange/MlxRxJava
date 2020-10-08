package com.mlx.customrxjava.demo

/**
 * Project:CustomRxJava
 * Created by MLX on 2020/10/8.
 */
class Observable {

    private val observerList= mutableListOf<Observer>()

    fun subscribe(observer: Observer){
        observerList.add(observer)
    }

    fun notifyObserver(){
        observerList.forEach {
            it.change()
        }
    }
}