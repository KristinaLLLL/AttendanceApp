package ru.kristylopatina.attendanceapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.kristylopatina.attendanceapp.database.DatabaseRepository
import ru.kristylopatina.attendanceapp.database.room.AppRoomDatabase
import ru.kristylopatina.attendanceapp.database.room.repository.RoomRepository
import ru.kristylopatina.attendanceapp.model.Attendance
import ru.kristylopatina.attendanceapp.utils.Constants

class MainViewModel(application: Application) : AndroidViewModel(application) {

    var context = application

    fun initDatabase(type:String, subject: String, onSuccess: ()->Unit){
        Log.d("checkData", "MainViewModel initDatabase with type: $type")
        when(type){
            Constants.Key.TYPE_ROOM->{
                val dao = AppRoomDatabase.getInstance(context = context).getRoomDao()
                Constants.Key.REPOSITORY = RoomRepository(dao)
                onSuccess()
            }
        }
    }

    fun addAttendance(subject: String, attendance: Attendance, onSuccess: () -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            Constants.Key.REPOSITORY.create(attendance=attendance, subject = subject){
                viewModelScope.launch(Dispatchers.Main){
                    onSuccess()
                }
            }
        }
    }

    fun readAllAttendances() = Constants.Key.REPOSITORY.readAll
    //fun readByDateAttendances() = Constants.Key.REPOSITORY.readByDate.value

    fun updateAttendance(attendance: Attendance, onSuccess: () -> Unit){
        viewModelScope.launch(Dispatchers.IO) {
            Constants.Key.REPOSITORY.update(attendance = attendance){
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }
    fun deleteAttendance(attendance: Attendance, onSuccess: () -> Unit){
        viewModelScope.launch(Dispatchers.IO) {
            Constants.Key.REPOSITORY.delete(attendance = attendance){
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(application = application) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel class")
    }
}