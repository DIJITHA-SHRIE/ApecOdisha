package com.example.apcodisha

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "signup")
data class RegistrationModel(
    @PrimaryKey @ColumnInfo(name = "message")
    val message:String
)
