package id.fdaengnaba.rsrujukanc19.network

import id.fdaengnaba.rsrujukanc19.model.Hospital
import retrofit2.Call
import retrofit2.http.GET

interface HospitalApiService {
    @GET("api/id/covid19/hospitals")
    fun getHospitals(): Call<List<Hospital>>
}