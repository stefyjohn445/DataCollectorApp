package com.example.datacollectorapplication.screens.users

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.datacollectorapplication.R
import com.example.datacollectorapplication.databinding.ActivityUsersBinding
import com.example.datacollectorapplication.screens.users.fragments.HomeFragment
import com.example.datacollectorapplication.screens.users.fragments.UserManagementFragment

class UsersActivity : AppCompatActivity() {

    private lateinit var homeFragment: HomeFragment
    private lateinit var userManagementFragment: UserManagementFragment

    private lateinit var usersBinding: ActivityUsersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        usersBinding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(usersBinding.root)

        homeFragment = HomeFragment(this)
        userManagementFragment = UserManagementFragment(this)

        setStartUpFragment()
        onMenuOptionSelected()
        setOnFragmentSwiped()
    }

    private fun setStartUpFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.usersScreenFrameLayout, homeFragment)
            .commit()
    }

    private fun setOnFragmentSwiped() {
        usersBinding.usersScreenSwipeRefresh.setOnRefreshListener {
            val fragment =
                supportFragmentManager.findFragmentById(R.id.usersScreenFrameLayout)
            if (fragment is OnSwipeListener) {
                (fragment as OnSwipeListener?)?.onSwipeUp()
            }
        }
    }

    private fun onMenuOptionSelected(){
        usersBinding.bottomNavBar.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.usersScreenFrameLayout, homeFragment).commit()
                }

                R.id.user_management -> {
                    supportFragmentManager.beginTransaction().replace(R.id.usersScreenFrameLayout, userManagementFragment).commit()
                }

                else -> { Toast.makeText(this@UsersActivity, "No Id Found", Toast.LENGTH_SHORT).show() }
            }
            true
        }
    }
}

interface OnSwipeListener {
    fun onSwipeUp()
}