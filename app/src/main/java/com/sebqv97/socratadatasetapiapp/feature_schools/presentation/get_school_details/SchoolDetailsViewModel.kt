package com.sebqv97.socratadatasetapiapp.feature_schools.presentation.get_school_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebqv97.socratadatasetapiapp.core.util.Resource
import com.sebqv97.socratadatasetapiapp.feature_schools.Constants
import com.sebqv97.socratadatasetapiapp.feature_schools.domain.model.SchoolModel
import com.sebqv97.socratadatasetapiapp.feature_schools.domain.use_case.GetSchoolDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SchoolDetailsViewModel @Inject constructor(
   private val getSchoolDetailsUseCase: GetSchoolDetailsUseCase,
   savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _getSchoolDetailsState = mutableStateOf(value = GetSchoolDetailsState())
    val getSchoolDetailsState : State<GetSchoolDetailsState> = _getSchoolDetailsState

    init {
        savedStateHandle.get<String>(Constants.PARAM_SCHOOL_NAME)?.let { pressedSchool->
            getSchools(pressedSchool)
        }

    }

    private fun getSchools(tappedSchool:String) {
        viewModelScope.launch {
            getSchoolDetailsUseCase(tappedSchool = tappedSchool).collect{ collectedResource ->
                when(collectedResource){
                    is Resource.Loading -> {_getSchoolDetailsState.value = GetSchoolDetailsState(isLoading = true)
                    }
                    is Resource.Error -> {_getSchoolDetailsState.value = GetSchoolDetailsState(encounteredError = collectedResource.errorType)
                    }
                    is Resource.Success -> {_getSchoolDetailsState.value = GetSchoolDetailsState(schoolDetails = collectedResource.data!!)
                    }
                }
            }
        }
    }
}