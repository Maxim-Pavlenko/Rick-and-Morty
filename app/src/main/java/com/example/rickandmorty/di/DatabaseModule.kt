package com.example.rickandmorty.di

import android.content.Context
import androidx.room.Room
import com.example.rickandmorty.data.local.CharacterDataBase
import com.example.rickandmorty.util.Constants.CHARACTER_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): CharacterDataBase {
        return Room.databaseBuilder(
            context,
            CharacterDataBase::class.java,
            CHARACTER_DATABASE
        ).build()
    }

    @Provides
    @Singleton
    fun provideInMemoryDatabase(
        @ApplicationContext context: Context
    ): CharacterDataBase {
        return Room.inMemoryDatabaseBuilder(
            context,
            CharacterDataBase::class.java
        ).build()
    }
}