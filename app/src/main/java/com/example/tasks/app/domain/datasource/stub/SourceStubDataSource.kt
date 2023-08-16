package com.example.tasks.app.domain.datasource.stub

import com.example.tasks.app.domain.dto.response.source.SourceRes

interface SourceStubDataSource {

    suspend fun getSourceByCategory(): SourceRes
}