package com.mlx.customrxjava.core

import android.os.Handler
import android.os.Looper
import android.os.Message
import java.util.concurrent.Executors

/**
 * Project:CustomRxJava
 * Created by MLX on 2020/10/8.
 */
class Schedulers(){


    private var IOThreadPool =Executors.newCachedThreadPool()
    private var handler=Handler(Looper.getMainLooper()){ message->
        message.callback.run()
        return@Handler true
    }



    fun <T> submitSubscribeWork(source: MlxObservableOnSubscribe<T>, downStream: MlxObserver<T>,thread:Int) {
        when(thread){
            IO->{
                IOThreadPool.submit {
                    source.subscribe(downStream)
                }
            }
            MAIN->{
                handler.let {
                    val message=Message.obtain(it){
                        source.subscribe(downStream)
                    }
                    it.sendMessage(message)
                }
            }
        }

    }

    fun  submitObserverWork(function: () -> Unit,thread:Int) {
        when(thread){
            IO->{
                IOThreadPool?.submit {
                    function.invoke()
                }
            }
            MAIN->{
                handler?.let {
                    val m=Message.obtain(it){
                        function.invoke()
                    }
                    it.sendMessage(m)
                }
            }
        }
    }

    companion object{

        val INSTANCE: Schedulers by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            Schedulers()
        }

        private val IO=0;
        private val MAIN=1

        fun IO():Int{
            return IO
        }

        fun mainThread():Int{
            return MAIN
        }

    }


}