package com.example.furniture.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.furniture.R
import com.example.furniture.components.Header
import com.example.furniture.ui.screens.profile.components.ProfileItemFeature
import com.example.furniture.ui.screens.profile.components.ProfileItemFeatureProps
import com.example.furniture.ui.theme.AppTheme
import com.example.furniture.utils.NavigationUtils

@Composable
fun ProfileScreen(navHostController: NavHostController) {
//    val user by authViewModel.user.observeAsState()  authViewModel: AuthViewModel = hiltViewModel<AuthViewModel>()
    val feat1 = ProfileItemFeatureProps(name = "My orders", "Already have 10 orders") {
        navHostController.navigate(NavigationUtils.myOrders)
    }
    val feat2 = ProfileItemFeatureProps(name = "Shipping Address", "Already have 10 orders") {
        navHostController.navigate(NavigationUtils.shippingAddress)
    }
    val feat3 = ProfileItemFeatureProps(name = "Payment Method", "Already have 10 payments") {
        navHostController.navigate(NavigationUtils.payment)
    }
    val feat4 = ProfileItemFeatureProps(name = "My reviews", "Already have 10 orders", {})
    val feat5 = ProfileItemFeatureProps(name = "Setting", "Already have 10 orders", {})
    val feats = listOf(feat1, feat2, feat3, feat4, feat5)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.appColors.tertiary)
            .padding(10.dp)
    ) {
        Header(
            iconLeft = R.drawable.search_2,
            iconRight = R.drawable.log_out,
            contentCenter = {
                Text(
                    text = "Profile", style = AppTheme.appTypography.subTitle.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight(700),
                        color = Color.Black
                    )
                )
            },
            sizeIconLeft = 30.dp,
            sizeIconRight = 28.dp,
            iconLeftPress = { /*TODO*/ },
            iconRightPress = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()

        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
        ) {
//            if (!user?.data?.avatar.equals("")) {
//
//                AsyncImage(
//                    model = RetrofitUtils.BASE_URL + "" + user?.data?.avatar,
//                    contentDescription = "Avatar"
//                )
//            } else {
//
//            }
            Image(
                painter = painterResource(id = R.drawable.avatar_test), contentDescription = "",
                modifier = Modifier.size(80.dp)
            )
            Column(modifier = Modifier.padding(start = 10.dp)) {
                Text(
                    text = "Nguyễn Việt Anh", style = AppTheme.appTypography.h1.copy(
                        fontSize = 20.sp
                    )
                )
                Text(
                    text = "nguyenvietem@gmail.com",
                    modifier = Modifier.padding(top = 6.dp),
                    style = AppTheme.appTypography.body2.copy(color = Color(0xFF808080))
                )
            }
        }

        LazyColumn {
            itemsIndexed(feats) { index, item ->
                ProfileItemFeature(item = item, index = index)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    AppTheme {
        val navHostController = rememberNavController()
        ProfileScreen(navHostController)
    }
}