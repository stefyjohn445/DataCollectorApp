package com.example.datacollectorapplication.screens.users.adapters

//class PatientAdapter {
//}


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.datacollectorapplication.databinding.PatientItemBinding
//import com.example.datacollectorapplication.databinding.UserManagementItemBinding
import com.example.datacollectorapplication.model.Patient
//import com.example.datacollectorapplication.model.UserManagement

class PatientAdapter(private val patientList: List<Patient>, private val listener: OnItemClickListener): RecyclerView.Adapter<PatientAdapter.ViewHolder>() {
    class ViewHolder(private val patientAdapterBinding: PatientItemBinding) : RecyclerView.ViewHolder(patientAdapterBinding.root) {
        val firstName = patientAdapterBinding.firstNameTV
        val lastName = patientAdapterBinding.lastNameTV
        val weight = patientAdapterBinding.weightTV
        val height = patientAdapterBinding.heightTV
        val prescriberName = patientAdapterBinding.prescriberNameTV
        val email = patientAdapterBinding.patientEmailTV
        val phoneNumber = patientAdapterBinding.patientPhoneNumTV
        val editBtn = patientAdapterBinding.editBtn
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PatientItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = patientList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val patient = patientList[position]
        holder.firstName.text = patient.firstName
        holder.lastName.text = patient.lastName
        holder.weight.text = patient.weight
        holder.height.text = patient.height
//        holder.prescriberName = patient.prescriberName
        holder.email.text = patient.email
        holder.phoneNumber.text = patient.phoneNumber
        holder.editBtn.setOnClickListener {
            listener.onItemClick(patient)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(patient: Patient)
    }
}