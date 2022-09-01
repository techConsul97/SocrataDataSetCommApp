package com.sebqv97.socratadatasetapiapp.feature_schools.data.remote.dto.schools


import com.google.gson.annotations.SerializedName
import com.sebqv97.socratadatasetapiapp.feature_schools.domain.model.SchoolModel

data class SchoolsDtoItem(

    @SerializedName("attendance_rate")
    val attendanceRate: String,
    @SerializedName("bus")
    val bus: String,
    @SerializedName("campus_name")
    val campusName: String?,
    @SerializedName("city")
    val city: String,

    @SerializedName("college_career_rate")
    val collegeCareerRate: String?,
    @SerializedName("council_district")
    val councilDistrict: String?,
    @SerializedName("dbn")
    val dbn: String,

    @SerializedName("end_time")
    val endTime: String?,
    @SerializedName("extracurricular_activities")
    val extracurricularActivities: String?,
    @SerializedName("fax_number")
    val faxNumber: String?,
    @SerializedName("finalgrades")
    val finalgrades: String,

    @SerializedName("location")
    val location: String,




    @SerializedName("school_email")
    val schoolEmail: String?,
    @SerializedName("school_name")
    val schoolName: String,
    @SerializedName("school_sports")
    val schoolSports: String?,

    @SerializedName("specialized")
    val specialized: String?,
    @SerializedName("start_time")
    val startTime: String?,
    @SerializedName("state_code")
    val stateCode: String,

    @SerializedName("total_students")
    val totalStudents: String,
    @SerializedName("transfer")
    val transfer: String?,
    @SerializedName("website")
    val website: String,
    @SerializedName("zip")
    val zip: String
){
    fun toSchoolModel(): SchoolModel = SchoolModel(
        city = city,
        collegeCareerRate = collegeCareerRate,
        councilDistrict = councilDistrict,
        schoolEmail = schoolEmail,
        schoolName = schoolName,
        schoolSports = schoolSports,
        specialized = specialized,
        startTime = startTime,
        stateCode = stateCode,
        totalStudents = totalStudents,
        dbn = dbn
    )
}