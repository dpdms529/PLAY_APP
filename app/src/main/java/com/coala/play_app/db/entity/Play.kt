package com.coala.play_app.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Play (
    @PrimaryKey(autoGenerate = true) val play_id : Int,
    val play_name:String?,
    val place:String?,
    val cost:String?,
    val num:String?,
    val act:String?
)



