package com.example.furniture.components

import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.furniture.ui.theme.AppTheme
import kotlinx.coroutines.launch

@Composable
fun DatePicker(visible: Boolean, onClose: () -> Unit) {
    val days = (1..12).toList()
    val listStateDay = rememberLazyListState()
    val focusRequester = remember { FocusRequester() }
    val coroutineScope = rememberCoroutineScope()
    var focusedItem by remember { mutableIntStateOf(0) }
    if (visible) {
        Dialog(onDismissRequest = {
            onClose()
        }) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent),
                contentAlignment = Alignment.Center
            ) {


                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .clip(RoundedCornerShape(25.dp))
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LazyColumn(modifier = Modifier.height(60.dp), state = listStateDay) {
                        itemsIndexed(days) {index, item->
                        Text(text = if (item<10) "0$item" else item.toString(),

                            modifier = Modifier

                                .onFocusChanged { focusState ->
                                    if (focusState.isFocused) {
                                        focusedItem = index
                                    }
                                }
                                .focusRequester(focusRequester)
                                .focusable()
                                ,
                            style = TextStyle(
                                color = if (focusedItem == index) Color.White else Color.Black
                            )
                            )

                        }
                    }
                    LaunchedEffect(Unit) {
                        focusRequester.requestFocus()
                    }
                    LaunchedEffect(remember { derivedStateOf { listStateDay.firstVisibleItemIndex } }) {
                        coroutineScope.launch {
                            val middleItemIndex = listStateDay.firstVisibleItemIndex + listStateDay.layoutInfo.visibleItemsInfo.size / 2
                            if (middleItemIndex in days.indices) {
                                focusedItem = middleItemIndex
                                focusRequester.requestFocus()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DatePickerPreview() {
    AppTheme {
        DatePicker(visible = true) {

        }
    }
}