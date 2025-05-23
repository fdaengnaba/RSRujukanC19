package id.fdaengnaba.rsrujukanc19.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.fdaengnaba.rsrujukanc19.MapActivity
import id.fdaengnaba.rsrujukanc19.R
import id.fdaengnaba.rsrujukanc19.model.Hospital
import id.fdaengnaba.rsrujukanc19.util.ImageFetcher

class HospitalAdapter(private val hospitalList: List<Hospital>) :
        RecyclerView.Adapter<HospitalAdapter.HospitalViewHolder>() {

            class HospitalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
                val imageHospital: ImageView = itemView.findViewById(R.id.imageHospital)
                val textName: TextView = itemView.findViewById(R.id.textName)
                val textAddress: TextView = itemView.findViewById(R.id.textAddress)
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_hospital, parent, false)

        return HospitalViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: HospitalViewHolder,
        position: Int
    ) {
        val hospital = hospitalList[position]
        holder.textName.text = hospital.name
        holder.textAddress.text = hospital.address
        holder.textAddress.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, MapActivity::class.java)
            intent.putExtra("province", hospital.province)
            context.startActivity(intent)
        }

        Thread {
            val imageUrl = ImageFetcher.getImageUrlFromWikipedia(hospital.name)

            (holder.itemView.context as? android.app.Activity)?.runOnUiThread {
                Glide.with(holder.itemView.context)
                    .load(imageUrl)
                    .placeholder(R.drawable.hospital_placeholder)
                    .error(R.drawable.hospital_placeholder)
                    .into(holder.imageHospital)
            }
        }.start()
    }

    override fun getItemCount(): Int = hospitalList.size
        }