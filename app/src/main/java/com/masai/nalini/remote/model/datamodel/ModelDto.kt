package com.masai.nalini.remote.model.datamodel


import com.google.gson.annotations.SerializedName

data class ModelDto(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("status")
    val status: Status
)