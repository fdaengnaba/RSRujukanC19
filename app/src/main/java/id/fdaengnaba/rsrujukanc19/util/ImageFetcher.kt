package id.fdaengnaba.rsrujukanc19.util

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

object ImageFetcher {
    fun getImageUrlFromWikipedia(hospitalName: String): String? {
        return try {
            val client = OkHttpClient()
            val encodedTitle = hospitalName.replace(" ", "_")
            val url =
                "https://en.wikipedia.org/api/rest_v1/page/summary/$encodedTitle"

            val request = Request.Builder()
                .url(url)
                .build()

            val response = client.newCall(request).execute()

            if (response.isSuccessful) {
                val json = JSONObject( response.body?.string() ?: "")
                if (json.has("thumbnail")) {
                    return json.getJSONObject("thumbnail").getString("source")
                }
            }
            null
        } catch (e: Exception) {
            Log.e("ImageFetcher", "Error fetching image: ${e.message}")
            null
        }
    }
}