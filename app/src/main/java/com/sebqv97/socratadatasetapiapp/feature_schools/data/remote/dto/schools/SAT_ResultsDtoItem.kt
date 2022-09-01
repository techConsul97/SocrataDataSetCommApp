package com.sebqv97.socratadatasetapiapp.feature_schools.data.remote.dto.schools


import com.google.gson.annotations.SerializedName
import com.sebqv97.socratadatasetapiapp.feature_schools.domain.model.SchoolDetailsModel

data class SAT_ResultsDtoItem(
    @SerializedName("dbn")
    val dbn: String,
    @SerializedName("num_of_sat_test_takers")
    val numOfSatTestTakers: String,
    @SerializedName("sat_critical_reading_avg_score")
    val satCriticalReadingAvgScore: String,
    @SerializedName("sat_math_avg_score")
    val satMathAvgScore: String,
    @SerializedName("sat_writing_avg_score")
    val satWritingAvgScore: String,
    @SerializedName("school_name")
    val schoolName: String
){
    fun toSAT_ResultsModel():SchoolDetailsModel = SchoolDetailsModel(
        dbn = dbn,
        numOfSatTestTakers = numOfSatTestTakers,
        satCriticalReadingAvgScore = satCriticalReadingAvgScore,
        satMathAvgScore = satMathAvgScore,
        satWritingAvgScore = satWritingAvgScore,
        schoolName = schoolName
    )
}