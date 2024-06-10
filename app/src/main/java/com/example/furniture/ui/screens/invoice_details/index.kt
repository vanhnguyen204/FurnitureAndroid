package com.example.furniture.ui.screens.invoice_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.furniture.R
import com.example.furniture.components.Header
import com.example.furniture.components.ModalRating
import com.example.furniture.data.viewmodel.InvoiceViewModel
import com.example.furniture.ui.screens.invoice_details.components.InvoiceDetailsCart
import com.example.furniture.ui.theme.AppTheme

@Composable
fun InvoiceDetails(
    invoiceId: String,
    navController: NavController,
    invoiceViewModel: InvoiceViewModel = hiltViewModel<InvoiceViewModel>()
) {
    val invoiceDetails by invoiceViewModel.invoiceDetail.collectAsState()
    var visibleModalRating by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(Unit) {
        invoiceViewModel.getInvoiceDetails(invoiceId)
    }
    Column(
        modifier = Modifier
            .background(Color(0xFFF2F2F2))
            .padding(horizontal = 10.dp)
            .fillMaxSize()
    ) {
        ModalRating(isVisible = visibleModalRating, onCancel = {
            visibleModalRating = !visibleModalRating
        }) {

        }
        Header(
            iconLeft = R.drawable.left,
            iconRight = null,
            contentCenter = {
                Text(text = "Invoice details", style = AppTheme.appTypography.titleHeaderStyle)
            },
            sizeIconLeft = 30.dp,
            sizeIconRight = 30.dp,
            iconLeftPress = {
                navController.popBackStack()
                Unit
            },
            iconRightPress = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth()
        )

        LazyColumn {
            items(invoiceDetails) {
                InvoiceDetailsCart(item = it) {

                }
            }
        }
    }
}