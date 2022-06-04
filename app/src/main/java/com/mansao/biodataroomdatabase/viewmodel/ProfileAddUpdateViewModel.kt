package com.mansao.biodataroomdatabase.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.mansao.biodataroomdatabase.database.Profile
import com.mansao.biodataroomdatabase.repository.ProfileRepository

class ProfileAddUpdateViewModel(application: Application) : ViewModel() {
    private val profileRepository: ProfileRepository = ProfileRepository(application)

    fun insert(profile: Profile) {
        profileRepository.insert(profile)
    }

    fun delete(profile: Profile) {
        profileRepository.delete(profile)
    }

    fun update(profile: Profile) {
        profileRepository.update(profile)
    }

}