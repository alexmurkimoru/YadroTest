package com.example.yadrotest

import com.google.gson.annotations.SerializedName


data class Location(
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("local_names")
    val localNames: LocalNames? = LocalNames(),
)

data class LocalNames (
    @SerializedName("ru" )
    val ruName : String? = ""
)