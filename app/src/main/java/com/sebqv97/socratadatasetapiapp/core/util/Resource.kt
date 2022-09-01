package com.sebqv97.socratadatasetapiapp.core.util

sealed class Resource<T>(val data: T? = null, val errorType: ErrorTypes? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(errorType: ErrorTypes, data: T? = null) : Resource<T>(data, errorType)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}