package com.example.filmesesries.components

import android.text.Layout.Alignment
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun MovieAppBar(
    title: String,
    icon:ImageVector? = null,
    icon2:ImageVector? = null,
    isPrincipalOrSettings: Boolean = true,
    color: Color,
    navController: NavController,
    onArrowBackClicked: () -> Unit = {  }
) {

    TopAppBar(title = {


        //Row( verticalAlignment = Alignment.CenterVertically ) {

        if (icon != null) {
            Icon(imageVector = icon, contentDescription = "Arrow Back" ,
                modifier = Modifier.clickable {

                    onArrowBackClicked.invoke()

                })


        }



        Spacer(modifier = Modifier.width( 40.dp ) )

        Text(text = title,
            style = TextStyle( fontWeight = FontWeight.Bold, fontSize = 20.sp )
        )

        Spacer(modifier = Modifier.width( 190.dp ) )

        if ( isPrincipalOrSettings ) {

            if (icon2 != null) {
                Icon(imageVector = icon2, contentDescription = "Principal ou Settings" ,
                )
            }


        }





        //}

    },

        actions = {

            IconButton(onClick = { /*TODO*/ }) {

            }

        },
        backgroundColor = color,
        elevation = 0.dp)

}