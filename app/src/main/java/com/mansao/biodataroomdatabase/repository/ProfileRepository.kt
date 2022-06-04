package com.mansao.biodataroomdatabase.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.mansao.biodataroomdatabase.database.Profile
import com.mansao.biodataroomdatabase.database.ProfileDao
import com.mansao.biodataroomdatabase.database.ProfileRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ProfileRepository(application: Application) {
    private val profileDao: ProfileDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = ProfileRoomDatabase.getDatabase(application)
        profileDao = db.profileDao()
    }

    fun getAllData(): LiveData<List<Profile>> = profileDao.getAllData()

    fun insert(profile: Profile) {
        executorService.execute {
            profileDao.insert(profile)
        }
    }

    fun delete(profile: Profile) {
        executorService.execute {
            profileDao.delete(profile)
        }
    }

    fun update(profile: Profile) {
        executorService.execute {
            profileDao.update(profile)
        }
    }
}