package com.mansao.biodataroomdatabase.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mansao.biodataroomdatabase.database.Profile
import com.mansao.biodataroomdatabase.repository.ProfileRepository

class MainViewModel (application: Application): ViewModel(){
    private val profileRepository: ProfileRepository = ProfileRepository(application)

    fun getAllData(): LiveData<List<Profile>> = profileRepository.getAllData()
}