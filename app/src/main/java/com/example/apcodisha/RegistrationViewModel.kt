package com.example.apcodisha

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


@ExperimentalCoroutinesApi
@FlowPreview
class RegistrationViewModel internal constructor(
    private val registrationRepository: RegistrationRepository
):ViewModel() {

    val registrationMutableLiveData = MutableLiveData<RegistrationModel>()
    lateinit var registrationInputModel:RegistrationInputModel


    private val registrationInput = ConflatedBroadcastChannel<RegistrationInputModel>()

   /* val registrationUsingFlow: LiveData<RegistrationModel> = registrationInput.asFlow()
        .flatMapLatest { growZone ->

            registrationRepository.getRegistrationWithFlow(registrationInputModel)

        }.asLiveData()*/

    // can call by creating a function too
    init {

    }

    fun callRegistration(registrationInputModel: RegistrationInputModel){
        /*registrationInput.asFlow()
            .flatMapLatest { growZone ->
               // launchDataLoad {  registrationRepository.getRegistrationWithFlow(registrationInputModel)}
                registrationRepository.getRegistrationWithFlow(registrationInputModel).conflate()

            }.asLiveData()*/


        launchDataLoad {  registrationRepository.getRegistrationWithFlow(registrationInputModel)}


    }
    private fun launchDataLoad(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            try {
                block()
            } catch (error: Throwable) {
                Log.i("ViewModelCatch",error.localizedMessage)

            } finally {
                Log.i("FinalViewModel","error")
            }
        }
    }








}