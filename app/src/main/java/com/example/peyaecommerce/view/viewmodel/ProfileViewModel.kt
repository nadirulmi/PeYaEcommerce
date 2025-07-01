package com.example.peyaecommerce.view.viewmodel

import android.app.Application
import android.net.Uri
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cloudinary.Cloudinary
import com.example.peyaecommerce.model.data.ProfileDataSource
import com.example.peyaecommerce.model.Profile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class ProfileViewModel @Inject constructor(
    val myApp: Application,
    val cloudinary: Cloudinary,
    private val profileDataSource: ProfileDataSource
): AndroidViewModel(myApp) {

    private val _profile = MutableStateFlow(Profile())
    val profile: MutableStateFlow<Profile> = _profile

    private val _isImageUploading = MutableStateFlow(false)
    val isImageUploading: StateFlow<Boolean> = _isImageUploading


    init{
        loadUserProfile()
    }

    private fun loadUserProfile() {
        viewModelScope.launch {
            delay(150)
            _profile.value = profileDataSource.getProfileInfo()
        }
    }

    fun updateProfile(newProfile: Profile, imageUri: Uri?) {
        _profile.value = newProfile
        if (imageUri != null && imageUri != Uri.EMPTY) {
            uploadImageToCloudinary(imageUri)
        }
    }

    private fun uploadImageToCloudinary(uri: Uri){
        viewModelScope.launch(Dispatchers.IO){
            _isImageUploading.value = true
            try {
               val inputStream = getApplication<Application>().contentResolver.openInputStream(uri)
                val uploadResult = cloudinary.uploader().upload(
                    inputStream,
                    mapOf("upload_preset" to "dqczp7b2h")
                )
                val imageUrl = uploadResult["secure_url"] as String
                val updateProfile = _profile.value.copy(image = imageUrl)
                _profile.value = updateProfile
            } catch (e: Exception) {
                Log.e("ProfileViewModel", "Error uploading image: ${e.message}")
            } finally {
                _isImageUploading.value = false
            }
        }
    }
}