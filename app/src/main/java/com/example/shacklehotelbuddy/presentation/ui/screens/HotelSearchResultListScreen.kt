package com.example.shacklehotelbuddy.presentation.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.shacklehotelbuddy.R
import com.example.shacklehotelbuddy.data.model.hotel.Hotel
import com.example.shacklehotelbuddy.presentation.HotelViewModel
import com.example.shacklehotelbuddy.ui.theme.SmallHeading
import com.example.shacklehotelbuddy.presentation.ui.theme.largeUnit
import com.example.shacklehotelbuddy.presentation.ui.theme.mediumUnit
import com.example.shacklehotelbuddy.presentation.ui.theme.smallUnit
import com.example.shacklehotelbuddy.ui.theme.MediumSpacer
import com.example.shacklehotelbuddy.ui.theme.ShackleHotelBuddyTheme
import com.example.shacklehotelbuddy.ui.theme.SmallSpacer
import com.example.shacklehotelbuddy.ui.theme.SmallTitle
import com.example.shacklehotelbuddy.ui.theme.SmallTitleBold
import com.example.shacklehotelbuddy.ui.theme.XSmallSpacer


@Composable
fun HotelSearchResultListScreen(hotelViewModel:HotelViewModel){
    val hotelsList=hotelViewModel.hotelsList.collectAsState()

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
                    Icon(imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = ShackleHotelBuddyTheme.colors.black
                        )
                }

                SmallHeading(
                    modifier= Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically),
                    text = stringResource(id = R.string.search_results),
                    textAlign = TextAlign.Center,
                    color = ShackleHotelBuddyTheme.colors.black
                )
            }

            MediumSpacer()

            HotelsSearchResultList(hotelsList.value){
                /*
                So api didnt work and i had no idea how does response look like
                api is throwing 503 constantly
                 */
                //hotelViewModel.fetchHotelDetail(it)
            }

        }

    }

}

@Composable
fun HotelsSearchResultList(list:List<Hotel>,getHotelDetails:(Hotel)->Unit) {
    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        items(list) {
            HotelItem(it){
                getHotelDetails(it)
            }
        }

    }
}

@Composable
fun HotelItem(data:Hotel,getHotelDetails:(Hotel)->Unit){

    Column(modifier=Modifier.padding(top = mediumUnit).clickable {
        getHotelDetails(data)
    }) {
        AsyncImage(
            model = data.hotelImageLink,
            contentDescription = data.hotelName,
            contentScale = ContentScale.FillBounds ,
            modifier  = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(smallUnit))
        )

        SmallSpacer()

        Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().
        padding(end = mediumUnit)) {

            SmallTitleBold(text =data.hotelName ,
                color = ShackleHotelBuddyTheme.colors.black)

            Row {

                Icon(painter = painterResource(id = R.drawable.star),
                    contentDescription ="star" , tint = ShackleHotelBuddyTheme.colors.black)
                SmallTitle(text =data.rating ,
                    color = ShackleHotelBuddyTheme.colors.black)
            }
        }


        XSmallSpacer()

        SmallTitle(text =data.place ,
            color = ShackleHotelBuddyTheme.colors.grayText)

        XSmallSpacer()

        SmallTitle(text ="USD${data.price}" ,
            color = ShackleHotelBuddyTheme.colors.grayText)


    }

}

@Composable
@Preview
fun PreviewHotelSearchResultListScreen(){

    HotelSearchResultListScreen(hiltViewModel())

}