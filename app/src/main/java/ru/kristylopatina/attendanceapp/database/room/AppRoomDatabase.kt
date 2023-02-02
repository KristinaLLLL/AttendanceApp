package ru.kristylopatina.attendanceapp.database.room

import android.content.Context
import androidx.compose.runtime.CompositionContext
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.kristylopatina.attendanceapp.database.room.dao.AttendanceRoomDao
import ru.kristylopatina.attendanceapp.model.Attendance

@Database(entities = [Attendance::class], version = 1)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun getRoomDao(): AttendanceRoomDao

    companion object{

        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        fun getInstance(context: Context) : AppRoomDatabase{
            return if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context, AppRoomDatabase::class.java,
                    "attendance_database").build()
                INSTANCE as AppRoomDatabase
            }else INSTANCE as AppRoomDatabase
        }
    }
}