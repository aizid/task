package com.example.tasks.app.di

import com.example.tasks.app.TaskApp
import com.example.tasks.app.data.api.*
import com.example.tasks.app.data.datasource.remote.*
import com.example.tasks.app.data.datasource.stub.*
import com.example.tasks.app.domain.datasource.remote.*
import com.example.tasks.app.domain.datasource.stub.*
import com.example.tasks.ext.stub.StubUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    /** Source DataSource */
    @Singleton
    @Provides
    fun provideSourceStubDataSource(
        myApplication: TaskApp,
        stubUtil: StubUtil
    ): SourceStubDataSource = SourceStubDataSourceImpl(
        myApplication.applicationContext, stubUtil)

    @Singleton
    @Provides
    fun provideSourceDataSource(
        sourceApi: SourceApi
    ): SourceDataSource = SourceDataSourceImpl(sourceApi)

}