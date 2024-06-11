package com.example.furniture.ui.navigators

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.furniture.R
import com.example.furniture.data.model.response.Payment
import com.example.furniture.data.model.response.ShippingAddress
import com.example.furniture.data.viewmodel.AuthViewModel
import com.example.furniture.data.viewmodel.CartViewModel
import com.example.furniture.ui.screens.DonePurchase
import com.example.furniture.ui.screens.favorite.FavoriteScreen
import com.example.furniture.ui.screens.home.HomeScreen
import com.example.furniture.ui.screens.notification.NotificationScreen
import com.example.furniture.ui.screens.cart.CartScreen
import com.example.furniture.ui.screens.checkout.Checkout
import com.example.furniture.ui.screens.invoice_details.InvoiceDetails
import com.example.furniture.ui.screens.my_orders.MyOrder
import com.example.furniture.ui.screens.my_reviews.MyReviews
import com.example.furniture.ui.screens.payment.Payment
import com.example.furniture.ui.screens.payment_management.PaymentManagement
import com.example.furniture.ui.screens.shipping_address_management.ManageShippingAddress
import com.example.furniture.ui.screens.product_details.ProductDetails
import com.example.furniture.ui.screens.profile.ProfileScreen
import com.example.furniture.ui.screens.rating_product_details.RatingProductDetails
import com.example.furniture.ui.screens.setting.Setting
import com.example.furniture.ui.screens.shipping_address.ShippingAddressScreen
import com.example.furniture.ui.theme.AppTheme
import com.example.furniture.utils.NavigationUtils
import com.google.gson.Gson

