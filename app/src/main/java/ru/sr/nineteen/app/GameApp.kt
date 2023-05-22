package ru.sr.nineteen.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.sr.mimeteen.di.databaseModule
import ru.sr.nineteen.di.authorizationModule
import ru.sr.nineteen.di.coreModule
import ru.sr.nineteen.di.gameModel
import ru.sr.nineteen.di.menuModule
import ru.sr.nineteen.di.profileModule
import ru.sr.nineteen.di.ratingModule
import ru.sr.nineteen.di.repositoryModule
import ru.sr.nineteen.di.trainingModule

class GameApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@GameApp)
            modules(
                listOf(repositoryModule())
                        + gameModel()
                        + authorizationModule()
                        + coreModule()
                        + ratingModule()
                        + menuModule()
                        + databaseModule()
                        + profileModule()
                        + trainingModule()
            )
        }
    }
}