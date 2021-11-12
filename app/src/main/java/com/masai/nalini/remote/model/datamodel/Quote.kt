package com.masai.nalini.remote.model.datamodel


import com.google.gson.annotations.SerializedName

data class Quote(
    @SerializedName("USD")
    val uSD: USD
)