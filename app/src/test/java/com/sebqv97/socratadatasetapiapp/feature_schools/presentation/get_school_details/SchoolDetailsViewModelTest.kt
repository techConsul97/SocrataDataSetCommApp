package com.sebqv97.socratadatasetapiapp.feature_schools.presentation.get_school_details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.sebqv97.socratadatasetapiapp.feature_schools.data.remote.SchoolsApi
import com.sebqv97.socratadatasetapiapp.feature_schools.data.repository.SchoolsRepositoryImpl
import com.sebqv97.socratadatasetapiapp.feature_schools.domain.repository.SchoolsRepository
import com.sebqv97.socratadatasetapiapp.feature_schools.domain.use_case.GetSchoolDetailsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class SchoolDetailsViewModelTest {
    private var schoolsViewModel: SchoolDetailsViewModel? = null
    private var schoolsRepository: SchoolsRepository? = null
    private var getSchoolsUseCase: GetSchoolDetailsUseCase? = null


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
        getSchoolsUseCase = GetSchoolDetailsUseCase(schoolsRepository!!)

    }

    @After
    fun tearDown() {
        schoolsRepository = null
        getSchoolsUseCase = null
        schoolsViewModel = null

        Dispatchers.resetMain()
    }

    @Test
    fun `confirm first state is loading`() {
        runBlocking {
            schoolsViewModel = SchoolDetailsViewModel(
                getSchoolsUseCase!!, savedStateHandle = SavedStateHandle(
                    mapOf("dbn" to "seba")
                )
            )
            val state = schoolsViewModel!!.getSchoolDetailsState.value
            assertTrue(state.isLoading)
        }
    }
}

