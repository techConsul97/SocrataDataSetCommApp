package com.sebqv97.socratadatasetapiapp.feature_schools.presentation.get_school_details

import com.sebqv97.socratadatasetapiapp.core.util.ErrorTypes
import com.sebqv97.socratadatasetapiapp.feature_schools.domain.model.SchoolDetailsModel

data class GetSchoolDetailsState(
    val isLoading: Boolean = false,
    val schoolDetails : SchoolDetailsModel? = null,
    val encounteredError : ErrorTypes? = null
)