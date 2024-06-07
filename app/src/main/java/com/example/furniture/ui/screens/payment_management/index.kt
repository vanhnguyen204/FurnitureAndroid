package com.example.furniture.ui.screens.payment_management

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.furniture.R
import com.example.furniture.components.Header
import com.example.furniture.data.model.request.RequestBodyPayment
import com.example.furniture.data.model.response.Payment
import com.example.furniture.data.viewmodel.PaymentViewModel
import com.example.furniture.helper.Console
import com.example.furniture.ui.screens.payment.components.renderCardNumber
import com.example.furniture.ui.screens.payment_management.components.ModalBottomBank
import com.example.furniture.ui.theme.AppTheme
import com.example.furniture.utils.RetrofitUtils
import kotlinx.coroutines.launch

data class BankType(
    val icon: Int,
    val type: String,
    val name: String
)

@Composable
fun PaymentManagement(navHostController: NavController, payment: Payment?, isCreate: Boolean, paymentViewModel: PaymentViewModel = hiltViewModel<PaymentViewModel>()) {
   val context = LocalContext.current
    val (visible, setVisible) = remember {
        mutableStateOf(false)
    }
    var cardHolderName by remember {
        mutableStateOf(payment?.cartHolderName ?: "")
    }
    var errorCardHolderName by remember {
        mutableStateOf("")
    }
    var cardNumber by remember {
        mutableStateOf(payment?.cartNumber ?: "")
    }
    var errorCardNumber by remember {
        mutableStateOf("")
    }
    var cvv by remember {
        mutableStateOf(payment?.cvv ?: "")
    }
    var errorCVV by remember {
        mutableStateOf("")
    }
    var expiryDate by remember {
        mutableStateOf(payment?.expiryDate ?: "")
    }
    var errorExpiryDate by remember {
        mutableStateOf("")
    }
    val (selectedBank, setSelectedBank) = remember {
        mutableStateOf(
            BankType(
                icon = R.drawable.icon_vietcombank,
                type = "vietcombank",
                name = "Vietcombank"
            )
        )
    }
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
        ModalBottomBank(visible = visible, onClose = {
            setVisible(false)
        }) {
            setSelectedBank(it)
        }
        Column {
            Header(
                iconLeft = R.drawable.left,
                iconRight = null,
                contentCenter = {
                    Text(
                        text = if (isCreate) "Add payment method" else "Edit payment method",
                        style = AppTheme.appTypography.titleHeaderStyle
                    )
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

            CardTemplate(
                icon = selectedBank.icon,
                cardNumber = if (cardNumber != "") cardNumber else "123456789",
                cardHolderName = if (cardHolderName != "") cardHolderName else "Ex: NGUYEN VAN A",
                expiryDate = if (expiryDate != "") expiryDate else "01/27"
            )

            Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 40.dp)) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(AppTheme.appColors.appGray)
                    .padding(10.dp)
                    .clickable {
                        setVisible(true)
                    }
                ) {
                    Image(
                        painter = painterResource(selectedBank.icon),
                        modifier = Modifier.size(25.dp),
                        contentDescription = "Icon bank"
                    )
                    Text(text = selectedBank.name, modifier = Modifier.padding(start = 15.dp))
                }
                InputPayment(
                    label = "Card Holder Name",
                    placeholder = "Ex: NGUYEN VAN A",
                    value = cardHolderName,
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                        .fillMaxWidth()
                        .background(AppTheme.appColors.appGray)
                        .padding(10.dp),
                    error = errorCardHolderName,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    ),
                ) {
                    cardHolderName = it
                    errorCardHolderName = if (cardHolderName == "") {
                        "Card holder name is required"
                    }else{
                        ""
                    }
                }
                InputPayment(
                    label = "Card Number",
                    placeholder = "Ex: 123456789",
                    value = cardNumber,
                    error = errorCardNumber,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .border(1.dp, AppTheme.appColors.appGray, RoundedCornerShape(5.dp))
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(10.dp)

                ) {
                    cardNumber = it
                    errorCardNumber = if (cardNumber == "") {
                        "Card number is required"
                    }else{
                        ""
                    }
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    InputPayment(
                        label = "CVV",
                        placeholder = "Ex: 123",
                        value = cvv.toString(),
                        error = errorCVV,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        modifier = Modifier
                            .clip(RoundedCornerShape(5.dp))
                            .background(AppTheme.appColors.appGray)
                            .padding(10.dp)
                            .weight(1f)
                    ) {
                        cvv = it
                        errorCVV = if (cvv.toString() == "") {
                            "Cvv is required"
                        }else{
                            ""
                        }
                    }

                    Spacer(modifier = Modifier.width(20.dp))
                    InputPayment(
                        label = "Expiry Date",
                        placeholder = "Ex: 01/25",
                        value = if (expiryDate.length > 2 && expiryDate[2] != '/') {
                            expiryDate.substring(0, 2) + "/" + expiryDate.substring(2)
                        } else {
                            expiryDate
                        },
                        error = errorExpiryDate,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text
                        ),
                        modifier = Modifier
                            .clip(RoundedCornerShape(5.dp))
                            .border(1.dp, AppTheme.appColors.appGray, RoundedCornerShape(5.dp))
                            .background(Color.White)
                            .padding(10.dp)
                            .weight(1f)

                    ) {
                        expiryDate = it
                        errorExpiryDate = if (expiryDate == "") {
                            "Expiry date is required"
                        }else{
                            ""
                        }
                    }
                }
            }
        }

        Button(
            shape = RoundedCornerShape(5.dp),
            onClick = {
                if (cardHolderName == "") {
                    errorCardHolderName = "Card holder name is required"
                }
                if (cardNumber == "") {
                    errorCardNumber = "Card number is required"
                }
                if (cvv.toString() == "") {
                    errorCVV = "Cvv is required"
                }
                if (expiryDate == "") {
                    errorExpiryDate = "Expiry date is required"
                }
                if (
                    errorCardHolderName == "" ||
                    errorCardNumber == "" ||
                    errorCVV == "" ||
                    errorExpiryDate == ""
                ){
                    val newPayment = RequestBodyPayment(
                        cardNumber ,
                        bankName = selectedBank.name,
                        type = selectedBank.type,
                        cvv = cvv.toString(),
                        cartHolderName = cardHolderName,
                        expiryDate = expiryDate
                    )
                  paymentViewModel.viewModelScope.launch {
                     val res = paymentViewModel.addNewPayment(newPayment)
                      if (res) {
                          navHostController.popBackStack()
                      }
                  }
                }
            },
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
            )
        ) {
            Text(text = "Save payment", modifier = Modifier.padding(10.dp))
        }
    }
}

