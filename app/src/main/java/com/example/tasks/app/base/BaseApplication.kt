package com.example.tasks.app.base

import android.app.Application

abstract class BaseApplication: Application() {
    abstract fun getBaseUrl(): String
}