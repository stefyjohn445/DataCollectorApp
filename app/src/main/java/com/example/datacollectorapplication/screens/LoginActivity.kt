package com.example.datacollectorapplication.screens

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import com.example.datacollectorapplication.screens.facility.FacilityActivity
import com.example.datacollectorapplication.R
import com.example.datacollectorapplication.databinding.ActivityLoginBinding
import com.example.datacollectorapplication.model.UiState
import com.example.datacollectorapplication.screens.users.UsersActivity
import com.example.datacollectorapplication.viewmodels.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var loginBinding: ActivityLoginBinding
    private var isPasswordShowing = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        loginBinding.signInButton.setOnClickListener {
           getLoginCredentials()
        }

        onClickOfPasswordIcon()
    }

    private fun getLoginCredentials(){
        val username = loginBinding.userIDET.text.toString().trim()
        val password = loginBinding.passwordET.text.toString().trim()

        val loginViewModel = loginViewModel(username, password)
        loginViewModel.checkUserCredentials(username, password)

    }

    private fun loginViewModel(username: String, password: String): LoginViewModel {
        val viewModelLogin = LoginViewModel()
        viewModelLogin.uiState.observe(this){ state ->
            when(state){
                is UiState.Loading -> {
                    if(state.isLoading){
                        Toast.makeText(this@LoginActivity, "Loading...", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this@LoginActivity, "Loading Completed", Toast.LENGTH_SHORT).show()
                    }
                }

                is UiState.Success -> {
                    if(state.result == "Success"){
                        when (username) {
                            "sa@gmail.com" -> {
                                val intent = Intent(this, FacilityActivity::class.java)
                                startActivity(intent)
                            }
                            "admin@gmail.com" -> {
                                val intent = Intent(this, UsersActivity::class.java)
                                intent.putExtra("USER_ROLE", "Admin")
                                startActivity(intent)
                                finish()
                            }
                            else -> {
                                val intent = Intent(this, UsersActivity::class.java)
                                intent.putExtra("USER_ROLE", "Nurse")
                                startActivity(intent)
                                finish()
                            }
                        }
                    }else{
                        Toast.makeText(this@LoginActivity, state.result, Toast.LENGTH_SHORT).show()
                        loginBinding.passwordET.setText(password)
                        loginBinding.userIDET.setText(username)
                    }
                }

                is UiState.Error -> {
                    Toast.makeText(this@LoginActivity, state.message, Toast.LENGTH_SHORT).show()
                    loginBinding.passwordET.setText(password)
                    loginBinding.userIDET.setText(username)
                }
            }
        }

        return viewModelLogin
    }

    /**
     * Description : This method is used to show or hide password based on user input
     */
    @SuppressLint("ClickableViewAccessibility", "UseCompatLoadingForDrawables")
    private fun onClickOfPasswordIcon() {
        val showDrawable = this.resources.getDrawable(R.drawable.show_password_icon)
        val hideDrawable = this.resources.getDrawable(R.drawable.hide_password_icon)
        loginBinding.passwordET.setOnTouchListener { v: View?, event: MotionEvent ->
            val drawableRight = 2
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= loginBinding.passwordET.right - loginBinding.passwordET.compoundDrawables[drawableRight].bounds.width() - loginBinding.passwordET.paddingRight) {
                    if (isPasswordShowing) {
                        isPasswordShowing = false
                        loginBinding.passwordET.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            null,
                            null,
                            showDrawable,
                            null
                        )
                        loginBinding.passwordET.transformationMethod =
                            HideReturnsTransformationMethod.getInstance()
                    } else {
                        isPasswordShowing = true
                        loginBinding.passwordET.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            null,
                            null,
                            hideDrawable,
                            null
                        )
                        loginBinding.passwordET.transformationMethod =
                            PasswordTransformationMethod.getInstance()
                    }
                    loginBinding.passwordET.text?.length?.let {
                        loginBinding.passwordET.setSelection(
                            it
                        )
                    }
                }
            }
            false
        }
    }
}