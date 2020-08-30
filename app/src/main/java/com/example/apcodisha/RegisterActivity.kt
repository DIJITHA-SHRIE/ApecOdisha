package com.example.apcodisha

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    lateinit var loginButton: Button
    lateinit var registerButton: Button


    private val viewModel: RegistrationViewModel by viewModels {
        Injector.provideViewModelFactory(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        loginButton = findViewById(R.id.login_button)
        registerButton = findViewById(R.id.register_button)


        val maritalStatusArray = resources.getStringArray(R.array.maritalstatus)
        val maritalAdapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, maritalStatusArray)
        marital_dropdown.setAdapter(maritalAdapter)

        viewModel.callDistrict()

        viewModel.districtUsingFlow.observe(this) { value ->
            if (value != null && value.size > 0) {
                Log.i("DistrictMessage", "${value.get(0).district_name}")
            }

            val districtarray = arrayOfNulls<String>(value.size)

            for (i in value.indices) {
                districtarray[i] = value.get(i).district_name
            }

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, districtarray)
            district_aeps.setAdapter(adapter)
        }

        loginButton.setOnClickListener {
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)

        }

        registerButton.setOnClickListener {

            if (email_text.text.toString().length != 0 && aepsPassword.text.toString().length != 0 &&
                aepsPassword.text.toString().equals(aepsConfrimPassword.text.toString())
            ) {
                // send input and call
                val registrationInputModel = RegistrationInputModel(
                    aepsName.text.toString(), email_text.text.toString(), "1234567890",
                    aepsPassword.text.toString(), "yy", "yy", "yy", "yy", "yy", "yy",
                    "yy", "yy", "yy", "yy", "yy", "yy", "yy", "yy"
                )



                viewModel.callRegistration(registrationInputModel)
            } else {
                Toast.makeText(
                    applicationContext,
                    "Please enter email and passowrd correctly",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        viewModel.registrationUsingFlow.observe(this) { value ->

            if (value != null) {
                Log.i("RegistrationMessage", value.message)
                Toast.makeText(applicationContext, "Registaration success", Toast.LENGTH_LONG)
                    .show()
            }


            // need to observe the value, once the value is set from viewmodel.

        }


    }

}


class RegistrationViewModelFactory(private val repository: RegistrationRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        RegistrationViewModel(repository) as T

}