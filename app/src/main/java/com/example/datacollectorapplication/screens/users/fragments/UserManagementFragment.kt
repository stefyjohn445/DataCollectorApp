package com.example.datacollectorapplication.screens.users.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.datacollectorapplication.R
import com.example.datacollectorapplication.databinding.DialogAddUserBinding
import com.example.datacollectorapplication.databinding.FragmentUserManagementBinding
import com.example.datacollectorapplication.model.UserManagement
import com.example.datacollectorapplication.screens.users.adapters.UserManagementAdapter

/**
 * A simple [Fragment] subclass.
 * Use the [UserManagementFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserManagementFragment(private val context: Context) : Fragment() {

    private lateinit var userFragmentUserManagementBinding: FragmentUserManagementBinding
    private val usersList = mutableListOf<UserManagement>(
        UserManagement("Jane", "Doe", "jane@gmail.com", "9876543212", "Nurse"),
        UserManagement("Joe", "Doe", "joe@gmail.com", "9846543212", "Prescriber"),
        UserManagement("Jane", "Austin", "jane@gmail.com", "9876643212", "Admin"),
        UserManagement("Jade", "Walter", "jade@gmail.com", "9876673212", "Nurse"),
    )
    private var selectedItem: String = "Admin"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        userFragmentUserManagementBinding = FragmentUserManagementBinding.inflate(layoutInflater)
        return userFragmentUserManagementBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userFragmentUserManagementBinding.userManagementFab.setOnClickListener {
            showAddUserDialog(isAddUser = true)
        }

        userFragmentUserManagementBinding.userManageRecyclerView.layoutManager = LinearLayoutManager(context)
        userFragmentUserManagementBinding.userManageRecyclerView.adapter = UserManagementAdapter(usersList, object :
            UserManagementAdapter.OnItemClickListener {
            override fun onItemClick(user: UserManagement) {
                showAddUserDialog(isAddUser = false, user)
            }
        })

    }

    private fun showAddUserDialog(isAddUser: Boolean, user: UserManagement? = null) {
        val dialogBinding = DialogAddUserBinding.inflate(LayoutInflater.from(context))
        val builder = context?.let {
            AlertDialog.Builder(it)
                .setView(dialogBinding.root)
                .setTitle("Add User")
        }
        createUserRoleDropDown(dialogBinding)
        val alertDialog = builder?.show()

        val window = alertDialog?.window
        window?.setBackgroundDrawableResource(R.drawable.rounded_dialog_background)

        if(isAddUser){
            dialogBinding.saveBtn.setOnClickListener {
                usersList.add(UserManagement(dialogBinding.firstNameET.text.toString(), dialogBinding.lastNameET.text.toString(),
                    dialogBinding.emailET.text.toString(), dialogBinding.phoneNumET.text.toString(),
                    selectedItem))
                alertDialog?.dismiss()
            }
        }else{
            dialogBinding.saveBtn.text = "Update"
            dialogBinding.saveBtn.setOnClickListener {
                alertDialog?.dismiss()
            }

            dialogBinding.firstNameET.setText(user?.firstName)
            dialogBinding.lastNameET.setText(user?.lastName)
            dialogBinding.userRoleSpinner.setSelection(if (user?.userRole == "Admin") 0 else if(user?.userRole == "Nurse") 1 else 2)
            dialogBinding.emailET.setText(user?.email)
            dialogBinding.phoneNumET.setText(user?.phoneNumber)
        }

        dialogBinding.cancelBtn.setOnClickListener {
            alertDialog?.dismiss()
        }

    }

    private fun createUserRoleDropDown(dialogBinding: DialogAddUserBinding) {
        ArrayAdapter.createFromResource(
            context,
            R.array.dropdown_items,
            R.layout.custom_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            dialogBinding.userRoleSpinner.adapter = adapter
        }

        dialogBinding.userRoleSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
               selectedItem = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //Nothing selected
            }

        }
    }
}