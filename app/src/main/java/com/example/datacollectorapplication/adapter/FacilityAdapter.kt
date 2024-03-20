package com.example.datacollectorapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.datacollectorapplication.R
import com.example.datacollectorapplication.model.Facility

class FacilityAdapter(private val facilities: List<Facility>) : RecyclerView.Adapter<FacilityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacilityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.facility_item, parent, false)
        return FacilityViewHolder(view)
    }

    override fun onBindViewHolder(holder: FacilityViewHolder, position: Int) {
        val facility = facilities[position]
        holder.facilityName.text = facility.name
        holder.location.text = facility.location
        holder.contactNumber.text = facility.contactNumber
        holder.email.text = facility.email
    }

    override fun getItemCount(): Int = facilities.size
}

class FacilityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val facilityName: TextView = itemView.findViewById(R.id.facility_name)
    val location: TextView = itemView.findViewById(R.id.location)
    val contactNumber: TextView = itemView.findViewById(R.id.contact_number)
    val email: TextView = itemView.findViewById(R.id.email)


}
