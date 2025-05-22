package id.fdaengnaba.rsrujukanc19.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://dekontaminasi.com/"

    val hospitalApiService: HospitalApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HospitalApiService::class.java)
    }
}