package com.example.datacollectorapplication.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.datacollectorapplication.screens.facility.FacilityActivity
import com.example.datacollectorapplication.R
import com.example.datacollectorapplication.model.Facility

class FacilityAdapter(private val facilities: MutableList<Facility>) : RecyclerView.Adapter<FacilityViewHolder>() {

    // Update the adapter's list of facilities
    @SuppressLint("NotifyDataSetChanged")
    fun updateFacilityList(newFacilities: List<Facility>) {
        facilities.clear()
        facilities.addAll(newFacilities)
        notifyDataSetChanged()
    }

    fun updateFacility(updatedFacility: Facility) {
        val position = facilities.indexOfFirst { it.id == updatedFacility.id }
        Log.d("UpdateFacility", "Position: $position")
        if (position != -1) {
            facilities[position] = updatedFacility
            notifyItemChanged(position)
        }else {
            Log.d("UpdateFacility", "Facility not found for ID: ${updatedFacility.id}")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacilityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.facility_item, parent, false)
        return FacilityViewHolder(view, this)
    }

    override fun onBindViewHolder(holder: FacilityViewHolder, position: Int) {
        val facility = facilities[position]
        holder.bind(facility)
    }

    override fun getItemCount(): Int = facilities.size

    fun getFacilityAtPosition(position: Int): Facility {
        return facilities[position]
    }
}

class FacilityViewHolder(itemView: View, private val adapter: FacilityAdapter) : RecyclerView.ViewHolder(itemView) {
    val facilityName: TextView = itemView.findViewById(R.id.facility_name)
    val location: TextView = itemView.findViewById(R.id.location)
    val contactNumber: TextView = itemView.findViewById(R.id.contact_number)
    val email: TextView = itemView.findViewById(R.id.email)
    val editIcon: ImageView = itemView.findViewById(R.id.imgEdit)

    fun bind(facility: Facility) {
        facilityName.text = facility.name
        location.text = facility.location
        contactNumber.text = facility.contactNumber
        email.text = facility.email
    }

    init {
        editIcon.setOnClickListener {
            val facility = adapter.getFacilityAtPosition(adapterPosition)
            val context = itemView.context
            if (context is FacilityActivity) {
                context.showAddFacilityDialog(true, facility)
            }
        }
    }
}
