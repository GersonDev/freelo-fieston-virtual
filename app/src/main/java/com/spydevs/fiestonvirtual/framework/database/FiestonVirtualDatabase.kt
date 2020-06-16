package com.spydevs.fiestonvirtual.framework.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.spydevs.fiestonvirtual.framework.database.FiestonVirtualDatabase.Companion.DATABASE_VERSION
import com.spydevs.fiestonvirtual.framework.database.dao.UsersDao
import com.spydevs.fiestonvirtual.framework.database.entities.UserEntity

@Database(entities = [UserEntity::class], version = DATABASE_VERSION, exportSchema = false)
abstract class FiestonVirtualDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersDao

    companion object {

        const val DATABASE_VERSION: Int = 1
        const val DATABASE_NAME = "FiestonVirtual"

        fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, FiestonVirtualDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}