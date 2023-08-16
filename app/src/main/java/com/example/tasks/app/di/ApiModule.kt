package com.example.tasks.app.di

import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import android.content.Context
import com.google.gson.GsonBuilder
import com.example.tasks.BuildConfig
import com.example.tasks.app.data.api.SourceApi
import com.example.tasks.app.data.dao.SourceDao
import com.example.tasks.app.data.repositoriesimpl.*
import com.example.tasks.app.domain.datasource.remote.*
import com.example.tasks.app.domain.datasource.stub.*
import com.example.tasks.app.domain.repositories.*
import com.example.tasks.ext.constant.Const
import com.example.tasks.ext.constant.ConstKeys
import com.example.tasks.ext.context.isNetworkAvailable
import com.example.tasks.ext.exeption.NoNetworkException
import com.example.tasks.ext.interceptor.ForbiddenInterceptor
import com.example.tasks.ext.interceptor.ResourceNotFoundInterceptor
import com.example.tasks.ext.other.ErrorCodesMapper
import com.example.tasks.ext.prefs.SharedPreferenceManager
import com.example.tasks.ext.provider.SchedulerProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun providesRetrofit(@ApplicationContext context: Context): Retrofit {
        val gson = GsonBuilder().setDateFormat(Const.SERVER_TIME_FORMAT).create()
        val preferenceManager = SharedPreferenceManager(context)
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ConstKeys.END_POINT)
            .client(buildRetrofitClient(context, preferenceManager))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private fun chuckInterceptor(@ApplicationContext context: Context)
            : ChuckerInterceptor = ChuckerInterceptor.Builder(context)
        .collector(ChuckerCollector(context))
        .maxContentLength(250000L)
        .redactHeaders(emptySet())
        .alwaysReadResponseBody(false)
        .build()

    private fun buildRetrofitClient(context: Context, sharePref: SharedPreferenceManager): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .readTimeout(60L, TimeUnit.SECONDS)
            .connectTimeout(60L, TimeUnit.SECONDS)
            .writeTimeout(60L, TimeUnit.SECONDS)
            .addInterceptor(chuckInterceptor(context))
            .addInterceptor(ForbiddenInterceptor())
            .addInterceptor(ResourceNotFoundInterceptor())
            .addInterceptor(HttpLoggingInterceptor())
            .addInterceptor(Interceptor { chain: Interceptor.Chain ->
                if (context.isNetworkAvailable) {
                    val token: String = ConstKeys.TOKEN
                    val request: Request = chain.request().newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .addHeader("X-Api-Key:", token)
                        .build()
                    return@Interceptor chain.proceed(request)
                } else {
                    throw NoNetworkException()
                }
            })

        if (BuildConfig.DEBUG || BuildConfig.BUILD_TYPE == "debug") {
            val interceptor   = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptor)
            builder.addInterceptor { chain ->
                val request = chain.request()
                val newRequest = request.newBuilder().build()
                chain.proceed(newRequest)
            }
        }
        return builder.build()
    }


    // API
    @Singleton
    @Provides
    fun provideSourceApi(retrofit: Retrofit): SourceApi = retrofit.create(
        SourceApi::class.java
    )



    // REPO
    @Singleton
    @Provides
    fun provideSourceRepository(@ApplicationContext context: Context, source: SourceDataSource, stubSource: SourceStubDataSource, schedulerProvider: SchedulerProvider):
            SourceRepository = SourceRepositoryImpl(source, stubSource, schedulerProvider, ErrorCodesMapper(context))

}