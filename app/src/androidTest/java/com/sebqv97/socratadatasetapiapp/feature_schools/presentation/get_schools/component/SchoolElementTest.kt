package com.sebqv97.socratadatasetapiapp.feature_schools.presentation.get_schools.component

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import com.sebqv97.socratadatasetapiapp.feature_schools.domain.model.SchoolModel
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SchoolElementTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun assignAnEmptySchoolToLayoutCheckIfPropertiesAreSetProperly(){
        val school = SchoolModel(null, null, null, null ,null ,null,null,null,null,null,null)

        composeTestRule.setContent {
            SchoolElement(modifier = Modifier, school = school , onSchoolClicked = {})


        }
        composeTestRule.onNode(hasText("Error!")).assertExists()

    }

    @Test
    fun assignSchoolToLayoutCheckIfPropertiesAreSetProperly(){
        val school = SchoolModel(null, null, null, null ,"seba" ,null,null,null,null,null,null)

        composeTestRule.setContent {
            SchoolElement(modifier = Modifier, school = school , onSchoolClicked = {})


        }
        composeTestRule.onNode(hasText("seba")).assertExists()

    }

}