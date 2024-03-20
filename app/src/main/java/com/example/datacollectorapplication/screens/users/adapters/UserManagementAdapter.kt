package com.example.datacollectorapplication.screens.users.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.datacollectorapplication.databinding.UserManagementItemBinding
import com.example.datacollectorapplication.model.UserManagement

class UserManagementAdapter(private val usersList: List<UserManagement>, private val listener: OnItemClickListener): RecyclerView.Adapter<UserManagementAdapter.ViewHolder>() {
    class ViewHolder(private val userManagementAdapterBinding: UserManagementItemBinding) : RecyclerView.ViewHolder(userManagementAdapterBinding.root) {
        val firstName = userManagementAdapterBinding.firstNameTV
        val lastName = userManagementAdapterBinding.lastNameTV
        val userRole = userManagementAdapterBinding.userRoleTV
        val email = userManagementAdapterBinding.userEmailTV
        val phoneNumber = userManagementAdapterBinding.userPhoneNumTV
        val editBtn = userManagementAdapterBinding.editBtn
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = UserManagementItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = usersList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = usersList[position]
        holder.firstName.text = user.firstName
        holder.lastName.text = user.lastName
        holder.userRole.text = user.userRole
        holder.email.text = user.email
        holder.phoneNumber.text = user.phoneNumber

        holder.editBtn.setOnClickListener {
            listener.onItemClick(user)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(user: UserManagement)
    }
}