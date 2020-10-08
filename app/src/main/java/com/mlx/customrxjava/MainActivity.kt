package com.mlx.customrxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mlx.customrxjava.core.MlxObservable
import com.mlx.customrxjava.core.MlxObservableOnSubscribeJava
import com.mlx.customrxjava.core.MlxObserver
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        custom()
        //test()
    }

    private fun custom() {
        MlxObservable.create(MlxObservableOnSubscribeJava<String> {
            Log.i("zzz", "上游:${Thread.currentThread().name}")
            it.onNext("z")
        })
            .map {
                Log.i("zzz", "map:${Thread.currentThread().name}")
                5
            }
            .observerOn(com.mlx.customrxjava.core.Schedulers.IO())
            .subscribeOn(com.mlx.customrxjava.core.Schedulers.mainThread())
            .doOnNext {
                Log.i("zzz", "下游1:${Thread.currentThread().name}")
            }
            .observerOn(com.mlx.customrxjava.core.Schedulers.IO())
            .doOnNext {
                Log.i("zzz", "下游2:${Thread.currentThread().name}")
            }
            .observerOn(com.mlx.customrxjava.core.Schedulers.mainThread())
            .subscribe(object : MlxObserver<Int> {
                override fun onNext(item: Int) {
                    Log.i("zzz", "下游3:${Thread.currentThread().name}")
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