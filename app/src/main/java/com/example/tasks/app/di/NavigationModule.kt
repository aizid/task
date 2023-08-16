package com.example.tasks.app.di

import android.app.Activity
import com.example.tasks.app.base.navigation.NavigationMain
import com.example.tasks.app.base.navigation.NavigationMiddle
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
object NavigationModule {
    @Provides
    fun providesNavigationMainModule(activity: Activity): NavigationMain {
        return NavigationMain(activity)
    }

    @Provides
    fun providesNavigationMidModule(activity: Activity): NavigationMiddle {
        return NavigationMiddle(activity)
    }
}