data class TabBarItem(
    val title: String,
    val icon: Int,
    val badgeAmount: Int? = null
)


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BottomTab(
    cartViewModel: CartViewModel = hiltViewModel<CartViewModel>(),
    navController: NavHostController = rememberNavController(),
    signOut: () -> Unit
) {

    val home = TabBarItem(NavigationUtils.homeScreen, R.drawable.home)
    val favorite = TabBarItem(NavigationUtils.favorite, R.drawable.heart_bottom_bar)
    val notification = TabBarItem(NavigationUtils.notification, R.drawable.bell, 2)
    val profile = TabBarItem(NavigationUtils.profile, R.drawable.user)
    val tabBarItems = listOf(home, favorite, notification, profile)

    AppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Scaffold(modifier = Modifier.fillMaxSize(),
                bottomBar = { TabView(tabBarItems, navController = navController) }) {
                Box(modifier = Modifier.padding(it)) {
                    NestedBottomTab(
                        navController = navController,
                        cartViewModel,
                        signOut = signOut
                    )
                }
            }
        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NestedBottomTab(
    navController: NavHostController, cartViewModel: CartViewModel,
    signOut: () -> Unit
) {
    val authViewModel: AuthViewModel = hiltViewModel()
    NavHost(
        navController = navController,
        startDestination = NavigationUtils.homeScreen,

        ) {
        composable(NavigationUtils.homeScreen) {

            HomeScreen(navHostController = navController)

        }
        composable(NavigationUtils.favorite) {
            FavoriteScreen(navController = navController)
        }
        composable(NavigationUtils.notification) {
            NotificationScreen()
        }
        composable(NavigationUtils.profile) {
            ProfileScreen(navController, signOut, authViewModel = authViewModel)
        }
        composable(
            route = NavigationUtils.productDetails + "/{idProductItem}",
            arguments = listOf(navArgument("idProductItem") { type = NavType.StringType })
        ) { backStackEntry ->
            val idProductItem = backStackEntry.arguments?.getString("idProductItem")
            ProductDetails(navHostController = navController, idProductItem)
        }

        composable(route = NavigationUtils.shippingAddress) {
            ShippingAddressScreen(navHostController = navController)
        }
        composable(route = NavigationUtils.shippingAddressManage + "/{isCreate}/{shippingAddress}",
            arguments = listOf(navArgument("isCreate") { type = NavType.BoolType },
                navArgument("shippingAddress") {
                    type = NavType.StringType
                    nullable = true
                }
            )

        ) { backStackEntry ->
            val shippingAddressJson = backStackEntry.arguments?.getString("shippingAddress")
            val shippingAddress = Gson().fromJson(shippingAddressJson, ShippingAddress::class.java)
            val isCreate = backStackEntry.arguments?.getBoolean("isCreate")
            ManageShippingAddress(
                navHostController = navController,
                isCreate = isCreate ?: true,
                shippingAddress
            )
        }

        composable(route = NavigationUtils.payment) {
            Payment(navHostController = navController)
        }
        composable(route = NavigationUtils.paymentManagement + "/{isCreate}/{payment}",
            arguments = listOf(navArgument("isCreate") { type = NavType.BoolType },
                navArgument("shippingAddress") {
                    type = NavType.StringType
                    nullable = true
                }
            )) { backStackEntry ->
            val paymentJson = backStackEntry.arguments?.getString("payment")
            val payment = Gson().fromJson(paymentJson, Payment::class.java)
            val isCreate = backStackEntry.arguments?.getBoolean("isCreate")
            PaymentManagement(
                navHostController = navController,
                payment = payment,
                isCreate = isCreate ?: true
            )
        }
        composable(route = NavigationUtils.cart) {
            CartScreen(navController = navController, cartViewModel = cartViewModel)
        }
        composable(route = NavigationUtils.checkout) {
            Checkout(navController = navController, cartViewModel = cartViewModel)
        }
        composable(route = NavigationUtils.donePurchase) {
            DonePurchase(navController = navController)
        }
        composable(route = NavigationUtils.myOrders) {
            MyOrder(navController = navController)
        }
        composable(
            route = NavigationUtils.invoiceDetails + "/{invoiceId}",
            arguments = listOf(navArgument("invoiceId") { type = NavType.StringType })
        ) { backStackEntry ->
            val invoiceId = backStackEntry.arguments?.getString("invoiceId") ?: ""
            InvoiceDetails(invoiceId = invoiceId, navController = navController)
        }
        composable(route = NavigationUtils.myReviews) {
            MyReviews(navController = navController)
        }
        composable(
            route = NavigationUtils.ratingDetails + "/{productId}/{image}/{name}",
            arguments = listOf(
                navArgument("productId") { type = NavType.StringType },
                navArgument("image") { type = NavType.StringType },
                navArgument("name") { type = NavType.StringType },
            )
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            val image = backStackEntry.arguments?.getString("image") ?: ""
            val name = backStackEntry.arguments?.getString("name") ?: ""
            RatingProductDetails(
                navController = navController,
                productId = productId,
                imageProduct = image,
                name = name
            )
        }
        composable(route = NavigationUtils.setting) {
            Setting(navController = navController, authViewModel = authViewModel)
        }
    }
}

@Composable
fun TabView(tabBarItems: List<TabBarItem>, navController: NavController) {
    var selectedTabIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    val navBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStack?.destination

    val bottomBarDestination = tabBarItems.any() {
        it.title == currentDestination?.route
    }
    if (bottomBarDestination) {
        NavigationBar(containerColor = Color.White) {
            tabBarItems.forEachIndexed { index, tabBarItem ->
                NavigationBarItem(
                    selected = selectedTabIndex == index,
                    onClick = {
                        navController.navigate(tabBarItem.title)
                        selectedTabIndex = index
                    },
                    icon = {
                        TabBarIconView(
                            icon = tabBarItem.icon,
                            title = tabBarItem.title,
                            badgeAmount = tabBarItem.badgeAmount
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.Black,
                        unselectedIconColor = Color.Gray,
                        indicatorColor = Color.White
                    )
                )

            }
        }
    }

}

// This component helps to clean up the API call from our TabView above,
// but could just as easily be added inside the TabView without creating this custom component
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabBarIconView(
    icon: Int,
    title: String,
    badgeAmount: Int? = null
) {
    BadgedBox(badge = { TabBarBadgeView(badgeAmount) }) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = title,
            modifier = Modifier.size(25.dp)
        )
    }
}

// This component helps to clean up the API call from our TabBarIconView above,
// but could just as easily be added inside the TabBarIconView without creating this custom component
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TabBarBadgeView(count: Int? = null) {
    if (count != null) {
        Badge {
            Text(count.toString())
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BottomTabPreview() {
    AppTheme {

    }
}