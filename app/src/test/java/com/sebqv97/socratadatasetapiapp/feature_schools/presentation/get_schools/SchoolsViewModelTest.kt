package com.sebqv97.socratadatasetapiapp.feature_schools.presentation.get_schools

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sebqv97.socratadatasetapiapp.feature_schools.data.remote.SchoolsApi
import com.sebqv97.socratadatasetapiapp.feature_schools.data.repository.SchoolsRepositoryImpl
import com.sebqv97.socratadatasetapiapp.feature_schools.domain.repository.SchoolsRepository
import com.sebqv97.socratadatasetapiapp.feature_schools.domain.use_case.GetSchoolsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
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
class SchoolsViewModelTest {
    private  var schoolsViewModel:SchoolsViewModel? = null
    private var schoolsRepository: SchoolsRepository? = null
    private var getSchoolsUseCase:GetSchoolsUseCase? = null


    @Mock
    private lateinit var schoolsApi: SchoolsApi
    private val testDispatcher = UnconfinedTestDispatcher()


    @get:Rule
//tell the application to run the tests INSTANTLY, HIGH PRIORITY
    val rule: TestRule = InstantTaskExecutorRule()



    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.openMocks(this)
        schoolsRepository = SchoolsRepositoryImpl(schoolsApi)
        getSchoolsUseCase = GetSchoolsUseCase(schoolsRepository!!)

    }

    @After
    fun tearDown() {
        schoolsRepository = null
        getSchoolsUseCase = null
        schoolsViewModel = null

        Dispatchers.resetMain()
    }

    @Test
    fun `confirm first state is loading`(){
        runBlocking {
            schoolsViewModel = SchoolsViewModel(getSchoolsUseCase!!)
            val state = schoolsViewModel!!.getSchoolsState.value
            assertTrue(state.isLoading)
        }
    }

    @Test
    fun `get Error Response from Api Expect Error State`(){
        runBlocking {
            whenever(schoolsApi.getNYCSchoolsDataFromApi()).thenReturn(Response.error(400,"".toResponseBody()))
            schoolsViewModel = SchoolsViewModel(getSchoolsUseCase!!)
            while (schoolsViewModel!!.getSchoolsState.value.isLoading){}
            val state = schoolsViewModel!!.getSchoolsState.value
            assertNotEquals(state.encounteredError,null)


        }
    }
}