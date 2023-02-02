package ru.kristylopatina.attendanceapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.security.auth.Subject

@Entity(tableName = "attendances")
data class Class(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    @ColumnInfo
    val name : String,
    @ColumnInfo
    val number : Int
)