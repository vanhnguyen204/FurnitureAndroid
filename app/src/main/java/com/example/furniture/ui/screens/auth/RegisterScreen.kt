package com.example.furniture.ui.screens.auth


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.furniture.R
import com.example.furniture.components.DialogMessage
import com.example.furniture.constant.Storage
import com.example.furniture.data.model.response.User
import com.example.furniture.data.viewmodel.AuthViewModel
import com.example.furniture.helper.Console
import com.example.furniture.helper.Resource
import com.example.furniture.helper.SharedPreferencesHelper
import com.example.furniture.ui.theme.AppTheme
import com.example.furniture.utils.NavigationUtils
import com.example.furniture.utils.Validate
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(
    navHostController: NavHostController,
    authViewModel: AuthViewModel = hiltViewModel<AuthViewModel>()
) {
    var dialogMessageVisible by remember {
        mutableStateOf(false)
    }
    var dialogSuccess by remember {
        mutableStateOf(false)
    }
    val userInfor by authViewModel.user.observeAsState()
    DialogMessage(
        visibility = dialogMessageVisible,
        onClose = {
            dialogMessageVisible = false
        },
        title = "Notification",
        message = "This account is already contain. Please try again",
        titleColor = Color.Red,
        messageColor = Color.Black
    )
    DialogMessage(
        visibility = dialogSuccess,
        onClose = {
            dialogSuccess = false
            navHostController.popBackStack()
        },
        title = "Notification",
        message = "Create account success!",
        titleColor = Color.Black,
        messageColor = Color.Black
    )
    val context = LocalContext.current
    val sharedPreferencesHelper = remember {
        SharedPreferencesHelper(context)
    }
    var isShowPass by remember {
        mutableStateOf(false)
    }
    var email by remember {
        mutableStateOf("")
    }
    var passWord by remember {
        mutableStateOf("")
    }
    var confirmPassword by remember {
        mutableStateOf("")
    }
    var isShowPassConfirm by remember {
        mutableStateOf(false)
    }
    var errorConfirmPass by remember {
        mutableStateOf("")
    }
    var errorEmail by remember {
        mutableStateOf("")
    }
    var errorPass by remember {
        mutableStateOf("")
    }
    var name by remember {
        mutableStateOf("")
    }
    var errorName by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .padding(0.dp)
            .background(Color.White)
            .fillMaxSize()
            .padding(end = 40.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 40.dp, top = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(width = 100.dp, height = 1.dp)
                    .background(Color(0xFFBDBDBD))
            )
            Box(
                modifier = Modifier
                    .padding(start = 15.dp, end = 15.dp)
                    .border(0.5.dp, Color.Black, CircleShape)
                    .padding(10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_top_login),
                    contentDescription = "Icon center top login",
                    modifier = Modifier.size(50.dp),

                    )
            }
            Box(
                modifier = Modifier
                    .size(width = 100.dp, height = 1.dp)
                    .background(Color(0xFFBDBDBD))
            )
        }
        Column(modifier = Modifier.padding(20.dp)) {

            Text(
                text = "WELCOME, LET'S REGISTER", style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontFamily = FontFamily(AppTheme.appFonts.gelasioRegular)
                )
            )
        }
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(0.dp)
                .shadow(1.dp, RoundedCornerShape(topEnd = 5.dp, bottomEnd = 5.dp), clip = true)
                .fillMaxWidth()
                .padding(20.dp)


        ) {
            Text(
                text = "Name",
                style = TextStyle(
                    color = Color.Gray
                ),
            )
            TextField(
                value = name,
                onValueChange = {
                    name = it
                    errorName = if (it == "") {
                        "Name is required"
                    } else {
                        ""
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                )
            )
            if (errorName.isNotEmpty()) {
                Text(
                    text = errorName, style = TextStyle(
                        color = Color.Red
                    ),
                    modifier = Modifier.padding(top = 5.dp)
                )
            }
            Text(
                text = "Email",
                style = TextStyle(
                    color = Color.Gray
                ),
                modifier = Modifier.padding(top = 20.dp)
            )
            TextField(
                value = email,
                onValueChange = {
                    email = it
                    errorEmail = Validate.validateEmail(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                )
            )
            if (errorEmail.isNotEmpty()) {
                Text(
                    text = errorEmail, style = TextStyle(
                        color = Color.Red
                    ),
                    modifier = Modifier.padding(top = 5.dp)
                )
            }
            Text(
                text = "Password",
                style = TextStyle(
                    color = Color.Gray
                ), modifier = Modifier.padding(top = 20.dp)
            )
            TextField(
                value = passWord,
                onValueChange = {
                    passWord = it
                    errorPass = Validate.validatePassword(it)
                },
                visualTransformation = if (isShowPass) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                ),
                trailingIcon = {
                    IconButton(onClick = {
                        isShowPass = !isShowPass
                    }) {

                        Icon(
                            if (isShowPass)
                                painterResource(id = R.drawable.view) else painterResource
                                (id = R.drawable.hidden),
                            contentDescription = "",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            )
            if (errorPass.isNotEmpty()) {
                Text(
                    text = errorPass, style = TextStyle(
                        color = Color.Red
                    ),
                    modifier = Modifier.padding(top = 5.dp)
                )
            }
            Text(
                text = "Confirm Password",
                style = TextStyle(
                    color = Color.Gray
                ), modifier = Modifier.padding(top = 20.dp)
            )
            TextField(
                value = confirmPassword,
                onValueChange = {
                    confirmPassword = it
                    errorConfirmPass = Validate.validateConfirmPassWord(passWord, it)
                },
                visualTransformation = if (isShowPassConfirm) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                ),
                trailingIcon = {
                    IconButton(onClick = {
                        isShowPassConfirm = !isShowPassConfirm
                    }) {

                        Icon(
                            if (isShowPassConfirm)
                                painterResource(id = R.drawable.view) else painterResource
                                (id = R.drawable.hidden),
                            contentDescription = "",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            )
            if (errorConfirmPass.isNotEmpty()) {
                Text(
                    text = errorConfirmPass, style = TextStyle(
                        color = Color.Red
                    ),
                    modifier = Modifier.padding(top = 5.dp)
                )
            }
            Button(

                onClick = {

                    val checkEmail = Validate.validateEmail(email.trim());
                    val checkPass = Validate.validatePassword(passWord.trim())
                    val checkConfirmPass = Validate.validateConfirmPassWord(passWord, confirmPassword)
                    if (checkEmail.isNotEmpty() || checkPass.isNotEmpty() || errorName.isNotEmpty() || checkConfirmPass.isNotEmpty()) {
                        errorEmail = checkEmail
                        errorPass = checkPass
                        errorConfirmPass = checkConfirmPass
                        errorName = if (name.isEmpty()) "Name is required" else ""
                    } else {
                        val user: User = User(email, passWord, name = name)
                        authViewModel.viewModelScope.launch {
                            val res = authViewModel.authRegister(user)
                            if (res) {
                                dialogSuccess = true
                            } else {
                                dialogMessageVisible = true
                            }
                        }

                    }

                },
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                ),
                modifier = Modifier
                    .padding(top = 70.dp)
                    .align(Alignment.CenterHorizontally),

                ) {
                Text(
                    text = "Sign Up",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.gelasio_medium)),
                        fontWeight = FontWeight(600),
                        textAlign = TextAlign.Center,
                        lineHeight = TextUnit(10f, TextUnitType(10L))
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Button(
                onClick = {
                    navHostController.popBackStack()
                }, colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                )
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Already have account?",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.gelasio_medium)),
                            fontWeight = FontWeight(600),
                            textAlign = TextAlign.Center,
                            lineHeight = TextUnit(10f, TextUnitType(10L)),
                            color = Color.Gray
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = "SIGN IN",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.gelasio_medium)),
                            fontWeight = FontWeight(600),
                            textAlign = TextAlign.Center,
                            lineHeight = TextUnit(10f, TextUnitType(10L)),
                            color = Color.Black
                        ),
                        modifier = Modifier.clickable { navHostController.popBackStack() }
                    )
                }
            }
        }

    }
}
