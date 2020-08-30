package com.example.apcodisha

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ApecDao {

    @Query("SELECT * from signup")
    fun getRegRoomFlow(): Flow<RegistrationModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(registrationModel:RegistrationModel)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllDistricts(districtList: List<DistrictDataModel>)


    @Query("SELECT * from districtList")
    fun getDistrictFlow(): Flow<List<DistrictDataModel>>


}