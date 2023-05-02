package com.example.nineteen_2_0.di

import androidx.room.Room
import com.example.nineteen_2_0.data.database.db.DataBase
import org.koin.core.qualifier.named
import org.koin.dsl.module

/*@Module
@InstallIn(SingletonComponent::class)
class Module {

    @Provides
    @Singleton
    fun provideGameDoa(dataBase: DataBase) = dataBase.gameDao()

    @Provides
    @Singleton
    fun provideRatingDao(dataBase: DataBase) = dataBase.ratingDao()

    @Provides
    @Singleton
    fun providerGameDataBase(@ApplicationContext appContext: Context) =
        Room.databaseBuilder(
            appContext,
            DataBase::class.java,
            "list"
        ).build()
}*/

fun dataBaseModule() = module {

    single(named("database")) {
        Room.databaseBuilder(
            get(),
            DataBase::class.java,
            "list"
        ).build()
    }

    single {
        get<DataBase>(named("database")).gameDao()
    }


    single {
        get<DataBase>(named("database")).ratingDao()
    }

}