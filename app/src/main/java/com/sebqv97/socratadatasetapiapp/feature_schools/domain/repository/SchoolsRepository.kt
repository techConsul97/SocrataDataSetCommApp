package com.sebqv97.socratadatasetapiapp.feature_schools.domain.repository

import com.sebqv97.socratadatasetapiapp.feature_schools.data.remote.dto.schools.SAT_ResultsDtoItem
import com.sebqv97.socratadatasetapiapp.feature_schools.data.remote.dto.schools.SchoolsDtoItem
import retrofit2.Response

interface SchoolsRepository {

    suspend fun getSchoolsFromApi():Response<List<SchoolsDtoItem>>
    suspend fun getSaTResultsForSchool(givenSchool:String):Response<List<SAT_ResultsDtoItem>>
}