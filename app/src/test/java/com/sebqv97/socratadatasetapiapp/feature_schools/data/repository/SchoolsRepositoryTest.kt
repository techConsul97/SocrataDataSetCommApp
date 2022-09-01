package com.sebqv97.socratadatasetapiapp.feature_schools.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sebqv97.socratadatasetapiapp.feature_schools.data.remote.SchoolsApi
import com.sebqv97.socratadatasetapiapp.feature_schools.data.remote.dto.schools.SAT_ResultsDtoItem
import com.sebqv97.socratadatasetapiapp.feature_schools.data.remote.dto.schools.SchoolsDtoItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class SchoolsRepositoryTest {
private lateinit var schoolsRepository: SchoolsRepositoryImpl
private val testDispatcher = StandardTestDispatcher()

@Mock
private lateinit var schoolApi:SchoolsApi


@get:Rule
//tell the application to run the tests INSTANTLY, HIGH PRIORITY
val rule: TestRule = InstantTaskExecutorRule()


    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher = testDispatcher)
        MockitoAnnotations.openMocks(this)
        schoolsRepository = SchoolsRepositoryImpl((schoolApi))
    }

    @After
    fun teardown(){
        Dispatchers.resetMain()
    }

   @Test

   fun `simulate Internet Connection Problem Expect Failed Status`(){
       runBlocking {
           whenever(schoolApi.getNYCSchoolsDataFromApi()).thenReturn(Response.error(12002,"Request timed out".toResponseBody()))
           whenever(schoolApi.getSAT_ResultForGivenShool("FAIL")).thenReturn(Response.error(12002,"Request timed out".toResponseBody()))

            val response1 = schoolsRepository.getSchoolsFromApi()
           assertEquals(12002, response1.code())

           //Now, let's test the second API
           val response2 = schoolsRepository.getSaTResultsForSchool("FAIL")
           assertEquals(12002,response2.code())
       }
   }

    @Test
    fun `simulate Bad Request Expect Failed Status`(){
        runBlocking {
            whenever(schoolApi.getNYCSchoolsDataFromApi()).thenReturn(Response.error(401,"Bad Request".toResponseBody()))
            whenever(schoolApi.getSAT_ResultForGivenShool("FAIL")).thenReturn(Response.error(401,"Request timed out".toResponseBody()))

            val response1 = schoolsRepository.getSchoolsFromApi()
            assertEquals(401, response1.code())

            //Now, let's test the second API
            val response2 = schoolsRepository.getSaTResultsForSchool("FAIL")
            assertEquals(401,response2.code())
        }
    }
    @Test
    fun `simulate Success Request Expect Success Status`(){
        runBlocking{
            whenever(schoolApi.getNYCSchoolsDataFromApi()).thenReturn(Response.success(200, listOf()))
            whenever(schoolApi.getSAT_ResultForGivenShool("Success")).thenReturn(Response.success(200, listOf()))

            val response1 = schoolsRepository.getSchoolsFromApi()
            assertEquals(200, response1.code())
            assertEquals(listOf<SchoolsDtoItem>(),response1.body())

            //Now, let's test the second API
            val response2 = schoolsRepository.getSaTResultsForSchool("Success")
            assertEquals(200,response2.code())
            assertEquals(listOf<SAT_ResultsDtoItem>(),response2.body())
        }
    }

}