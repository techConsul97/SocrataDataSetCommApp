package com.sebqv97.socratadatasetapiapp.feature_schools.presentation.get_schools

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sebqv97.socratadatasetapiapp.feature_schools.presentation.Screens
import com.sebqv97.socratadatasetapiapp.feature_schools.presentation.get_schools.component.SchoolElement
import com.sebqv97.socratadatasetapiapp.feature_schools.util.getWordsUseCaseErrorHandler


@Composable
fun SchoolsScreen(
    navController: NavController,
    modifier: Modifier,
    viewModel: SchoolsViewModel = hiltViewModel()
){
    val state = viewModel.getSchoolsState.value

    Box(modifier = modifier
        .fillMaxSize()
        .padding(12.dp)
    ){
        LazyColumn(contentPadding = PaddingValues(4.dp), verticalArrangement = Arrangement.spacedBy(6.dp)){
            items(state.schools){school ->
                SchoolElement(
                    modifier = modifier.border(BorderStroke(2.dp, Color.LightGray)),
                    school = school,
                    onSchoolClicked = {
                        navController.navigate(Screens.SchoolDetailsScreen.route + "/${school.dbn}")
                    })

            }
        }

        if(state.encounteredError != null){

            val errorMessage = getWordsUseCaseErrorHandler(state.encounteredError)
            Text(
                text = errorMessage,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.error,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )

        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = modifier.align(Alignment.Center))

        }
    }


}