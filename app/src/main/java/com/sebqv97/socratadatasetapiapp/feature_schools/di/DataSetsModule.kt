package com.sebqv97.socratadatasetapiapp.feature_schools.di

import com.sebqv97.socratadatasetapiapp.feature_schools.data.remote.SchoolsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataSetsModule {
    @Singleton
    @Provides
    fun provideSchoolApi(retrofit:Retrofit):SchoolsApi = retrofit.create(SchoolsApi::class.java)
}