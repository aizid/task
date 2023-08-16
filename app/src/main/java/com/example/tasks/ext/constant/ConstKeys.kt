package com.example.tasks.ext.constant

import com.example.tasks.BuildConfig

object ConstKeys {
    // Used to load the 'native-lib' library on application startup.
    init  {
        System.loadLibrary("native-lib");
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */

    private external fun getEndPoint(environment: Int): String
    private external fun getSaltKey(environment: Int): String
    private external fun getPreferenceName(environment: Int): String
    private external fun getToken(environment: Int): String

    // CONST
    private const  val ENVIRONMENT = BuildConfig.SERVER_STAGING
    val END_POINT = getEndPoint(ENVIRONMENT)
    val SALT_KEY = getSaltKey(ENVIRONMENT)
    val PREFERENCE_NAME = getPreferenceName(ENVIRONMENT)
    val TOKEN = getToken(ENVIRONMENT)
}