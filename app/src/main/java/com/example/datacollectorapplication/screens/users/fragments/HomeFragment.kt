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
import com.example.datacollectorapplication.databinding.DialogAddPatientBinding
import com.example.datacollectorapplication.databinding.FragmentHomeBinding
import com.example.datacollectorapplication.model.Patient
import com.example.datacollectorapplication.screens.users.adapters.PatientAdapter

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment(private val context: Context) : Fragment() {

    private lateinit var homeFragmentHomeBinding: FragmentHomeBinding

    private val patientList = mutableListOf<Patient>(
        Patient("145","Jane", "Doe", "50 Wt", "164 Ht", "jane@gmail.com","9846543212","John"),
        Patient("243","Jil", "Dawn", "60 Wt", "167 Ht", "jil@gmail.com","9846543278","Luke"),
        Patient("456","Jack", "Austin", "70 Wt", "168 Ht", "jack@gmail.com","9846543216","Jake"),
        Patient("134","Jake", "Shawn", "80 Wt", "171 Ht", "jake@gmail.com","9846543219","Luke"),
        Patient("154","John", "Del", "60 Wt", "167 Ht", "john@gmail.com","9846543218","John"),
    )
    private var selectedItem: String = "Luke"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home, container, false)
        homeFragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)
        return homeFragmentHomeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeFragmentHomeBinding.homeFab.setOnClickListener {
            showAddPatientDialog(isAddPatient = true)
        }

        homeFragmentHomeBinding.homeRecyclerView.layoutManager = LinearLayoutManager(context)
        homeFragmentHomeBinding.homeRecyclerView.adapter = PatientAdapter(patientList, object :
            PatientAdapter.OnItemClickListener {
            override fun onItemClick(patient: Patient) {
                showAddPatientDialog(isAddPatient = false, patient)
            }
        })

    }

    private fun showAddPatientDialog(isAddPatient: Boolean, patient: Patient? = null) {
        val dialogBinding = DialogAddPatientBinding.inflate(LayoutInflater.from(context))
        val builder = context?.let {
            AlertDialog.Builder(it)
                .setView(dialogBinding.root)
                .setTitle("Add Patient")
        }
        createPrescriberNameDropDown(dialogBinding)
        val alertDialog = builder?.show()

        val window = alertDialog?.window
        window?.setBackgroundDrawableResource(R.drawable.rounded_dialog_background)

        if(isAddPatient){
            dialogBinding.saveBtn.setOnClickListener {
                patientList.add(Patient(dialogBinding.patchIDET.text.toString(),dialogBinding.firstNameET.text.toString(), dialogBinding.lastNameET.text.toString(),
                    dialogBinding.weightET.text.toString(),dialogBinding.heightET.text.toString(),dialogBinding.emailET.text.toString(), dialogBinding.phoneNumET.text.toString(),selectedItem
                    ))

                alertDialog?.dismiss()
            }
        }else{
            dialogBinding.saveBtn.text = "Update"
            dialogBinding.saveBtn.setOnClickListener {
                alertDialog?.dismiss()
            }

            dialogBinding.firstNameET.setText(patient?.firstName)
            dialogBinding.lastNameET.setText(patient?.lastName)
            dialogBinding.weightET.setText(patient?.weight)
            dialogBinding.heightET.setText(patient?.height)
//            dialogBinding.prescriberNameSpinner.setSelection(patient?.prescriberName)
            dialogBinding.emailET.setText(patient?.email)
            dialogBinding.phoneNumET.setText(patient?.phoneNumber)
        }

        dialogBinding.cancelBtn.setOnClickListener {
            alertDialog?.dismiss()
        }

    }

    private fun createPrescriberNameDropDown(dialogBinding: DialogAddPatientBinding) {
        ArrayAdapter.createFromResource(
            context,
            R.array.dropdown_prescriber,
            R.layout.custom_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            dialogBinding.prescriberNameSpinner.adapter = adapter
        }

        dialogBinding.prescriberNameSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
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