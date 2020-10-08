package com.mlx.customrxjava.core

import android.os.Handler
import android.os.Looper
import android.os.Message
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Project:CustomRxJava
 * Created by MLX on 2020/10/8.
 */
class Schedulers(){


    var executorService:ExecutorService?=null
    var handler:Handler?=null

    constructor(executorService: ExecutorService):this(){
        this.executorService=executorService
    }

    constructor(handler: Handler):this(){
        this.handler=handler
    }



    fun <T> submitSubscribeWork(source: MlxObservableOnSubscribe<T>, downStream: MlxObserver<T>) {
        executorService?.execute {
            source.subscribe(downStream)
        }
        handler?.let {
            val m=Message.obtain(it){
                source.subscribe(downStream)
            }
            it.sendMessage(m)
        }
    }

    fun  submitObserverWork(function: () -> Unit) {
        executorService?.execute {
            function.invoke()
        }
        handler?.let {
            val m=Message.obtain(it){
                function.invoke()
            }
            it.sendMessage(m)
        }
    }

    companion object{

        private val IO=Executors.newCachedThreadPool()

        fun IO():Schedulers{
            return Schedulers(IO)
        }

        fun mainThread():Schedulers{
            return Schedulers(mainThread)
        }

        private val mainThread=Handler(Looper.getMainLooper()){message->
            message.callback.run()
            return@Handler true
        }
    }


}