package com.masai.nalini.remote.data

import com.masai.nalini.remote.model.datamodel.ModelDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {
    @GET("v1/cryptocurrency/listings/latest")
    suspend fun getData(
        @Header("X-CMC_PRO_API_KEY") token: String
    ): Response<ModelDto>
}