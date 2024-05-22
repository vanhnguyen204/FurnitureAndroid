package com.example.furniture.ui.screens.auth


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.furniture.R
import com.example.furniture.components.DialogMessage
import com.example.furniture.constant.Storage
import com.example.furniture.data.model.User
import com.example.furniture.data.viewmodel.AuthViewModel
import com.example.furniture.helper.Console
import com.example.furniture.helper.Resource
import com.example.furniture.helper.SharedPreferencesHelper
import com.example.furniture.ui.theme.AppTheme
import com.example.furniture.utils.NavigationUtils
import com.example.furniture.utils.Validate

@Composable
fun LoginScreen(
    navHostController: NavHostController,
    authViewModel: AuthViewModel = hiltViewModel<AuthViewModel>()
) {
    var dialogMessageVisible by remember {
        mutableStateOf(false)
    }

    val userInfor by authViewModel.user.observeAsState()
    DialogMessage(
        visibility = dialogMessageVisible,
        onClose = {

            dialogMessageVisible = false
            Console().log("LOG BUTTON", "VISIBLE${dialogMessageVisible}")
        },
        title = "Thông báo",
        message = "${userInfor?.errorResponse?.message}, ${userInfor?.errorResponse?.cause}",
        titleColor = Color.Red,
        messageColor = Color.White
    )
    LaunchedEffect(key1 = dialogMessageVisible) {
        Console().log("LOG USE EFFECT", "VISIBLE${dialogMessageVisible}")

    }
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
    var errorEmail by remember {
        mutableStateOf("")
    }
    var errorPass by remember {
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
                text = "Hello !", style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight(400),
                    color = Color.Gray,
                    fontFamily = FontFamily(AppTheme.appFonts.gelasioRegular)
                )
            )
            Text(
                text = "WELCOME BACK", style = TextStyle(
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
                text = "Email",
                style = TextStyle(
                    color = Color.Gray
                ),
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
                text = "Forgot password?",
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 10.dp)
            )
            Button(

                onClick = {

                    val checkEmail = Validate.validateEmail(email.trim());
                    val checkPass = Validate.validatePassword(passWord.trim())
                    if (checkEmail.isNotEmpty() || checkPass.isNotEmpty()) {
                        errorEmail = checkEmail
                        errorPass = checkPass
                    } else {
                        val user: User = com.example.furniture.data.model.User(email, passWord)
                        authViewModel.authLogin(user)

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
                    text = "Login",
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
            LaunchedEffect(key1 = userInfor) {
                userInfor?.let {
                    when (it) {
                        is Resource.Success -> {
                            it.data?.token?.let { token ->
                                sharedPreferencesHelper.saveDataLocalStorage(Storage.TOKEN.toString(),
                                    token
                                )
                            }
                            navHostController.navigate(NavigationUtils.bottomTab)

                        }
                        is Resource.Error -> {
                            dialogMessageVisible = true
                            Console().log("LOG USE EFFECT", "VISIBLE $dialogMessageVisible")
                        }
                        is Resource.ErrorRes -> {
                            dialogMessageVisible = true
                            Console().log("LOG USE EFFECT", "VISIBLE $dialogMessageVisible")
                        }
                    }
                }
            }


            Button(
                onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                )
            ) {
                Text(
                    text = "Sign up",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.gelasio_medium)),
                        fontWeight = FontWeight(600),
                        textAlign = TextAlign.Center,
                        lineHeight = TextUnit(10f, TextUnitType(10L)),
                        color = Color.Black
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

    }


}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    AppTheme {
        val navHostController = rememberNavController();
        LoginScreen(navHostController = navHostController)
    }
}