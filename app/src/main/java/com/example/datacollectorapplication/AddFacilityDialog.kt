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

class AddFacilityDialog(context: Context,  private val facility: Facility?,
                        private val listener: OnFacilityActionListener?,
                        private val isEditMode: Boolean = false) : Dialog(context) {

    interface OnFacilityActionListener  {
        fun onFacilityAdded(facility: Facility)
        fun onFacilityEdited(facility: Facility)
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
        val buttonAction = findViewById<Button>(R.id.buttonSave)

        if (isEditMode && facility != null) {
            // If in edit mode, populate fields with existing facility data
            editTextFacilityName.setText(facility.name)
            editTextLocation.setText(facility.location)
            editTextContactNumber.setText(facility.contactNumber)
            editTextEmail.setText(facility.email)
            buttonAction.text = context.getString(R.string.update) // Change button text to "Update"
        }else {
            buttonAction.text = context.getString(R.string.save) // Set button text to "Save" for add mode
        }

        buttonAction.setOnClickListener {
            val editedFacility = Facility(
                id = facility?.id ?: "", // Use the existing ID if available, or an empty string otherwise
                name = editTextFacilityName.text.toString(),
                location = editTextLocation.text.toString(),
                contactNumber = editTextContactNumber.text.toString(),
                email = editTextEmail.text.toString()
            )

            if (isEditMode) {
                listener?.onFacilityEdited(editedFacility)
            } else {
                listener?.onFacilityAdded(editedFacility)
            }
            dismiss()
        }
    }
}
