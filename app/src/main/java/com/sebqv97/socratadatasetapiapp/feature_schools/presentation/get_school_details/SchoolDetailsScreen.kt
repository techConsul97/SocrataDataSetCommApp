package com.sebqv97.socratadatasetapiapp.feature_schools.presentation.get_school_details

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sebqv97.socratadatasetapiapp.feature_schools.presentation.Screens
import com.sebqv97.socratadatasetapiapp.feature_schools.util.getWordsUseCaseErrorHandler


@Composable
fun SchoolDetailsScreen(modifier: Modifier, viewModel: SchoolDetailsViewModel = hiltViewModel(),navController: NavController) {
    val state = viewModel.getSchoolDetailsState.value
    val school = state.schoolDetails
Column() {
    UserAppBar(modifier = modifier, navController = navController)

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        if (school != null) {
            Card(
                elevation = 12.dp, shape = RoundedCornerShape(10.dp), modifier = modifier
                    .fillMaxSize()
                    .padding(12.dp)
                    .align(
                        Alignment.Center
                    )
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = school.schoolName,
                        fontWeight = FontWeight.W700,
                        fontStyle = FontStyle.Italic,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "The reading average score",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W400
                        )
                        Spacer(modifier = modifier.heightIn(16.dp))
                        Text(
                            text = school.satCriticalReadingAvgScore,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.W800
                        )

                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "The Math average score",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W400
                        )
                        Spacer(modifier = modifier.heightIn(16.dp))
                        Text(
                            text = school.satMathAvgScore,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.W800
                        )

                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "The Writing average score",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W400
                        )
                        Spacer(modifier = modifier.heightIn(16.dp))
                        Text(
                            text = school.satWritingAvgScore,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.W800
                        )

                    }


                }


            }
        }

        if (state.encounteredError != null) {

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


}

@Composable
fun UserAppBar(modifier: Modifier, navController: NavController) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .background(color = MaterialTheme.colors.primaryVariant)
            .fillMaxWidth()
    ) {
        IconButton(onClick = { navController.navigate(Screens.SchoolsScreen.route) }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Go back to Main Screen Icon",
                tint = Color.White,
                modifier = modifier.align(CenterVertically)
            )

        }


    }

}
