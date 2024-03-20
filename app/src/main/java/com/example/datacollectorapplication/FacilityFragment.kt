//package com.example.datacollectorapplication
//
//import android.annotation.SuppressLint
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.util.Log
//import android.widget.Toast
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.example.datacollectorapplication.adapter.FacilityAdapter
//import com.example.datacollectorapplication.model.Facility
//import com.google.android.material.floatingactionbutton.FloatingActionButton
//
//class FacilityActivity : AppCompatActivity(), AddFacilityDialog.OnFacilityAddedListener  {
//    private lateinit var facilityList: RecyclerView
//    private lateinit var adapter: FacilityAdapter
//    private val facilities = mutableListOf<Facility>()
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_facility)
//
//        facilityList = findViewById(R.id.facility_list_recycler_view)
//        facilityList.layoutManager = LinearLayoutManager(this)
//
//        // Create and set adapter
//        adapter = FacilityAdapter(facilities)
//        facilityList.adapter = adapter
//
//        val fabAdd: FloatingActionButton = findViewById(R.id.floatingActionButton)
//        fabAdd.setOnClickListener {
//            showAddFacilityDialog()
//        }
//
//        // Initialize sample data (if needed)
//        initializeSampleData()
//
////        // Create a list of Facility objects (replace with your data source)
////        val facilities = mutableListOf<Facility>()
////        facilities.add(Facility("Facility A", "Location 1", "123-456-7890", "facilityA@example.com"))
////        facilities.add(Facility("Facility B", "Location 2", "123-456-7890", "facilityB@example.com"))
////        facilities.add(Facility("Facility C", "Location 3", "123-456-7890", "facilityC@example.com"))
////        facilities.add(Facility("Facility D", "Location 4", "123-456-7890", "facilityD@example.com"))
////        facilities.add(Facility("Facility E", "Location 5", "123-456-7890", "facilityE@example.com"))
//
//
//    }
//
//    @SuppressLint("NotifyDataSetChanged")
//    private fun initializeSampleData() {
//        facilities.add(Facility("Facility A", "Location 1", "123-456-7890", "facilityA@example.com"))
//        facilities.add(Facility("Facility B", "Location 2", "123-456-7890", "facilityB@example.com"))
//        facilities.add(Facility("Facility C", "Location 3", "123-456-7890", "facilityC@example.com"))
//        facilities.add(Facility("Facility D", "Location 4", "123-456-7890", "facilityD@example.com"))
//        facilities.add(Facility("Facility E", "Location 5", "123-456-7890", "facilityE@example.com"))
//        adapter.notifyDataSetChanged()
//        Log.d("FacilityActivity", "Facilities list size: ${facilities.size}")
//
//    }
//
//    private fun showAddFacilityDialog() {
//        val dialog = AddFacilityDialog(this, this)
//        dialog.show()
//    }
//
//    @SuppressLint("NotifyDataSetChanged")
//    override fun onFacilityAdded(facility: Facility) {
//        facilities.add(facility)
//        adapter.notifyDataSetChanged()
//        Toast.makeText(this, "Facility added", Toast.LENGTH_SHORT).show()
//    }
//}

package com.example.datacollectorapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.datacollectorapplication.adapter.FacilityAdapter
import com.example.datacollectorapplication.model.Facility
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FacilityFragment : Fragment(), AddFacilityDialog.OnFacilityAddedListener {
    private lateinit var facilityList: RecyclerView
    private lateinit var adapter: FacilityAdapter
    private val facilities = mutableListOf<Facility>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_facility, container, false)

        facilityList = rootView.findViewById(R.id.facility_list_recycler_view)
        facilityList.layoutManager = LinearLayoutManager(requireContext())

        // Create and set adapter
        adapter = FacilityAdapter(facilities)
        facilityList.adapter = adapter

        val fabAdd: FloatingActionButton = rootView.findViewById(R.id.floatingActionButton)
        fabAdd.setOnClickListener {
            showAddFacilityDialog()
        }

        // Initialize sample data (if needed)
        initializeSampleData()

        return rootView
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initializeSampleData() {
        facilities.add(Facility("Facility A", "Location 1", "123-456-7890", "facilityA@example.com"))
        facilities.add(Facility("Facility B", "Location 2", "123-456-7890", "facilityB@example.com"))
        facilities.add(Facility("Facility C", "Location 3", "123-456-7890", "facilityC@example.com"))
        facilities.add(Facility("Facility D", "Location 4", "123-456-7890", "facilityD@example.com"))
        facilities.add(Facility("Facility E", "Location 5", "123-456-7890", "facilityE@example.com"))
        adapter.notifyDataSetChanged()
    }

    private fun showAddFacilityDialog() {
        val dialog = AddFacilityDialog(requireContext(), this)
        dialog.show()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onFacilityAdded(facility: Facility) {
        facilities.add(facility)
        adapter.notifyDataSetChanged()
        Toast.makeText(requireContext(), "Facility added", Toast.LENGTH_SHORT).show()
    }
}