package ru.sr.nineteen.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.sr.nineteen.di.dataBaseModule
import ru.sr.nineteen.di.repositoryModule
import ru.sr.nineteen.di.viewModelModule

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