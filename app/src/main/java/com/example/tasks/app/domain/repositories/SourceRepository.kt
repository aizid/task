package com.example.tasks.app.domain.repositories

import com.example.tasks.app.domain.dto.response.source.SourceRes
import com.example.tasks.app.domain.subscribers.Resource
import kotlinx.coroutines.flow.Flow

interface SourceRepository {
    // Remote and cache
    suspend fun getSourceByCategory(): Flow<Resource<SourceRes>>
}