package com.example.furniture.ui.screens.payment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.furniture.R
import com.example.furniture.components.Header
import com.example.furniture.data.model.response.Payment
import com.example.furniture.data.viewmodel.PaymentViewModel
import com.example.furniture.ui.screens.payment.components.PaymentItem
import com.example.furniture.ui.theme.AppTheme
import com.example.furniture.utils.NavigationUtils

@Composable
fun Payment(
    navHostController: NavController,
    paymentViewModel: PaymentViewModel = hiltViewModel<PaymentViewModel>()
) {
    val payments by paymentViewModel.payments.collectAsState()
    LaunchedEffect(Unit) {
        paymentViewModel.getMyPayment()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.appColors.tertiary)
            .padding(10.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Header(
                iconLeft = R.drawable.left,
                iconRight = null,
                contentCenter = {
                    Text(text = "Payment method", style = AppTheme.appTypography.titleHeaderStyle)
                },
                sizeIconLeft = 30.dp,
                sizeIconRight = null,
                iconLeftPress = {
                    navHostController.popBackStack()
                    Unit
                },
                iconRightPress = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth()
            )
            LazyColumn {
                itemsIndexed(payments) { index: Int, item: Payment ->
                    PaymentItem(item = item, index = index, navController = navHostController, paymentViewModel)
                }
            }
        }
        SmallFloatingActionButton(onClick = {
            navHostController.navigate(NavigationUtils.paymentManagement + "/true/null")
        }, modifier = Modifier.offset()) {
            Icon(
                painter = painterResource(id = R.drawable.plus_child),
                contentDescription = "Plus",
                modifier = Modifier.size(20.dp)
            )
        }
    }
}