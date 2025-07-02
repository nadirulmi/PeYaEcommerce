package com.example.peyaecommerce.model.repository

import com.example.peyaecommerce.model.models.Profile

interface ProfileDataSource {

    fun getProfileInfo(): Profile
}