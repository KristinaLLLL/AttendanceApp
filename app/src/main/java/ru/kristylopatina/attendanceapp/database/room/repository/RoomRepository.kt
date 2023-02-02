package ru.kristylopatina.attendanceapp.database.room.repository

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.LiveData
import ru.kristylopatina.attendanceapp.database.DatabaseRepository
import ru.kristylopatina.attendanceapp.database.room.dao.AttendanceRoomDao
import ru.kristylopatina.attendanceapp.model.Attendance

class RoomRepository(private val attendanceRoomDao: AttendanceRoomDao) : DatabaseRepository {

    override val readAll: LiveData<List<Attendance>>
        get() = attendanceRoomDao.getAllAttendance()

    override val readByDate: LiveData<List<Attendance>>
        get() = attendanceRoomDao.getByDateAttendance(date = "")

    override suspend fun create(attendance: Attendance, subject: String, onSuccess: () -> Unit) {
        attendanceRoomDao.addAttendance(attendance = attendance)
        onSuccess()
    }

    override suspend fun update(attendance: Attendance, onSuccess: () -> Unit) {
        attendanceRoomDao.updateAttendance(attendance = attendance)
        onSuccess()
    }

    override suspend fun delete(attendance: Attendance, onSuccess: () -> Unit) {
        attendanceRoomDao.deleteAttendance(attendance = attendance)
        onSuccess()
    }

}