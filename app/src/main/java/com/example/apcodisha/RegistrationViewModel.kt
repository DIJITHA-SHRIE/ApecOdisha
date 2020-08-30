package com.example.apcodisha

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


@ExperimentalCoroutinesApi
@FlowPreview
class RegistrationViewModel internal constructor(
    private val registrationRepository: RegistrationRepository
) : ViewModel() {


    val registrationUsingFlow: LiveData<RegistrationModel> =
        registrationRepository.getRegistrationWithFlowDAO().asLiveData()


    val districtUsingFlow: LiveData<List<DistrictDataModel>> =
        registrationRepository.getDistrictWithFlowDAO().asLiveData()

    // can call by creating a function too
    init {

    }

    fun callRegistration(registrationInputModel: RegistrationInputModel) {

        launchDataLoad { registrationRepository.getRegistrationWithFlow(registrationInputModel) }

    }

    fun callDistrict() {

        launchDataLoad { registrationRepository.getDistrictWithFlow() }

    }


    private fun launchDataLoad(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            try {
                block()
            } catch (error: Throwable) {
                Log.i("ViewModelCatch", error.localizedMessage)

            } finally {
                Log.i("FinalViewModel", "error")
            }
        }
    }


}