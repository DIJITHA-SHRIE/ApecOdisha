package com.example.apcodisha

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

class NetworkService {

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://door2dream.com/demo/ae_training_app/m_api/")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apecService = retrofit.create(ApecService::class.java)

    @ExperimentalCoroutinesApi
    suspend fun registrationService(registrationInputModel: RegistrationInputModel): RegistrationModel =

        withContext(Dispatchers.Default) {
            delay(1500)
            val result = apecService.getRegistrrationDone(
                registrationInputModel.name,
                registrationInputModel.email,
                registrationInputModel.phone,
                registrationInputModel.password,
                registrationInputModel.address,
                registrationInputModel.father_name,
                registrationInputModel.maretial_status,
                registrationInputModel.caste,
                registrationInputModel.cultivation_land,
                registrationInputModel.pan_no,
                registrationInputModel.district,
                registrationInputModel.block,
                registrationInputModel.gram_panchayat,
                registrationInputModel.village,
                registrationInputModel.dob,
                registrationInputModel.gender,
                registrationInputModel.aadhar_no,
                registrationInputModel.declaration
            )
            try {
                Log.i("resultMessage", result.message)
            } catch (e: Exception) {
                Log.i("resultError", e.localizedMessage)

            }
            result
        }

    @ExperimentalCoroutinesApi
    suspend fun districtService(): BlockResponseModel =
        withContext(Dispatchers.Default) {
            delay(1000)
          val result =  apecService.getDistrictList()
            result
        }




}

interface ApecService {
    @FormUrlEncoded
    @POST("m_participant_register.php")
    suspend fun getRegistrrationDone(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("phone") phone: String,
        @Field("password") password: String,
        @Field("address") address: String,
        @Field("father_name") father_name: String,
        @Field("maretial_status") maretial_status: String,
        @Field("caste") caste: String,
        @Field("cultivation_land") cultivation_land: String,
        @Field("pan_no") pan_no: String,
        @Field("district") district: String,
        @Field("block") block: String,
        @Field("gram_panchayat") gram_panchayat: String,
        @Field("village") village: String,
        @Field("dob") dob: String,
        @Field("gender") gender: String,
        @Field("aadhar_no") aadhar_no: String,
        @Field("declaration") declaration: String
    ): RegistrationModel

    @GET("m_district_api.php")
    suspend fun  getDistrictList(): BlockResponseModel

}