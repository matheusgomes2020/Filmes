package com.example.filmesesries.components

import android.text.Layout.Alignment
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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
    onArrowBackClicked: () -> Unit = {  },
    onIconClicked: () -> Unit = {  }
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

        //Spacer(modifier = Modifier.width( 170.dp ) )







        //}

    },

        actions = {

            if ( isPrincipalOrSettings ) {

                if (icon2 != null) {
                    Icon(imageVector = icon2, contentDescription = "Principal ou Settings" ,
                        modifier = Modifier.clickable {

                            onIconClicked.invoke()


                        }
                    )
                }


            }

        },
        backgroundColor = color,
        elevation = 0.dp)

}
@Composable
fun InputField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    labelId: String,
    enabled: Boolean,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {

    OutlinedTextField(value = valueState.value ,
        onValueChange = {

            valueState.value = it

        },
        label = { Text(text = labelId) },
        singleLine = isSingleLine,
        textStyle = TextStyle( fontSize = 18.sp,
            color = MaterialTheme.colors.onBackground ),
        modifier = modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        enabled = enabled,
        keyboardOptions = KeyboardOptions( keyboardType = keyboardType, imeAction = imeAction )
    )


}
