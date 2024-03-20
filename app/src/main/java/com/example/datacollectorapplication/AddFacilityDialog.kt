package com.example.datacollectorapplication

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.datacollectorapplication.model.Facility

class AddFacilityDialog(context: Context, private val listener: OnFacilityAddedListener) : Dialog(context) {

    interface OnFacilityAddedListener {
        fun onFacilityAdded(facility: Facility)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_facility)

        // Adjust dialog window size
        val window = window
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        val editTextFacilityName = findViewById<EditText>(R.id.editTextFacilityName)
        val editTextLocation = findViewById<EditText>(R.id.editTextLocation)
        val editTextContactNumber = findViewById<EditText>(R.id.editTextContactNumber)
        val editTextEmail = findViewById<EditText>(R.id.editTextEmail)
        val buttonSave = findViewById<Button>(R.id.buttonSave)

        buttonSave.setOnClickListener {
            val facilityName = editTextFacilityName.text.toString()
            val location = editTextLocation.text.toString()
            val contactNumber = editTextContactNumber.text.toString()
            val email = editTextEmail.text.toString()
            val facility = Facility(facilityName, location, contactNumber, email)
            listener.onFacilityAdded(facility)
            dismiss()
        }
    }
}
