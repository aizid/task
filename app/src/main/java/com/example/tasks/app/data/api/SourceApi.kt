package com.example.tasks.app.data.api

import com.example.tasks.app.base.api.ApiEndPoint
import com.example.tasks.app.domain.dto.response.source.SourceRes
import retrofit2.http.GET
import retrofit2.http.Query

interface SourceApi {

    // GET

    @GET(ApiEndPoint.GET_SOURCE_BY_CATEGORY)
    suspend fun getSource(): SourceRes

}