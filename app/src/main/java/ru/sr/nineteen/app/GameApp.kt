package ru.sr.nineteen.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.sr.nineteen.di.dataBaseModule
import ru.sr.nineteen.di.gameViewModelModule
import ru.sr.nineteen.di.menuViewModelModule
import ru.sr.nineteen.di.ratingModule
import ru.sr.nineteen.di.repositoryModule

class GameApp:Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@GameApp)
            modules(
                listOf(
                    dataBaseModule(),
                    repositoryModule(),
                    menuViewModelModule(),
                    gameViewModelModule(),
                    ratingModule()
                )
            )
        }
    }
}