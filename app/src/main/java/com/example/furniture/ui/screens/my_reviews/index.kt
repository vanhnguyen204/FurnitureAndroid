package com.example.furniture.ui.screens.my_reviews

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.furniture.R
import com.example.furniture.components.Header
import com.example.furniture.data.viewmodel.RatingViewModel
import com.example.furniture.ui.screens.my_reviews.components.RatingCard
import com.example.furniture.ui.theme.AppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyReviews(
    navController: NavController,
    ratingViewModel: RatingViewModel = hiltViewModel<RatingViewModel>()
) {
    val reviews by ratingViewModel.ratingReviews.collectAsState()
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = Color(0xFFF2F2F2),
    )
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFF2F2F2))
        .padding(top = 10.dp)) {
        Header(
            iconLeft = R.drawable.left,
            iconRight = null,
            contentCenter = {
                Text(text = "My Reviews", style = AppTheme.appTypography.titleHeaderStyle)
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
        if (reviews.isEmpty()) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = "You haven't reviewed any products yet.")
            }
            return
        }
        LazyColumn {
            items(reviews) {
                RatingCard(item = it)
            }
        }
    }
}