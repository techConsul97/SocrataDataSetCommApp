package com.sebqv97.socratadatasetapiapp.feature_schools.presentation.get_schools

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import androidx.navigation.NavController
import com.sebqv97.socratadatasetapiapp.core.util.Resource
import com.sebqv97.socratadatasetapiapp.feature_schools.domain.model.SchoolModel
import com.sebqv97.socratadatasetapiapp.feature_schools.domain.use_case.GetSchoolsUseCase
import com.sebqv97.socratadatasetapiapp.feature_schools.presentation.ui.theme.SocrataDataSetApiAppTheme
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import kotlinx.coroutines.flow.flow
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class SchoolsScreenKtTest {

    @Mock
    private lateinit var navController: NavController
    private lateinit var viewModel: SchoolsViewModel

    @Mock
    private lateinit var getSchoolsUseCase:GetSchoolsUseCase


    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
       MockitoAnnotations.openMocks(this)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testProgramStartLogic(){
        composeTestRule.setContent {
           whenever(getSchoolsUseCase()).thenReturn(flow{Resource.Loading<List<SchoolModel>>()})
            viewModel = SchoolsViewModel(getSchoolsUseCase)
            composeTestRule.setContent { 
                SchoolsScreen(navController = navController, modifier = Modifier )
            }
            composeTestRule.onRoot(useUnmergedTree = true).printToLog("iaiaia")
            

        }
    }
}