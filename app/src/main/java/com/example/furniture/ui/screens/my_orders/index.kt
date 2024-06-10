package com.example.furniture.ui.screens.my_orders

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.furniture.R
import com.example.furniture.components.Header
import com.example.furniture.data.viewmodel.InvoiceViewModel
import com.example.furniture.ui.screens.my_orders.components.InvoiceCard
import com.example.furniture.ui.theme.AppTheme

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyOrder(
    navController: NavController,
    invoiceViewModel: InvoiceViewModel = hiltViewModel<InvoiceViewModel>()
) {
    val invoices by invoiceViewModel.invoices.collectAsState()
    val PAGE_COUNT = 3
    val pagerState = rememberPagerState(
        pageCount = {
            PAGE_COUNT
        }
    )
    val pages = listOf("Delivery", "Processing", "Canceled")
    Column(modifier = Modifier
        .background(Color(0xFFF2F2F2))
        .padding(horizontal = 15.dp)) {
        Header(
            iconLeft = R.drawable.left,
            iconRight = null,
            contentCenter = {
                Text(text = "My orders", style = AppTheme.appTypography.titleHeaderStyle)
            },
            sizeIconLeft = 30.dp,
            sizeIconRight = 30.dp,
            iconLeftPress = {
                navController.popBackStack()
                Unit
            },
            iconRightPress = { },
            modifier = Modifier.fillMaxWidth()
        )
        if (invoices.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(text = "You have not purchased any products yet. Come to the booth and buy something")
            }
            return
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            pages.mapIndexed { index, item ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = item,
                        style =
                        if (pagerState.currentPage == index)
                            AppTheme.appTypography.titleHeaderStyle.copy(fontSize = 16.sp)
                        else AppTheme.appTypography.titleHeaderStyle.copy(
                            color = Color.Gray,
                            fontSize = 16.sp
                        ),
                        modifier = Modifier.padding(bottom = 3.dp)
                    )
                    Box(
                        modifier = Modifier
                            .height(4.dp)
                            .width(40.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(
                                if (pagerState.currentPage == index)
                                    Color.Black
                                else
                                    Color.Transparent
                            )
                    )
                }
            }
        }
        HorizontalPager(state = pagerState) { page ->
            Column(modifier = Modifier.fillMaxSize()) {
                LazyColumn {
                    itemsIndexed(invoices) { index, item ->
                        InvoiceCard(item = item, pages[page], navController = navController)
                    }
                }
            }
        }
    }
}