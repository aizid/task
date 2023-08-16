package com.example.tasks.app.data.dao

import androidx.room.*
import com.example.tasks.app.base.db.DBEntity
import com.example.tasks.app.data.entity.SourceObject
import kotlinx.coroutines.flow.Flow

@Dao
interface SourceDao {

    // PROFILE
    @Transaction
    @Query("SELECT * FROM ${DBEntity.ENTITY_SOURCE} ORDER BY id ASC LIMIT 1")
    fun getSaveNewsFlow(): Flow<List<SourceObject>>
    @Transaction
    @Query("SELECT * FROM ${DBEntity.ENTITY_SOURCE} ORDER BY id ASC LIMIT 1")
    fun getSaveNews(): List<SourceObject>



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSaveNews(sourceObject: SourceObject)

}