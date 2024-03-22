package com.example.shacklehotelbuddy.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.shacklehotelbuddy.R
import com.example.shacklehotelbuddy.ui.theme.SmallHeading
import com.example.shacklehotelbuddy.presentation.ui.theme.largeUnit
import com.example.shacklehotelbuddy.presentation.ui.theme.smallUnit


@Composable
fun HotelSearchResultListScreen(){
    Box ( modifier = Modifier
        .fillMaxSize()
        .padding(smallUnit),
        contentAlignment = Alignment.Center){

        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color.White)) {

            Row (modifier= Modifier
                .fillMaxWidth()
                .align(Alignment.End)
                .padding(top = largeUnit)){
                IconButton(onClick = {

                }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                }

                SmallHeading(
                    modifier= Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically),
                    text = stringResource(id = R.string.search_results),
                    textAlign = TextAlign.Center,
                )
            }

        }

    }

}

@Composable
fun HotelsSearchResultList() {
    LazyColumn() {
        /*items(mosques.items) {
            MosqueItem(viewMosque = it, onItemClick)
            Box(
                modifier = Modifier
                    .background(Color(0xFF2E3192))
                    .fillMaxWidth()
                    .height(1.dp)
            )
            XSmallSpacer()
        }*/

    }
}

@Composable
@Preview
fun PreviewHotelSearchResultListScreen(){

    HotelSearchResultListScreen()

}