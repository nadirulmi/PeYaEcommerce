package com.example.peyaecommerce.model.data

import com.example.peyaecommerce.model.models.Profile
import com.example.peyaecommerce.model.repository.ProfileDataSource
import javax.inject.Inject

class ProfileData @Inject constructor() : ProfileDataSource {

    val profile = Profile(
        name = "Usuario",
        lastName = "prueba",
        email = "test@gmail.com",
        password = "pass1234",
        nationality = "Argentina"
    )

    override fun getProfileInfo(): Profile = profile
}