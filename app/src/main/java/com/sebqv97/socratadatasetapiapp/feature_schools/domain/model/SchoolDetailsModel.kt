package com.sebqv97.socratadatasetapiapp.feature_schools.domain.model

import com.google.gson.annotations.SerializedName

data class SchoolDetailsModel(
    val city: String? = null,
    val collegeCareerRate: String?= null,
    val councilDistrict: String?= null,
    val schoolEmail: String?= null,
    val schoolSports: String?= null,
    val specialized: String?= null,
    val startTime: String?= null,
    val stateCode: String?= null,
    val totalStudents: String?= null,

    val dbn: String,
    val numOfSatTestTakers: String,
    val satCriticalReadingAvgScore: String,
    val satMathAvgScore: String,
    val satWritingAvgScore: String,
    val schoolName: String
)