package com.example.tasks.ext.constant

import com.example.tasks.BuildConfig

object Const {
    // AppConstant
    const val APP_DB_NAME      = BuildConfig.APPLICATION_ID + ".db"
    const val PREFS_NAME       = "wanna_prefs"
    const val NULL_INDEX       = -1L

    const val VIEW_TYPE_EMPTY  = 0
    const val VIEW_TYPE_NORMAL = 1

    // Time
    const val TIMESTAMP_FORMAT               = "yyyy-MM-dd HH:mm:ss"
    const val SERVER_TIME_FORMAT             = "yyyy-MM-dd"
    const val LOCAL_TIME_FORMAT              = "HH:mm"
    const val LOCAL_DATE_FORMAT              = "dd/MM/yyyy"
    const val LOCAL_MONTH_DATE_FORMAT        = "dd MMMM yyyy"
    const val LOCAL_DATE_SKOLLA_FORMAT       = "dd MMM, yyyy"
    const val LOCAL_DATE_HOUR_SKOLLA_FORMAT  = "dd MMM, yyyy HH:mm"
    const val LOCAL_HOUR_SKOLLA_FORMAT       = "HH:mm"
    const val LOCAL_SIMPLE_MONTH_DATE_FORMAT = "dd MMM yyyy"
    const val LOCAL_DATE_TIME_FORMAT         = "dd MMMM yyyy HH:mm:ss"
    const val LOCAL_MONTH_HOUR_DATE_FORMAT   = "dd MMMM yyyy"
    const val DUEL_DATE_FORMAT               = "dd MMMM yyyy | HH:mm"
    const val LOCAL_DATE_AND_TIME            = "yyyy.MM.dd G 'at' HH:mm:ss"

    const val LIMIT_PAGINATION                    = 10
    const val COUNTDOWN_INTERVAL                  = 1000L

    const val STYLE_CSS = "style/style.css"


    enum class LoggedInMode constructor(val type: Int) {
        LOGGED_IN_MODE_LOGGED_OUT(1),
        LOGGED_IN_MODE_SERVER(2),
    }
}