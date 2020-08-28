package com.example.apcodisha

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe

class RegisterActivity : AppCompatActivity() {
    lateinit var loginButton:Button
    lateinit var registerButton:Button

    private val viewModel: RegistrationViewModel by viewModels{
        Injector.provideViewModelFactory(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        loginButton = findViewById(R.id.login_button)
        registerButton = findViewById(R.id.register_button)

        loginButton.setOnClickListener{
            val intent = Intent(this,LogInActivity::class.java)
            startActivity(intent)
        }

        registerButton.setOnClickListener{
             // send input and call
            val registrationInputModel = RegistrationInputModel("Dijitha","yyy@gmail.com","1234567890",
                "1111111111","yy","yy","yy","yy","yy","yy",
                "yy","yy","yy","yy","yy","yy","yy","yy")


            viewModel.callRegistration(registrationInputModel)
            }

        viewModel.registrationMutableLiveData.observe(this){value ->
            Log.i("RegistrationMessage",value.message)

            // need to observe the value, once the value is set from viewmodel.

        }

        }

    }


class RegistrationViewModelFactory(private val repository: RegistrationRepository):
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = RegistrationViewModel(repository) as T

}