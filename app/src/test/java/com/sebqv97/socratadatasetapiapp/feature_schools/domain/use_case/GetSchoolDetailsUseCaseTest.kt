package com.sebqv97.socratadatasetapiapp.feature_schools.domain.use_case

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sebqv97.socratadatasetapiapp.core.util.ErrorTypes
import com.sebqv97.socratadatasetapiapp.core.util.Resource
import com.sebqv97.socratadatasetapiapp.feature_schools.data.remote.dto.schools.SchoolsDtoItem
import com.sebqv97.socratadatasetapiapp.feature_schools.domain.repository.SchoolsRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.whenever
import retrofit2.Response
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class GetSchoolDetailsUseCaseTest {

    private lateinit var getSchoolDetailsUseCase: GetSchoolDetailsUseCase
    private val testDispatcher = StandardTestDispatcher()

    @Mock
    private lateinit var repository: SchoolsRepository

    @get:Rule
//tell the application to run the tests INSTANTLY, HIGH PRIORITY
    val rule: TestRule = InstantTaskExecutorRule()


    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher = testDispatcher)
        MockitoAnnotations.openMocks(this)
        getSchoolDetailsUseCase = GetSchoolDetailsUseCase(repository)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `simulate Internet Connection Problem Expect Failed Status`() {
        runBlocking {
            whenever(repository.getSaTResultsForSchool("Fail")).doAnswer{throw IOException() }

            //collect the response
            getSchoolDetailsUseCase("Fail").filter { it is Resource.Error }
                .collect {
                    assertTrue(it is Resource.Error)
                    assertEquals(
                        "Couldn't reach server... Check your Internet Connection",
                        (it.errorType as ErrorTypes.InternetConnectionFailed).message
                    )
                }


        }
    }

    @Test
    fun `simulate Http Request Problem Expect Failed Status`() {
        runBlocking {
            whenever(repository.getSaTResultsForSchool("Fail")).thenReturn(Response.error(400,"".toResponseBody()))

            //collect the response
            getSchoolDetailsUseCase("Fail").filter { it is Resource.Error }
                .collect {
                    assertTrue(it is Resource.Error)
                    assertEquals(400, (it.errorType as ErrorTypes.ProblematicHttpRequest).code)
                }


        }
    }
    @Test
    fun `simulate coroutine problem Expect Failed Status`() {
        runBlocking {
            whenever(repository.getSaTResultsForSchool("Fail")).doAnswer{throw CancellationException("Sebaaaa")}

            //collect the response
            getSchoolDetailsUseCase("Fail").filter { it is Resource.Error }
                .collect {
                    assertTrue(it is Resource.Error)
                    assertTrue(it.errorType is ErrorTypes.JobCancellationError)
                }


        }
    }

    @Test
    fun `simulate success Response with empty Body Expect ERROR`(){
        runBlocking {
            whenever(repository.getSaTResultsForSchool("Fail")).thenReturn(Response.success(null))

            //Even though it is a success, due to empty body, an error should come
            getSchoolDetailsUseCase("Fail").filter { it is Resource.Error}
                .collect{
                    assertTrue(it is Resource.Error)
                    assertTrue(it.errorType is ErrorTypes.EmptyQuery)


                }
        }
    }

    @Test
    fun `Simulate Success Response With Data in Body EXPECT Success Status`(){
        runBlocking {
            whenever(repository.getSaTResultsForSchool("Fail")).thenReturn(Response.success(listOf()))
            getSchoolDetailsUseCase("Fail").filter { it is Resource.Success }
                .collect{
                    assertTrue(it is Resource.Success)
                    assertEquals(emptyList<SchoolsDtoItem>(),it.data)
                }
        }
    }
}