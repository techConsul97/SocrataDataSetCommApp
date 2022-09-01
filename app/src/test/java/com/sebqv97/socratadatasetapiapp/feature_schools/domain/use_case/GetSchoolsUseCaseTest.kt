package com.sebqv97.socratadatasetapiapp.feature_schools.domain.use_case

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sebqv97.socratadatasetapiapp.core.util.ErrorTypes
import com.sebqv97.socratadatasetapiapp.core.util.Resource
import com.sebqv97.socratadatasetapiapp.feature_schools.domain.repository.SchoolsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
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
import kotlin.coroutines.cancellation.CancellationException

@OptIn(ExperimentalCoroutinesApi::class)
class GetSchoolsUseCaseTest {

    private lateinit var getSchoolsUseCase: GetSchoolsUseCase
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
        getSchoolsUseCase = GetSchoolsUseCase(repository)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `simulate Internet Connection Problem Expect Failed Status`() {
        runBlocking {
            whenever(repository.getSchoolsFromApi()).doAnswer{throw IOException()}

            //collect the response
            getSchoolsUseCase().filter { it is Resource.Error }
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
            whenever(repository.getSchoolsFromApi()).thenReturn(Response.error(400,"".toResponseBody()))

            //collect the response
            getSchoolsUseCase().filter { it is Resource.Error }
                .collect {
                    assertTrue(it is Resource.Error)
                    assertEquals(400, (it.errorType as ErrorTypes.ProblematicHttpRequest).code)
                }


        }
    }
    @Test
    fun `simulate coroutine problem Expect Failed Status`() {
        runBlocking {
            whenever(repository.getSchoolsFromApi()).doAnswer{throw CancellationException("Sebaaaa")}

            //collect the response
            getSchoolsUseCase().filter { it is Resource.Error }
                .collect {
                    assertTrue(it is Resource.Error)
                    assertTrue(it.errorType is ErrorTypes.JobCancellationError)
                }


        }
    }


}

