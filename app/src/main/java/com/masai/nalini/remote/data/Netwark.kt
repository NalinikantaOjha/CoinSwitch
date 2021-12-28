package com.masai.nalini.remote.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Netwark {
    companion object{

        val BaseUrl="https://pro-api.coinmarketcap.com/"

        fun getInstance():Retrofit{
            return Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
// val userApi= getInstance().create(ApiService::class.java)

}