package com.sebqv97.socratadatasetapiapp.feature_schools.presentation



sealed class Screens(val route:String) {
    object SchoolsScreen:Screens("schools_screen")
    object SchoolDetailsScreen:Screens("school_details_screen")
}