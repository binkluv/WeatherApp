package ru.mmnigmatullov.weatherapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ru.mmnigmatullov.weatherapp.data.WeatherModel
import ru.mmnigmatullov.weatherapp.ui.theme.WhiteLight

@Composable
fun ListItem(item: WeatherModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 3.dp),
        backgroundColor = WhiteLight,
        elevation = 0.dp,
        shape = RoundedCornerShape(5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        start = 8.dp,
                        top = 5.dp,
                        bottom = 5.dp
                    )
            ) {
                Text(text = item.time)
                Text(text = item.condition)
            }
            Text(
                text = item.currentTemp.ifEmpty { "${item.maxTemp}/${item.minTemp}" },
                color = Color.Black,
                style = TextStyle(fontSize = 25.sp)
            )
            AsyncImage(
                model = "https:${item.icon}",
                contentDescription = "im5",
                modifier = Modifier
                    .size(35.dp)
                    .padding(end = 8.dp)
            )
        }
    }
}