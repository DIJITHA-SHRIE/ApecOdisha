package com.example.apcodisha

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.apcodisha.DistrictDataModel



@Entity(tableName = "district")
data class BlockResponseModel (
    @PrimaryKey @ColumnInfo(name = "message")
    val message :Int,
    val data :List<DistrictDataModel>
)