package com.example.tasks.app.di

import androidx.lifecycle.ViewModel
import com.example.tasks.app.base.viewmodel.ViewModelKey
import com.example.tasks.app.feature.activity.main.MainViewModel
import com.example.tasks.app.feature.activity.middle.MiddleViewModel
import com.example.tasks.app.feature.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
abstract class ViewModelModule {

    /**
    ACTIVITY VIEW MODEL
     */

    @Binds
    @IntoMap
    @ViewModelScoped
    @ViewModelKey(MainViewModel::class)
    abstract fun provideMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelScoped
    @ViewModelKey(MiddleViewModel::class)
    abstract fun provideMiddleViewModel(viewModel: MiddleViewModel): ViewModel

    /**
    FRAGMENT VIEW MODEL
     */

    @Binds
    @IntoMap
    @ViewModelScoped
    @ViewModelKey(HomeViewModel::class)
    abstract fun provideHomeViewModel(viewModel: HomeViewModel): ViewModel

//    @Binds
//    @IntoMap
//    @ViewModelScoped
//    @ViewModelKey(AboutViewModel::class)
//    abstract fun provideAboutViewModel(viewModel: AboutViewModel): ViewModel


    /**
     DIALOG VIEW MODEL
     */

//    @Binds
//    @IntoMap
//    @ViewModelScoped
//    @ViewModelKey(DialogInfoBottomViewModel::class)
//    abstract fun provideDialogInfoBottomViewModel(viewModel: DialogInfoBottomViewModel): ViewModel

}