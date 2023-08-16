package com.example.tasks.app.data.datasource.remote

import com.example.tasks.app.data.api.SourceApi
import com.example.tasks.app.domain.datasource.remote.SourceDataSource
import com.example.tasks.app.domain.dto.response.source.SourceRes
import javax.inject.Inject

class SourceDataSourceImpl @Inject constructor(
    private val sourceApi: SourceApi
) : SourceDataSource {

    override suspend fun getSourceByCategory(): SourceRes =
        sourceApi.getSource()

}