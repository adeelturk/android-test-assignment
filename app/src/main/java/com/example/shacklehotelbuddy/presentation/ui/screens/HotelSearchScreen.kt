package com.example.shacklehotelbuddy.presentation.ui.screens

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shacklehotelbuddy.R
import com.example.shacklehotelbuddy.core.networks.error.ErrorEntity
import com.example.shacklehotelbuddy.data.model.Duration
import com.example.shacklehotelbuddy.data.model.SearchQuery
import com.example.shacklehotelbuddy.presentation.AppState
import com.example.shacklehotelbuddy.presentation.HotelViewModel
import com.example.shacklehotelbuddy.presentation.rememberAppState
import com.example.shacklehotelbuddy.presentation.ui.HotelAppUiState
import com.example.shacklehotelbuddy.presentation.ui.theme.White
import com.example.shacklehotelbuddy.presentation.ui.theme.mediumUnit
import com.example.shacklehotelbuddy.presentation.ui.theme.smallUnit
import com.example.shacklehotelbuddy.presentation.ui.theme.xSmallUnit
import com.example.shacklehotelbuddy.presentation.utils.UiErrorKeys
import com.example.shacklehotelbuddy.ui.theme.LargeHeading
import com.example.shacklehotelbuddy.ui.theme.MediumSpacer
import com.example.shacklehotelbuddy.ui.theme.MediumTitle
import com.example.shacklehotelbuddy.ui.theme.ShackleHotelBuddyTheme
import com.example.shacklehotelbuddy.ui.theme.SmallTitle
import java.util.Calendar


@Composable
fun HotelSearchScreen(hotelViewModel:HotelViewModel,
                      appState: AppState,navigateToHotelsListScreen:()->Unit) {
    val hotelSearchQuery=hotelViewModel.hotelsSearchQuery.collectAsState()
    val showDialog = hotelViewModel.progressLoading.collectAsState()
    val uiAppState = hotelViewModel.uiAppState.collectAsState()
    val cachedSearchQueryList=hotelViewModel.searchQueryList.collectAsState()
    ProgressDialog(showDialog = showDialog.value) {

        hotelViewModel.showLoading(false)
    }

    HandleError(uiAppState.value,appState,hotelViewModel,navigateToHotelsListScreen)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.background), contentScale = ContentScale.FillWidth
            )
            .padding(horizontal = mediumUnit, vertical = smallUnit),
        contentAlignment = Alignment.Center
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            LargeHeading(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.select_guests_title),
                color = Color.White
            )
            MediumSpacer()
            Card {

                Column {
                    CheckInCheckOutDate(
                        duration = hotelSearchQuery.value.checkInDate,
                        icon = R.drawable.event_upcoming,
                        title = R.string.checkin_date
                    ) {

                        hotelViewModel.updateCheckInDate(it)
                    }
                    Divider(
                        modifier = Modifier
                            .height(1.dp)
                            .fillMaxWidth()
                    )

                    CheckInCheckOutDate(
                        duration = hotelSearchQuery.value.checkoutDate,
                        icon = R.drawable.event_available,
                        title = R.string.checkout_date
                    ) {
                        hotelViewModel.updateCheckoutDate(it)
                    }
                    Divider(
                        modifier = Modifier
                            .height(1.dp)
                            .fillMaxWidth()
                    )

                    PersonCount(R.drawable.person, R.string.adult,
                        hotelSearchQuery.value.adultCount) {
                    hotelViewModel.updateAdultCount(it)
                    }
                    Divider(
                        modifier = Modifier
                            .height(1.dp)
                            .fillMaxWidth()
                    )
                    PersonCount(R.drawable.supervisor_account, R.string.childern, hotelSearchQuery.value.childrenCount) {
                        hotelViewModel.updateChildrenCount(it)
                    }


                }


            }

            MediumTitle(
                text = stringResource(id = R.string.recentSearch),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = mediumUnit),
                color = ShackleHotelBuddyTheme.colors.white
            )

            RecentSearchList(cachedSearchQueryList.value)
            MediumSpacer()

            SearchButton {

                hotelViewModel.search()
            }

        }
    }

}

@Composable
fun HandleError(uiAppState: HotelAppUiState,appState: AppState,hotelViewModel: HotelViewModel,navigateToHotelsListScreen:()->Unit) {
    when (val state = uiAppState) {
        is HotelAppUiState.Error -> {
            when (val error = state.error) {
                is ErrorEntity.FrontEndError -> {
                    if (error.uiErrorKeys == UiErrorKeys.SEARCH_INCOMPLETE_PARAMS) {
                        appState.showSnackbar(stringResource(id = R.string.search_params_error))
                    }
                }

                else -> {

                    appState.showSnackbar(stringResource(id = R.string.something_went_wrong))

                }
            }
        }
        is HotelAppUiState.HotelSearchDataReceived->{

            navigateToHotelsListScreen()
            hotelViewModel.resetAppUiState()
        }
        else -> { }
    }
}

