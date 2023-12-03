package com.sviatkuzbyt.library.data.other

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "currentUser")
class CurrentUserManager(private val context: Context){
    private val key = longPreferencesKey("id")

    suspend fun changeUser(id: Long){
        context.dataStore.edit {
            it[key] = id
        }
        currentUser = id
    }

    suspend fun loadUser(){
        currentUser = context.dataStore.data.map {
            it[key] ?: NO_LOGIN
        }.first()
    }

    companion object{
        const val NO_LOGIN: Long = -1
        private var currentUser = NO_LOGIN
        fun getUser() = currentUser
    }
}