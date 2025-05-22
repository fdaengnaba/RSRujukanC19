package id.fdaengnaba.rsrujukanc19

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        val province = intent.getStringExtra("province") ?: "DKI Jakarta"

        // Dummy koordinat beberapa provinsi
        val coordinates = mapOf(
            "DKI Jakarta" to LatLng(-6.2088, 106.8456),
            "Jawa Barat" to LatLng(-6.9034, 107.5731),
            "Jawa Tengah" to LatLng(-7.0051, 110.4381),
            "Jawa Timur" to LatLng(-7.5361, 112.2384),
            "Bali" to LatLng(-8.4095, 115.1889)
        )

        val latLng = coordinates[province] ?: LatLng(-6.2088, 106.8456)

        map.addMarker(MarkerOptions().position(latLng).title("Provinsi: $province"))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 8f))
    }
}