package ru.mmnigmatullov.weatherapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch
import ru.mmnigmatullov.weatherapp.R
import ru.mmnigmatullov.weatherapp.data.WeatherModel
import ru.mmnigmatullov.weatherapp.ui.theme.WhiteLight


@Preview(showBackground = true)
@Composable
fun MainCard() {
    Column(
        modifier = Modifier
            .padding(5.dp)
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth(),
            backgroundColor = WhiteLight,
            elevation = 0.dp,
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.padding(top = 8.dp, start = 8.dp),
                        text = "20 June 2022 13:00",
                        style = TextStyle(fontSize = 15.sp),
                        color = Color.Black

                    )
                    AsyncImage(
                        model = "https://cdn.weatherapi.com/weather/64x64/day/116.png",
                        contentDescription = "im2",
                        modifier = Modifier
                            .size(35.dp)
                            .padding(
                                top = 3.dp,
                                end = 8.dp
                            )
                    )
                }
                Text(
                    text = "Moscow",
                    style = TextStyle(fontSize = 24.sp),
                    color = Color.Black
                )
                Text(
                    text = "23°C",
                    style = TextStyle(fontSize = 65.sp),
                    color = Color.Black
                )
                Text(
                    text = "Cloudy",
                    style = TextStyle(fontSize = 16.sp),
                    color = Color.Black
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween

                ) {
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = "im3",
                            tint = Color.Black
                        )
                    }
                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text = "23°C/12°C",
                        style = TextStyle(fontSize = 16.sp),
                        color = Color.Black
                    )
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_sync),
                            contentDescription = "im4",
                            tint = Color.Black
                        )
                    }
                }
            }
        }
    }
}

@ExperimentalPagerApi
@Composable
fun TabLayout(daysList: MutableState<List<WeatherModel>>){
    val tabList = listOf("HOURS", "DAYS")
    val pagerState = rememberPagerState()
    val tabIndex = pagerState.currentPage
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier
        .padding(start = 5.dp, end = 5.dp)
        .clip(RoundedCornerShape(5.dp))

    ) {
    TabRow(
        selectedTabIndex = tabIndex,
        indicator = { pos ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, pos)
            )
                    },
        backgroundColor = WhiteLight,
        contentColor = Color.Black
    ) {
       tabList.forEachIndexed{index, text->
           Tab(
               selected = false,
               onClick = {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(index)
                }
           },
               text = {
                   Text(text = text)
               }
           )
       }
    }
        HorizontalPager(
            count = tabList.size,
            state = pagerState,
            modifier = Modifier.weight(1.0f)
        ) {
                index ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ){
                itemsIndexed(
                    daysList.value
                ){
                    _, item ->ru.mmnigmatullov.weatherapp.ListItem(item)
                }


                }
            }
        }
    }
