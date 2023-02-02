package ru.kristylopatina.attendanceapp.utils

import ru.kristylopatina.attendanceapp.database.DatabaseRepository

object Constants{
    object Key{
        const val TYPE_DATABASE = "type_database"
        const val TYPE_ROOM = "type_room"
        const val TYPE_FIREBASE = "type_firebase"

        const val  TYPE_CHEM = "chem"
        const val  TYPE_COMP = "compSci"
        const val  TYPE_ENGL = "engl"
        const val  TYPE_HIST = "hist"
        const val  TYPE_MATH = "math"
        const val  TYPE_PHIS = "phis"
        const val  TYPE_LITER = "liter"

        const val  ID = "Id"

        lateinit var REPOSITORY: DatabaseRepository
    }
    const val TYPE_ROOM = "type_room"
}
