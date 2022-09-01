package com.sebqv97.socratadatasetapiapp.feature_schools.domain.use_case

import com.sebqv97.socratadatasetapiapp.core.util.ErrorTypes
import com.sebqv97.socratadatasetapiapp.core.util.Resource
import com.sebqv97.socratadatasetapiapp.feature_schools.domain.model.SchoolDetailsModel
import com.sebqv97.socratadatasetapiapp.feature_schools.domain.repository.SchoolsRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.util.concurrent.CancellationException
import javax.inject.Inject


class GetSchoolDetailsUseCase @Inject constructor(
    private val  schoolsRepository: SchoolsRepository
){

    operator fun invoke(tappedSchool:String):Flow<Resource<SchoolDetailsModel>> = flow {

        try {
            emit(Resource.Loading())
            coroutineScope {
                delay(1000)
                val response = async {
                    schoolsRepository.getSaTResultsForSchool(givenSchool = tappedSchool)
                }.await()
                if(response.isSuccessful){
                    if(response.body()!=null && response.body()!!.isNotEmpty()){
                        response.body()?.let { list ->
                            emit(Resource.Success(list[0].toSAT_ResultsModel()))
                        }
                    }else{
                        emit(Resource.Error(ErrorTypes.EmptyQuery()))
                    }

                }
            }



        }catch (e:IOException){
            emit(Resource.Error(ErrorTypes.InternetConnectionFailed()))

        }catch (e:CancellationException){
            val cause = e.cause.toString()
            emit(Resource.Error(ErrorTypes.JobCancellationError(cause)))
        }


    }


}