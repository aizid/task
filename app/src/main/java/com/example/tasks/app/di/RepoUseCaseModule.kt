package com.example.tasks.app.di

import com.example.tasks.app.domain.interactors.*
import com.example.tasks.app.domain.repositories.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepoUseCaseModule {

    // REMOTE USE CASE
    /** Source Interact */
    @Provides
    @ViewModelScoped
    fun provideGetSourceByCategory(
        sourceRepository: SourceRepository
    ): SourceByCategoryUseCase {
        return SourceByCategoryUseCase(sourceRepository)
    }


    // LOCAL USE CASE
    /** Source Interact */
    @Provides
    @ViewModelScoped
    fun provideGetSavedSource(
        sourceRepository: SourceRepository
    ): SourceByCategoryLocalUseCase {
        return SourceByCategoryLocalUseCase(sourceRepository)
    }

}