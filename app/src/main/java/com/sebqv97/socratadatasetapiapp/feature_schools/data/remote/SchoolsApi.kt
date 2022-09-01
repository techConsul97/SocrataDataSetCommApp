package com.sebqv97.socratadatasetapiapp.feature_schools.data.remote

import com.sebqv97.socratadatasetapiapp.feature_schools.data.remote.dto.schools.SAT_ResultsDtoItem
import com.sebqv97.socratadatasetapiapp.feature_schools.data.remote.dto.schools.SchoolsDtoItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface SchoolsApi {


    @Headers("X-SODA2-Legacy-Types:true")
    @GET(SCHOOLS_RESOURCE_ENDPOINT)
    suspend fun getNYCSchoolsDataFromApi(

    ):Response<List<SchoolsDtoItem>>

    @Headers("X-SODA2-Legacy-Types:true")
    @GET(SAT_RESULTS_ENDPOINT)
    suspend fun getSAT_ResultForGivenShool(@Query("dbn")givenSchoolName:String):Response<List<SAT_ResultsDtoItem>>


    companion object{
        const val BASE_URL = "https://data.cityofnewyork.us/"
        const val SCHOOLS_RESOURCE_ENDPOINT ="resource/s3k6-pzi2.json"
        const val SAT_RESULTS_ENDPOINT = "resource/f9bf-2cp4.json"
    }
}