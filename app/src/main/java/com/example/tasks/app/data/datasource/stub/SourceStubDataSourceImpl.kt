package com.example.tasks.app.data.datasource.stub

import android.content.Context
import com.example.tasks.R
import com.example.tasks.app.domain.datasource.stub.SourceStubDataSource
import com.example.tasks.app.domain.dto.response.source.Source
import com.example.tasks.app.domain.dto.response.source.SourceRes
import com.example.tasks.app.domain.entities.SourceModel
import com.example.tasks.ext.stub.StubUtil
import javax.inject.Inject

class SourceStubDataSourceImpl @Inject constructor(
    private val context: Context,
    private val stubUtil: StubUtil
): SourceStubDataSource {

    override suspend fun getSourceByCategory(): SourceRes =
        stubUtil.parseInto(
            jsonString = stubUtil.getJsonFromRaw(context, R.raw.stub_source),
            classOfT = SourceRes::class.java,
            defaultObject = SourceRes(
                "Default Dummy Data", listOf<Source>()
            )
        )!!

}