package com.sebqv97.socratadatasetapiapp.feature_schools.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sebqv97.socratadatasetapiapp.feature_schools.presentation.Screens
import com.sebqv97.socratadatasetapiapp.feature_schools.presentation.get_school_details.SchoolDetailsScreen
import com.sebqv97.socratadatasetapiapp.feature_schools.presentation.get_schools.SchoolsScreen
import com.sebqv97.socratadatasetapiapp.feature_schools.presentation.ui.theme.SocrataDataSetApiAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            
            val navController = rememberNavController()
            SocrataDataSetApiAppTheme {
                NavHost(navController = navController, startDestination = Screens.SchoolsScreen.route){
                    composable(route = Screens.SchoolsScreen.route){
                        SchoolsScreen(navController = navController, modifier = Modifier)
                    }
                    composable(route = Screens.SchoolDetailsScreen.route + "/{dbn}"){
                        SchoolDetailsScreen(modifier = Modifier)
                    }
                }
                // A surface container using the 'background' color from the theme




            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SocrataDataSetApiAppTheme {

    }
}