fun renderExpiryDate(expiryDate: String): String {
    var exportDate = ""
    if (expiryDate.length == 2) {
        exportDate += "$exportDate/"
    }
    exportDate += expiryDate
    return exportDate
}

@Composable
fun InputPayment(
    label: String,
    placeholder: String,
    value: String,
    modifier: Modifier,
    error: String,
    keyboardOptions: KeyboardOptions,
    onChangeText: (value: String) -> Unit
) {

    Column(modifier = modifier) {
        Column(

        ) {
            Text(
                text = label,
                modifier = Modifier.padding(bottom = 5.dp),
                style = AppTheme.appTypography.textProduct
            )
            Box {
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,

                        style = AppTheme.appTypography.textProduct
                    )
                }
                BasicTextField(
                    value = value,
                    onValueChange = {
                        onChangeText(it)
                    },
                    decorationBox = {
                        it()
                    },
                    keyboardOptions = keyboardOptions
                )
            }

        }
        if (error != "") {
            Text(
                text = error, style = TextStyle(
                    color = Color.Red
                ),
                modifier = Modifier.padding(top = 5.dp)
            )
        }
    }


}

@Composable
fun CardTemplate(icon: Any, cardNumber: String, cardHolderName: String, expiryDate: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.background_bank),
            contentDescription = "Background bank",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
        )

        Image(
            painter = painterResource(id = R.drawable.wave), modifier = Modifier.align(
                Alignment.BottomEnd
            ), contentDescription = "Wave"
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {

            if (icon is Int) {
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = "Icon bank",
                    modifier = Modifier.size(25.dp)
                )
            } else {
                AsyncImage(model = RetrofitUtils.BASE_URL + icon, contentDescription = "Icon bank")
            }
            Text(
                text = renderCardNumber(cardNumber), letterSpacing = 10.sp, style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.White
                ),

                modifier = Modifier.padding(vertical = 10.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Card Holder Name",
                        modifier = Modifier.padding(bottom = 10.dp),
                        style = TextStyle(
                            color = Color.White
                        )
                    )
                    Text(
                        text = cardHolderName, style = TextStyle(
                            color = Color.White
                        )
                    )
                }

                Column {
                    Text(
                        text = "Expiry Date",
                        modifier = Modifier.padding(bottom = 10.dp),
                        style = TextStyle(
                            color = Color.White
                        )
                    )
                    Text(
                        text = expiryDate, style = TextStyle(
                            color = Color.White
                        )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable

fun PaymentManagementPreview() {
    AppTheme {
        PaymentManagement(
            navHostController = rememberNavController(),
            payment = null,
            isCreate = true
        )
    }
}
