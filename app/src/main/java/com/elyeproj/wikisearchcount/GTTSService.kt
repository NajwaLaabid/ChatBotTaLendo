package com.elyeproj.wikisearchcount

import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface GTTSService {
    @Headers("X-Goog-Api-Key: AIzaSyCnYLnu23mKvpbBNxDLOzDfQvl7z4AhQ2Q")
    @POST("v1beta1/text:synthesize")
    fun read(@Body ttr: TextToRead) : Observable<Model.Result>

    companion object {

        fun create(): GTTSService {
            val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            }

            val client : OkHttpClient = OkHttpClient.Builder().apply {
                this.addInterceptor(interceptor)
            }.build()

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://texttospeech.googleapis.com/")
                    .client(client)
                    .build()

            return retrofit.create(GTTSService::class.java)
        }
    }

}