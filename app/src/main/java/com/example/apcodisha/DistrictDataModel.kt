package com.example.apcodisha

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "districtList")
data class DistrictDataModel(
    @PrimaryKey @ColumnInfo(name = "id")
    val id:Int,
    val district_name:String
)
