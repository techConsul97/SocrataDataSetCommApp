package com.sebqv97.socratadatasetapiapp.feature_schools.presentation.get_schools

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebqv97.socratadatasetapiapp.core.util.Resource
import com.sebqv97.socratadatasetapiapp.feature_schools.domain.use_case.GetSchoolsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SchoolsViewModel @Inject constructor(
    private val getSchoolsUseCase: GetSchoolsUseCase
):ViewModel() {

    private val _getSchoolsState = mutableStateOf(value = GetSchoolsState())
    val getSchoolsState : State<GetSchoolsState> = _getSchoolsState

    init {
        getSchools()
    }

    private fun getSchools() {
       viewModelScope.launch {
            getSchoolsUseCase().collect{ collectedResource ->
                when(collectedResource){
                    is Resource.Loading -> {_getSchoolsState.value = GetSchoolsState(isLoading = true)
                    }
                    is Resource.Error -> {_getSchoolsState.value = GetSchoolsState(encounteredError = collectedResource.errorType)
                    }
                    is Resource.Success -> {_getSchoolsState.value = GetSchoolsState(schools = collectedResource.data!!)}
                }
            }
       }
    }
}