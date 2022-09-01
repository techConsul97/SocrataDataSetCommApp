package com.sebqv97.socratadatasetapiapp.feature_schools.data.repository

import com.sebqv97.socratadatasetapiapp.feature_schools.data.remote.SchoolsApi
import com.sebqv97.socratadatasetapiapp.feature_schools.data.remote.dto.schools.SAT_ResultsDtoItem
import com.sebqv97.socratadatasetapiapp.feature_schools.data.remote.dto.schools.SchoolsDtoItem
import com.sebqv97.socratadatasetapiapp.feature_schools.domain.repository.SchoolsRepository
import retrofit2.Response
import javax.inject.Inject

class SchoolsRepositoryImpl @Inject constructor(
    private val schoolsApi: SchoolsApi
) :SchoolsRepository{
    override suspend fun getSchoolsFromApi(): Response<List<SchoolsDtoItem>> {
        return schoolsApi.getNYCSchoolsDataFromApi()
    }

    override suspend fun getSaTResultsForSchool(givenSchool: String): Response<List<SAT_ResultsDtoItem>> {
        return schoolsApi.getSAT_ResultForGivenShool(givenSchoolName = givenSchool)
    }


}