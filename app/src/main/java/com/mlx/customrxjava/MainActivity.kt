package com.mlx.customrxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mlx.customrxjava.core.MlxObservable
import com.mlx.customrxjava.core.MlxObservableOnSubscribeJava
import com.mlx.customrxjava.core.MlxObserver
import com.mlx.customrxjava.core.Schedulers


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        custom()

        //test()
    }

    private fun custom() {
        MlxObservable.create(MlxObservableOnSubscribeJava<Int> {
            Log.i("zzz", "上游线程:${Thread.currentThread().name}")
            it.onNext(10)
        })

            .subscribeOn(Schedulers.IO())
            .observerOn(Schedulers.mainThread())
            .doOnNext {  }
            .observerOn(Schedulers.IO())
            .doOnNext {  }
            .observerOn(Schedulers.mainThread())
            .subscribe(object : MlxObserver<Int> {
                override fun onNext(item: Int) {
                    Log.i("zzz", "下游线程:${Thread.currentThread().name}")
                }

                override fun onSubscribe() {
                    println("onSubscribe")
                }

                override fun onError(e: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onComplete() {
                    TODO("Not yet implemented")
                }

            })
    }

}