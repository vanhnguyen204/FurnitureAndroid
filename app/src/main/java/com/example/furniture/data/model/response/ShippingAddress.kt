package com.example.furniture.data.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ShippingAddress (
   @SerializedName("_id")
   val id: String,
    @SerializedName("userId")
   val userId: String,
   @SerializedName("country")
   val country: String,
   @SerializedName("city")
   val city: String,
   @SerializedName("district")
   val district: String,
   @SerializedName("addressDetail")
   val addressDetail: String,
   @SerializedName("recipient")
   val recipient: String,
   @SerializedName("isSelected")
   var isSelected: Boolean
)