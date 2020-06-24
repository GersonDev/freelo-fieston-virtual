package com.spydevs.fiestonvirtual.framework.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey var id: Int = 0,
    @ColumnInfo(name = "first_name") var firstName: String? = null,
    @ColumnInfo(name = "last_name") var lastName: String? = null
)