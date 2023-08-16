package com.example.tasks.app.domain.datasource.remote

import com.example.tasks.app.domain.dto.response.source.SourceRes

interface SourceDataSource {

    suspend fun getSourceByCategory(): SourceRes

}