package com.ibrahim.demo.cardanim

import com.ibrahim.demo.cardanim.model.Results
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitApiService {

    @GET("api/?results=10")
    fun randomUser() : Deferred<Response<Results>>


}