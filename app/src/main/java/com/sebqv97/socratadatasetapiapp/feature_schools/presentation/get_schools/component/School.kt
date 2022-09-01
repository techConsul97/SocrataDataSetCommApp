package com.sebqv97.socratadatasetapiapp.feature_schools.presentation.get_schools.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sebqv97.socratadatasetapiapp.feature_schools.domain.model.SchoolDetailsModel
import com.sebqv97.socratadatasetapiapp.feature_schools.domain.model.SchoolModel

@Composable
fun SchoolElement(modifier: Modifier,school:SchoolModel,onSchoolClicked:(String)->Unit) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .clickable {
                    onSchoolClicked(school.dbn!!)
                }
                .padding(8.dp)

        ) {
            Text(text = school.schoolName!!,fontSize = 16.sp, fontWeight = FontWeight.W500, fontStyle = FontStyle.Italic, textAlign = TextAlign.Start, textDecoration = TextDecoration.Underline)
        }

    }



