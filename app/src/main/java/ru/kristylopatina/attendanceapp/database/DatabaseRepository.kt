package ru.kristylopatina.attendanceapp.database

import android.content.Context
import androidx.lifecycle.LiveData
import ru.kristylopatina.attendanceapp.model.Attendance

interface DatabaseRepository {

    val readAll: LiveData<List<Attendance>>

    val readByDate: LiveData<List<Attendance>>

    suspend fun create(attendance: Attendance, subject: String, onSuccess: ()->Unit)

    suspend fun update(attendance: Attendance, onSuccess: ()->Unit)

    suspend fun delete(attendance: Attendance, onSuccess: ()->Unit)
}