package com.example.furniture.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.furniture.R
import com.example.furniture.ui.theme.AppTheme

data class Item(
    val title: String,
    val icon: Int
)

@Composable
fun CategoryComponent() {
    val itemCategories = listOf(
        Item("Popular", R.drawable.star),
        Item("Chair", R.drawable.chair),
        Item("Table", R.drawable.side_stable),
        Item("ArmChair", R.drawable.armchair),
        Item("Bed", R.drawable.bed),
        Item("Lamp", R.drawable.bulb),
    )
    var isSelected by remember {
        mutableStateOf(0)
    }
    LazyRow {
        itemsIndexed(itemCategories) { index, item ->
            ItemCategory(
                item = item,
                index = index,
                isSelected = isSelected == index,
                onItemSelected = {
                    isSelected = index
                })
        }
    }
}

fun handleSelected() {

}

@Composable
fun ItemCategory(item: Item, index: Int, isSelected: Boolean, onItemSelected: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(horizontal = 15.dp, vertical = 20.dp),
    ) {
        Column(
            modifier = Modifier
                .background(
                    if (isSelected) Color.Black else Color.Gray,
                    shape = RoundedCornerShape(15.dp)
                )
                .clip(RoundedCornerShape(15.dp))
                .padding(12.dp)
                .clickable {
                    onItemSelected()
                }
        ) {
            Image(
                painter = painterResource(id = item.icon),
                contentDescription = "Icon category",
                modifier = Modifier
                    .size(20.dp),
                colorFilter = ColorFilter.tint(color = Color.White)

            )
        }
        Text(text = item.title, style = TextStyle(
            fontSize = 14.sp
        ), modifier = Modifier.padding(top = 5.dp))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CatePreview() {
    AppTheme {
        CategoryComponent()
    }
}