@Composable
fun ProgressDialog(showDialog:Boolean,onDismiss:()->Unit){

    if (showDialog) {
        Dialog(
            onDismissRequest = { onDismiss() },
            DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            Box(
                contentAlignment= Alignment.Center,
                modifier = Modifier
                    .size(100.dp)
                    .background(White, shape = RoundedCornerShape(8.dp))
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun RecentSearchList(searchHistory: List<SearchQuery>) {
        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)) {
            items(searchHistory) {
                SearchResultItem(it.toString())
            }

        }

}

@Composable
fun SearchResultItem(searchHistory: String) {
    Box(modifier = Modifier.padding(vertical = xSmallUnit)) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(horizontal = smallUnit)
        ) {
            Image(
                modifier = Modifier.padding(end = 5.dp),
                painter = painterResource(id = R.drawable.manage_history),
                contentDescription = "checkin date"
            )

            Divider(
                modifier = Modifier
                    .height(30.dp)
                    .width(1.dp)
            )

            SmallTitle(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(horizontal = smallUnit),
                text = searchHistory,
                color = ShackleHotelBuddyTheme.colors.grayText,

                )

        }
    }

}

}

@Composable
fun PersonCount(icon: Int, title: Int,personCount:Int, selectedPersonCount: (Int) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
    ) {

        SearchOptionTitle(this, icon, title)

        Row(
            modifier = Modifier
                .weight(1f),
            horizontalArrangement = Arrangement.Center
        ) {
            SmallTitle(
                modifier = Modifier
                    .background(color = if (personCount > 0) ShackleHotelBuddyTheme.colors.grayButton else ShackleHotelBuddyTheme.colors.grayBorder)
                    .padding(
                        vertical = smallUnit,
                        horizontal = mediumUnit
                    )
                    .clickable {
                        if (personCount > 0) {
                            selectedPersonCount(personCount - 1)
                        }
                    },
                text = "-",
                color = ShackleHotelBuddyTheme.colors.white,
            )


            SmallTitle(
                modifier = Modifier
                    .background(color = Color.Transparent)
                    .padding(
                        vertical = smallUnit,
                        horizontal = mediumUnit
                    )
                    .clickable {
                    },
                text = personCount.toString(),
                color = ShackleHotelBuddyTheme.colors.grayText,
            )
            SmallTitle(
                modifier = Modifier
                    .background(color = ShackleHotelBuddyTheme.colors.grayButton)
                    .padding(
                        vertical = smallUnit,
                        horizontal = mediumUnit
                    )
                    .clickable {
                        selectedPersonCount(personCount + 1)
                    },
                text = "+",
                color = ShackleHotelBuddyTheme.colors.white,
            )
        }


    }

}

@Composable
fun SearchButton(startHotelSearch: () -> Unit) {

    Button(
        colors = ButtonDefaults.buttonColors(containerColor = ShackleHotelBuddyTheme.colors.teal),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        onClick = { startHotelSearch() }
    ) {
        SmallTitle(
            text = stringResource(id = R.string.search),
        )
    }
}

@Composable
fun CheckInCheckOutDate(duration: Duration, icon: Int, title: Int, selectedDate: (Duration) -> Unit) {

    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
    ) {

        SearchOptionTitle(this, icon, title)


        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            LocalContext.current,
            { _: DatePicker, year: Int, month: Int, day: Int ->
                selectedDate(Duration(day, month + 1, year))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.datePicker.minDate = Calendar.getInstance().timeInMillis

        SmallTitle(
            modifier = Modifier
                .weight(1f)
                .padding(
                    vertical = smallUnit,
                    horizontal = mediumUnit
                )
                .padding(start = smallUnit)
                .clickable {
                    datePickerDialog.show()
                },
            text =duration.toString(),
            color = ShackleHotelBuddyTheme.colors.grayText,
        )

    }

}

@Composable
fun SearchOptionTitle(rowScope: RowScope, icon: Int, title: Int) {
    rowScope.apply {
        Row(
            modifier = Modifier
                .weight(1f)
                .padding(
                    vertical = smallUnit,
                    horizontal = smallUnit
                )
        ) {
            Icon(
                modifier = Modifier.padding(end = 5.dp),
                painter = painterResource(id = icon),
                contentDescription = "checkin date "
            )
            SmallTitle(
                modifier = Modifier,
                text = stringResource(id = title),
                color = ShackleHotelBuddyTheme.colors.grayText
            )

        }

        Divider(
            modifier = Modifier
                .height(40.dp)
                .width(1.dp)
        )
    }
}


@Composable
@Preview
fun PreviewHotelSearchScreen() {
    ShackleHotelBuddyTheme {
        HotelSearchScreen(hiltViewModel(),rememberAppState()){}
    }

}