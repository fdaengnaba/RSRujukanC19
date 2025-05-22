package id.fdaengnaba.rsrujukanc19

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import id.fdaengnaba.rsrujukanc19.adapter.HospitalAdapter
import id.fdaengnaba.rsrujukanc19.model.Hospital
import id.fdaengnaba.rsrujukanc19.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HospitalAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerHospital)

        ApiClient.hospitalApiService.getHospitals().enqueue(object : Callback<List<Hospital>> {
            override fun onResponse(
                call: Call<List<Hospital>>,
                response: Response<List<Hospital>>
            ) {
                if (response.isSuccessful) {
                    val hospitals = response.body() ?: emptyList()
                    adapter = HospitalAdapter(hospitals)
                    recyclerView.adapter = adapter
                }
            }

            override fun onFailure(call: Call<List<Hospital>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Gagal ambil data", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
