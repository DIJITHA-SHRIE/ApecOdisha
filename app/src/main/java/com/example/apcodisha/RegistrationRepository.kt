package com.example.apcodisha

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow


@ExperimentalCoroutinesApi
@FlowPreview
class RegistrationRepository private constructor(
    private val networkService: NetworkService,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
)
{

   suspend fun getRegistrationWithFlow(registrationInputModel: RegistrationInputModel):RegistrationModel {
        return networkService.registrationService(registrationInputModel)
   }





    companion object {
        // For Singleton instantiation
        @Volatile private var instance: RegistrationRepository? = null

        fun getInstance(plantService: NetworkService) =
            instance ?: synchronized(this) {
                instance ?: RegistrationRepository( plantService).also { instance = it }
            }
    }

    }