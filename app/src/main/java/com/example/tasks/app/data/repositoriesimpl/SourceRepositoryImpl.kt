package com.example.tasks.app.data.repositoriesimpl

import com.example.tasks.app.domain.datasource.remote.SourceDataSource
import com.example.tasks.app.domain.datasource.stub.SourceStubDataSource
import com.example.tasks.app.domain.dto.response.source.SourceRes
import com.example.tasks.app.domain.repositories.SourceRepository
import com.example.tasks.app.domain.subscribers.DataSource
import com.example.tasks.app.domain.subscribers.Resource
import com.example.tasks.ext.other.ErrorCodesMapper
import com.example.tasks.ext.provider.SchedulerProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SourceRepositoryImpl @Inject constructor(
    private val remoteSource: SourceDataSource,
    private val stubDataSource: SourceStubDataSource,
    private val schedulerProvider: SchedulerProvider,
    private val errorCodesMapper: ErrorCodesMapper
) : SourceRepository {

    private var isUsingDummyDataSource = true
    override suspend fun getSourceByCategory(): Flow<Resource<SourceRes>> =
        flow {
            if (isUsingDummyDataSource) {
                emit(Resource.Success(data = stubDataSource.getSourceByCategory(), DataSource.REMOTE))
            } else {
                emit(Resource.Success(data = remoteSource.getSourceByCategory(), DataSource.REMOTE))
            }
        }

}