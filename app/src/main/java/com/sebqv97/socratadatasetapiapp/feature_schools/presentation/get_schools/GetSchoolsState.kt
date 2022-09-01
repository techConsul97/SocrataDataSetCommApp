package com.sebqv97.socratadatasetapiapp.feature_schools.presentation.get_schools

import com.sebqv97.socratadatasetapiapp.core.util.ErrorTypes
import com.sebqv97.socratadatasetapiapp.feature_schools.domain.model.SchoolModel

data class GetSchoolsState(
    val isLoading: Boolean = false,
    val schools : List<SchoolModel> = emptyList(),
    val encounteredError:ErrorTypes? = null
)
