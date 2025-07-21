package com.example.peyaecommerce.model.data.remote

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "user_prefs")

class UserPreferences(private val context: Context) {

    private val USER_ID = stringPreferencesKey("user_id")
    private val USER_FULL_NAME = stringPreferencesKey("user_full_name")
    private val USER_EMAIL = stringPreferencesKey("user_email")
    private val USER_NATIONALITY = stringPreferencesKey("user_nationality")

    suspend fun saveUser(user: UserDto) {
        context.dataStore.edit { prefs ->
            prefs[USER_ID] = user._id ?: ""
            prefs[USER_FULL_NAME] = user.fullName
            prefs[USER_EMAIL] = user.email
            prefs[USER_NATIONALITY] = user.nationality
        }
    }

    val userFlow: Flow<UserDto> = context.dataStore.data.map { prefs ->
        UserDto(
            _id = prefs[USER_ID],
            fullName = prefs[USER_FULL_NAME] ?: "",
            email = prefs[USER_EMAIL] ?: "",
            nationality = prefs[USER_NATIONALITY] ?: ""
        )
    }
}
