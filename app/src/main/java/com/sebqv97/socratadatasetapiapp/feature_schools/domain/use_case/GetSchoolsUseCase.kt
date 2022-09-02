package com.sebqv97.socratadatasetapiapp.feature_schools.domain.use_case

import com.sebqv97.socratadatasetapiapp.core.util.ErrorTypes
import com.sebqv97.socratadatasetapiapp.core.util.Resource
import com.sebqv97.socratadatasetapiapp.feature_schools.domain.model.SchoolModel
import com.sebqv97.socratadatasetapiapp.feature_schools.domain.repository.SchoolsRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

import java.io.IOException
import java.util.concurrent.CancellationException
import javax.inject.Inject

class GetSchoolsUseCase @Inject constructor(
    private val schoolsRepository: SchoolsRepository
) {
    operator fun invoke(): Flow<Resource<List<SchoolModel>>> = flow {

        try {
            emit(Resource.Loading())
            coroutineScope {
                delay(1000)
                val response = async {
                    schoolsRepository.getSchoolsFromApi()
                }.await()
                if (response.isSuccessful) {
                    if(response.body()!=null) {
                        response.body()?.let { list ->
                            emit(Resource.Success(list.map { it.toSchoolModel() }))
                        }
                    }else{
                        emit(Resource.Error(ErrorTypes.EmptyQuery()))
                    }
                }
                if(!response.isSuccessful){
                    val errorCode = response.code()
                    emit(Resource.Error(ErrorTypes.ProblematicHttpRequest(errorCode)))
                }

            }


        } catch (e: IOException) {
            emit(Resource.Error(ErrorTypes.InternetConnectionFailed()))

        } catch (e: CancellationException) {
            val cause = e.cause.toString()
            emit(Resource.Error(ErrorTypes.JobCancellationError(cause)))
        }

    }


}