package com.ibrahim.demo.cardanim.model

import com.ibrahim.demo.cardanim.RetrofitApiService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitFactory  {
    const val BASE_URL = "https://randomuser.me/"//api/?results=10

    fun makeRetrofitService(): RetrofitApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build().create(RetrofitApiService::class.java)
    }
}