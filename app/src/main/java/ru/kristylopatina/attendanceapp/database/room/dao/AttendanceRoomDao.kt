package ru.kristylopatina.attendanceapp.database.room.dao

import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ru.kristylopatina.attendanceapp.model.Attendance

@Dao
interface AttendanceRoomDao {

    @Query("SELECT * FROM attendances")
    fun getAllAttendance(): LiveData<List<Attendance>>

    @Query("SELECT * FROM attendances WHERE date = :date")
    fun getByDateAttendance(date : String): LiveData<List<Attendance>>

    @Insert
    suspend fun addAttendance(attendance: Attendance)

    @Update
    suspend fun updateAttendance(attendance: Attendance)

    @Delete
    suspend fun deleteAttendance(attendance: Attendance)
}