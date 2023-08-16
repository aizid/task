package com.example.tasks.app.domain.interactors

import com.example.tasks.app.domain.FlowUseCase
import com.example.tasks.app.domain.dto.response.source.SourceRes
import com.example.tasks.app.domain.repositories.SourceRepository
import com.example.tasks.app.domain.subscribers.Resource
import kotlinx.coroutines.flow.Flow

class SourceByCategoryUseCase constructor(
    private val repository: SourceRepository
) : FlowUseCase<Nothing?, SourceRes>() {
    override suspend fun execute(parameters: Nothing?): Flow<Resource<SourceRes>> {
        return repository.getSourceByCategory()
    }
}

class SourceByCategoryLocalUseCase constructor(
    private val repository: SourceRepository
) : FlowUseCase<Nothing?, SourceRes>() {
    override suspend fun execute(parameters: Nothing?): Flow<Resource<SourceRes>> {
        return repository.getSourceByCategory()
    }
}