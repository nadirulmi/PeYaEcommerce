package com.example.peyaecommerce.model.data

import com.example.peyaecommerce.model.Profile
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