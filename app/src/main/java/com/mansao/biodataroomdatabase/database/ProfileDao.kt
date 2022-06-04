package com.mansao.biodataroomdatabase.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProfileDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(profile: Profile)

    @Delete
    fun delete(profile: Profile)

    @Update
    fun update(profile: Profile)

    @Query("SELECT * from profile ORDER by id ASC")
    fun getAllData(): LiveData<List<Profile>>
}