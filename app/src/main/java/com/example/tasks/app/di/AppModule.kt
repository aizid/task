package com.example.tasks.app.di

import android.content.Context
import com.example.tasks.app.TaskApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context)
    : TaskApp = app as TaskApp

    @Singleton
    @Provides
    fun provideContext(@ApplicationContext context: TaskApp)
    : Context = context

}