package com.example.apcodisha

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow


@ExperimentalCoroutinesApi
@FlowPreview
class RegistrationRepository private constructor(
    private val apecDao: ApecDao,
    private val networkService: NetworkService,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    suspend fun getRegistrationWithFlow(registrationInputModel: RegistrationInputModel) {
        val regResult = networkService.registrationService(registrationInputModel)
        apecDao.insertAll(regResult)

    }

    fun getRegistrationWithFlowDAO(): Flow<RegistrationModel> {
        return apecDao.getRegRoomFlow()

    }

    suspend fun getDistrictWithFlow() {
        val regResult = networkService.districtService()
        Log.i("districtResult","${regResult.message}")
        apecDao.insertAllDistricts(regResult.data)

    }

    fun getDistrictWithFlowDAO(): Flow<List<DistrictDataModel>> {
        return apecDao.getDistrictFlow()

    }


    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: RegistrationRepository? = null

        fun getInstance(apecDao: ApecDao, plantService: NetworkService) =
            instance ?: synchronized(this) {
                instance ?: RegistrationRepository(apecDao, plantService).also { instance = it }
            }
    }

}