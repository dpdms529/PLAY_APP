package com.coala.play_app.db.dao

import androidx.room.*
import com.coala.play_app.db.entity.Play

@Dao
interface PlayDao {
    @Query("SELECT * FROM play")
    fun getAll():List<Play>

    @Query("SELECT * FROM play " +
            "WHERE place IN (:indoor, :outdoor) " +
            "AND   cost IN (:free, :pay) " +
            "AND   num  IN (:alone, :friend) " +
            "AND   act  IN (:active, :inactive) " +
            "ORDER BY random() " +
            "LIMIT 1")
    fun getResult(indoor:String?,outdoor:String?,free:String?,pay:String?,alone:String?,friend:String?,active:String?,inactive:String?):Play

    @Insert
    fun insert(play: Play)

    @Delete
    fun delete(play: Play)

    @Update
    fun update(play: Play)

}