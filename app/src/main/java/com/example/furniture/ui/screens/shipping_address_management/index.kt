package com.example.furniture.ui.screens.shipping_address_management

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.furniture.R
import com.example.furniture.components.DialogConfirm
import com.example.furniture.components.DynamicSelectTextField
import com.example.furniture.components.Header
import com.example.furniture.data.model.request.ShippingAddressRequest
import com.example.furniture.data.model.response.ShippingAddress
import com.example.furniture.data.viewmodel.ShippingAddressViewModel
import com.example.furniture.ui.theme.AppTheme
import kotlinx.coroutines.launch

@Composable
fun ManageShippingAddress(
    navHostController: NavController,
    isCreate: Boolean,
    shippingAddress: ShippingAddress?,
    shippingAddressViewModel: ShippingAddressViewModel = hiltViewModel<ShippingAddressViewModel>()
) {
    val address = shippingAddressViewModel.addresses.collectAsState()
    val (fullName, setFullName) = remember {
        mutableStateOf(shippingAddress?.recipient ?: "")
    }
    val (isModalConfirmDelete, setIsModalConfirmDelete) = remember {
        mutableStateOf(false)
    }
    val (addressDetail, setAddressDetail) = remember {
        mutableStateOf(shippingAddress?.addressDetail ?: "")
    }
    val data = listOf("Hà Nội", "Hưng Yên")
    var selectCountry by remember { mutableStateOf(shippingAddress?.country ?: "Select Country") }
    var selectedDistrict by remember {
        mutableStateOf(shippingAddress?.district ?: "Select District")
    }
    Column(verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(horizontal = 10.dp)) {
            Header(
                iconLeft = R.drawable.left,
                iconRight = null,
                contentCenter = {
                    Text(
                        text = if (isCreate) "Add shipping address" else "Update shipping address",
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



            Column(modifier = Modifier.padding(top = 27.dp)) {
                InputShippingAddress(
                    label = "Full name",
                    placeholder = "Ex: Nguyễn Việt Anh",
                    value = fullName
                ) {
                    setFullName(it)
                }
                Spacer(modifier = Modifier.height(20.dp))
                InputShippingAddress(
                    label = "Address detail",
                    placeholder = "Ex: 143/1, Xuân Phương",
                    value = addressDetail
                ) {
                    setAddressDetail(it)
                }
                Spacer(modifier = Modifier.height(20.dp))

                DynamicSelectTextField(
                    selectedValue = selectCountry,
                    options = data,
                    label = "City",
                    onValueChangedEvent = {
                        selectCountry = it

                    },
                )

                println("LaunchedEffect Triggered: $selectCountry")
                LaunchedEffect(key1 = selectCountry) {
                    shippingAddressViewModel.getAddress(selectCountry)
                }
                Spacer(modifier = Modifier.height(20.dp))
                DynamicSelectTextField(
                    selectedValue = selectedDistrict,
                    options = address.value,
                    label = "District",
                    onValueChangedEvent = {
                        selectedDistrict = it
                    },
                )

            }
        }
        DialogConfirm(
            visible = isModalConfirmDelete,
            title = "Notification",
            message = "Are you sure you want to delete this address?",
            onCancel = {
                setIsModalConfirmDelete(false)
                println(isModalConfirmDelete)
            }) {
            shippingAddressViewModel.viewModelScope.launch {
                val response =
                    shippingAddressViewModel.deleteShippingAddress(shippingAddress?.id ?: "")
                if (response.status == 200) {
                    setIsModalConfirmDelete(false)
                    navHostController.popBackStack()
                }
            }
        }
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Button(
                onClick = {
                    val newShippingAddress = ShippingAddressRequest(
                        country = "Việt Nam",
                        city = selectCountry,
                        district = selectedDistrict,
                        addressDetail = addressDetail,
                        recipient = fullName,
                    )

                    if (isCreate) {
                        shippingAddressViewModel.createShippingAddress(newShippingAddress)
                    } else {
                        val updateShippingAddress = ShippingAddressRequest(
                            shippingAddressId = shippingAddress?.id,
                            country = "Việt Nam",
                            city = selectCountry,
                            district = selectedDistrict,
                            addressDetail = addressDetail,
                            recipient = fullName,
                        )
                        shippingAddressViewModel.viewModelScope.launch {
                            shippingAddressViewModel.updateShippingAddress(shippingAddress = updateShippingAddress)
                        }
                    }
                    navHostController.popBackStack()
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),


                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black

                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = if (isCreate) "Save Address" else "Update Address")
            }
            if (!isCreate) {
                Button(
                    onClick = {
                        setIsModalConfirmDelete(true)
                    }, modifier = Modifier
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(text = "Delete address")
                }
            }
        }
    }
}

@Composable
fun InputShippingAddress(
    label: String,
    placeholder: String,
    value: String,
    onChangeText: (value: String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppTheme.appColors.appGray)
            .padding(10.dp)

    ) {
        Text(
            text = label,
            modifier = Modifier.padding(bottom = 5.dp),
            style = AppTheme.appTypography.textProduct
        )
        Box {
            if (value.isEmpty()) {
                Text(text = placeholder, style = AppTheme.appTypography.textProduct)
            }
            BasicTextField(
                value = value,
                onValueChange = {
                    onChangeText(it)
                },
                decorationBox = {
                    it()
                },
            )
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ManageShippingAddressPreview() {
    AppTheme {


    }
}