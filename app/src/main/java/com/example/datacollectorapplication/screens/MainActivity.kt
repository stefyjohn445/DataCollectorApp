package com.example.datacollectorapplication.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import com.example.datacollectorapplication.FacilityFragment
import com.example.datacollectorapplication.R

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
//    private val requestPermissionLauncher =
//        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
//            if (isGranted) {
//                openCamScanner()
//            }
//        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openFacilityFragment()


//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
//        navController = navHostFragment.navController
//
//        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
//        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.homeFragment -> {
//                    navController.navigate(R.id.facilityFragment)
//                    true
//                }
//                // Add other cases for navigation to different fragments if needed
//                else -> false
//            }
//        }
    }

    private fun openFacilityFragment() {
        val facilityFragment = FacilityFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, facilityFragment) // R.id.fragment_container is the id of the FrameLayout in your activity_main.xml where you want to place the fragment
            .addToBackStack(null) // Add the transaction to the back stack
            .commit()
    }
}
//        val scanPdfButton: Button = findViewById(R.id.scanPdfButton)
//        scanPdfButton.setOnClickListener {
//            checkPermissionsAndOpenCamScanner()
//        }

        // Initialize NavHostFragment
//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
//        navController = navHostFragment.navController
//
//        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
//        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.homeFragment -> {
//                    navController.navigate(R.id.homeFragment)
//                    true
//                }
//                // Add other menu items here if needed
//                else -> false
//            }
//        }
//    }
//    }

//    private fun checkPermissionsAndOpenCamScanner() {
//        if (checkSelfPermission(android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
//            openCamScanner()
//        } else {
//            requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)
//        }
//    }

//    private fun openCamScanner() {
//
//    }
