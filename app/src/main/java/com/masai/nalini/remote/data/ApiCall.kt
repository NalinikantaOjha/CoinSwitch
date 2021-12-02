package com.masai.nalini.remote.data


import com.masai.nalini.remote.model.datamodel.ModelDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiCall {
    @GET("api/users/{ID}")
    fun getUserDetails(@Path("ID") id: Int): Call<ModelDto>

    @GET("v1/cryptocurrency/listings/latest")
    fun getUserDetails2(
        @Header("X-CMC_PRO_API_KEY") token: String
    ): Call<ModelDto>
}