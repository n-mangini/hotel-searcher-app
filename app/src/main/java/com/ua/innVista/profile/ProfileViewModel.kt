package com.ua.innVista.profile

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ua.innVista.data.PreferencesKeys
import com.ua.innVista.data.getFromDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    @ApplicationContext val context: Context,
) : ViewModel() {

    private var _userName = MutableStateFlow("")
    val userName = _userName.asStateFlow()

    init {
        getNameFromDataStore()
    }

    private fun getNameFromDataStore() {
        viewModelScope.launch {
            getFromDataStore(context, PreferencesKeys.USER_NAME_KEY).collect {
                _userName.value = it ?: ""
            }
        }
    }

    fun saveToDataStore(username: String) {
        viewModelScope.launch {
            com.ua.innVista.data.saveToDataStore(
                context,
                username,
                PreferencesKeys.USER_NAME_KEY
            )
            _userName.value = username
        }
    }
}
