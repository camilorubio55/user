package com.justo.user.data.db.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "user")
data class UserDB(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    var email: String,
    val phone: String,
    val image: String
) : Parcelable