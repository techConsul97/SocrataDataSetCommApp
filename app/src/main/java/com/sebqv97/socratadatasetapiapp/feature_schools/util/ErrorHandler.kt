package com.sebqv97.socratadatasetapiapp.feature_schools.util

import com.sebqv97.socratadatasetapiapp.core.util.ErrorTypes

fun getWordsUseCaseErrorHandler(errorType: ErrorTypes):String{
    //check which type of error was triggered
    return when(errorType){
        is ErrorTypes.ApiQueryTypeError -> errorType.message
        is ErrorTypes.InternetConnectionFailed -> errorType.message
        is ErrorTypes.EmptyQuery -> errorType.message
        is ErrorTypes.ProblematicHttpRequest-> errorType.message
        is ErrorTypes.EmptySearchField-> errorType.message
        is ErrorTypes.DBInsertionSuccessRetrievingFailed -> errorType.message
        else -> "Unexpected Error"
    }
}