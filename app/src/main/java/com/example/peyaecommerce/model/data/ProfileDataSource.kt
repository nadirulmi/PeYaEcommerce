package com.example.peyaecommerce.model.data

import com.example.peyaecommerce.model.Profile

interface ProfileDataSource {

    fun getProfileInfo(): Profile
}