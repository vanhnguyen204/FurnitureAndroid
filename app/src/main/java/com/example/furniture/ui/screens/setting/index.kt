package com.example.furniture.ui.screens.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.furniture.R
import com.example.furniture.components.DialogConfirm
import com.example.furniture.components.Header
import com.example.furniture.data.viewmodel.AuthViewModel
import com.example.furniture.services.UserInforRequest
import com.example.furniture.ui.screens.setting.components.SettingTextField
import com.example.furniture.ui.theme.AppTheme
import kotlinx.coroutines.launch

@Composable
fun Setting(navController: NavController, authViewModel: AuthViewModel) {
    val userInfor by authViewModel.userInfor.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F2))
            .padding(top = 10.dp)
    ) {
        Header(
            iconLeft = R.drawable.left,
            iconRight = null,
            contentCenter = {
                Text(text = "Setting", style = AppTheme.appTypography.titleHeaderStyle)
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
        Spacer(modifier = Modifier.height(20.dp))
        SettingTextField(
            title = "Personal Information",
            isPass = false,
            labelTextField = "Name",
            valueInitial = userInfor.name,
            onConfirm = {name->
                authViewModel.viewModelScope.launch {
                    authViewModel.updateInfor(UserInforRequest(name, ""))
                }
            }
        )
        Spacer(modifier = Modifier.height(10.dp))

        SettingTextField(
            title = null,
            isPass = false,
            labelTextField = "Email",
            valueInitial = userInfor.email,
            onConfirm = {}
        )
        Spacer(modifier = Modifier.height(20.dp))

        SettingTextField(
            title = "Password",
            isPass = true,
            labelTextField = "Pass",
            valueInitial = "123456789",
            onConfirm = {
                authViewModel.viewModelScope.launch {
                    authViewModel.updateInfor(UserInforRequest("", it))
                }
            }
        )

    }
}