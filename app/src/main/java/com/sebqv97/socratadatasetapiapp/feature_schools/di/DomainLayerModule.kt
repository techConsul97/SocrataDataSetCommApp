package com.sebqv97.socratadatasetapiapp.feature_schools.di

import com.sebqv97.socratadatasetapiapp.feature_schools.data.remote.SchoolsApi
import com.sebqv97.socratadatasetapiapp.feature_schools.data.repository.SchoolsRepositoryImpl
import com.sebqv97.socratadatasetapiapp.feature_schools.domain.repository.SchoolsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DomainLayerModule {

    @Provides
    fun provideSchoolsRepository(api: SchoolsApi):SchoolsRepository = SchoolsRepositoryImpl(api)

}