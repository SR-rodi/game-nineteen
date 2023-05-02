package com.example.nineteen_2_0.app

import android.app.Application
import com.example.nineteen_2_0.di.dataBaseModule
import com.example.nineteen_2_0.di.repositoryModule
import com.example.nineteen_2_0.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NewApp:Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@NewApp)
            modules(
                listOf(
                    dataBaseModule(),
                    repositoryModule(),
                    viewModelModule()
                )
            )
        }
    }
}