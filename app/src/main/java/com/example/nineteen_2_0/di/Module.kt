package com.example.nineteen_2_0.di

import android.content.Context
import androidx.room.Room
import com.example.nineteen_2_0.data.database.db.DataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
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
}