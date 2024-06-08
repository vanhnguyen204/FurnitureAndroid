package com.example.furniture.di

import android.content.Context
import android.content.SharedPreferences
import com.example.furniture.data.model.response.Cart
import com.example.furniture.data.repository.AuthRepositoryImpl
import com.example.furniture.data.repository.CartRepositoryImpl
import com.example.furniture.data.repository.FavoriteRepositoryImpl
import com.example.furniture.data.repository.PaymentRepositoryImpl
import com.example.furniture.data.repository.ProductRepositoryImpl
import com.example.furniture.data.repository.ShippingAddressRepositoryImpl
import com.example.furniture.domain.repository.AuthRepository
import com.example.furniture.domain.repository.CartRepository
import com.example.furniture.domain.repository.FavoriteRepository
import com.example.furniture.domain.repository.PaymentRepository
import com.example.furniture.domain.repository.ProductRepository
import com.example.furniture.domain.repository.ShippingAddressRepository
import com.example.furniture.services.AuthService
import com.example.furniture.services.CartService
import com.example.furniture.services.FavoriteService
import com.example.furniture.services.PaymentService
import com.example.furniture.services.ProductService
import com.example.furniture.services.ShippingAddressService
import com.example.furniture.utils.RetrofitUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    //configuration Retrofit
    @Provides
    @Singleton
    fun provideApi(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(RetrofitUtils.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    }
    //configuration service
    //PRODUCT
    @Provides
    @Singleton
    fun provideProductService(retrofit: Retrofit): ProductService {
        return retrofit.create(ProductService::class.java)
    }
    @Provides
    @Singleton
    fun provideProductRepository(productService: ProductService): ProductRepository {
        return ProductRepositoryImpl(productService)
    }

    //FAVORITE
    @Provides
    @Singleton
    fun provideFavoriteService(
       retrofit: Retrofit
    ): FavoriteService {
       return retrofit.create(FavoriteService::class.java)
    }
    @Provides
    @Singleton
    fun provideFavoriteRepository(favoriteService: FavoriteService,  sharedPreferences: SharedPreferences): FavoriteRepository {
        return FavoriteRepositoryImpl(favoriteService, sharedPreferences)
    }

    //AUTHENTICATION
    @Provides
    @Singleton
    fun provideAuthService(
        retrofit: Retrofit
    ): AuthService {
        return retrofit.create(AuthService::class.java)
    }
    @Provides
    @Singleton
    fun provideAuthRepository(authService: AuthService): AuthRepository {
        return AuthRepositoryImpl(authService)
    }
    //SHIPPING ADDRESS

    @Provides
    @Singleton
    fun provideShippingAddressService(
        retrofit: Retrofit
    ): ShippingAddressService{
        return retrofit.create(ShippingAddressService::class.java)
    }

    @Provides
    @Singleton
    fun provideShippingAddressRepository(
        shippingAddressService: ShippingAddressService,
        sharedPreferences: SharedPreferences
    ): ShippingAddressRepository {
        return ShippingAddressRepositoryImpl(shippingAddressService, sharedPreferences)
    }

    //PAYMENT METHOD
    @Provides
    @Singleton
    fun providePaymentService(
        retrofit: Retrofit
    ): PaymentService{
        return retrofit.create(PaymentService::class.java)
    }

    @Provides
    @Singleton
    fun providePaymentRepository(
        paymentService: PaymentService,
        sharedPreferences: SharedPreferences
    ): PaymentRepository {
        return PaymentRepositoryImpl(sharedPreferences,paymentService)
    }
    //CART

    @Provides
    @Singleton
    fun provideCartService(
        retrofit: Retrofit
    ): CartService{
        return retrofit.create(CartService::class.java)
    }

    @Provides
    @Singleton
    fun provideCartRepository(
        cartService: CartService,
        sharedPreferences: SharedPreferences
    ): CartRepository {
        return CartRepositoryImpl(cartService, sharedPreferences)
    }
}