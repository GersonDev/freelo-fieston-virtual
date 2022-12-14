package com.spydevs.fiestonvirtual.framework.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "first_name") var firstName: String,
    @ColumnInfo(name = "last_name") var lastName: String,
    @ColumnInfo(name = "total_score") var totalScore: Int,
    @ColumnInfo(name = "ranking") var ranking: Int,
    @ColumnInfo(name = "id_event") var idEvent: Int,
    @ColumnInfo(name = "avatar") var avatar: String,
    @ColumnInfo(name = "token") var token: String = ""

)