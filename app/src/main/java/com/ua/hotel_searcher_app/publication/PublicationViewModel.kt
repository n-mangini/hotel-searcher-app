package com.ua.hotel_searcher_app.publication

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ua.hotel_searcher_app.apiManager.ApiServiceImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PublicationViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val apiServiceImpl: ApiServiceImpl
) : ViewModel() {

    private var _loadPublications = MutableStateFlow(false)
    val loadPublications = _loadPublications.asStateFlow()

    private val _publications = MutableStateFlow(listOf<PublicationModel>())
    val publications = _publications.asStateFlow()

    private val _showRetry = MutableStateFlow(false)
    val showRetry = _showRetry.asStateFlow()

    init {
        //loadPublication()
        fetchHardcodedPublications()
    }

    fun retryLoadingRanking() {
        Log.d("LoadPublication", "Retry loading publications")
        loadPublication()
    }

    private fun loadPublication() {
        _loadPublications.value = true
        apiServiceImpl.getPublications(
            context = context,
            onSuccess = {
                Log.d("LoadPublication", "Success: ${it.size} publications received")
                viewModelScope.launch {
                    try {
                        _publications.emit(it.sortedByDescending { it.price })
                        Log.d("LoadPublication", "Publications emitted successfully")
                    } catch (e: Exception) {
                        Log.e("LoadPublication", "Error emitting publications: ${e.message}")
                    }
                }
                _showRetry.value = false
            },
            onFail = {
                Log.e("LoadPublication", "Failed to load publications")
                _showRetry.value = true
            },
            loadingFinished = {
                _loadPublications.value = false
                Log.d("LoadPublication", "Loading finished")
            }
        )
    }


    private fun fetchHardcodedPublications() {
        viewModelScope.launch {
            val fetchedPublicationModels = listOf(
                PublicationModel(
                    "Cozy Apartment",
                    "A beautiful place to stay in the city center.",
                    "New York",
                    "$120/night"
                ),
                PublicationModel(
                    "Modern Loft",
                    "Spacious and bright loft with modern amenities.",
                    "Los Angeles",
                    "$200/night"
                ),
                PublicationModel(
                    "Beach House",
                    "Enjoy the sea breeze at this beachfront property.",
                    "Zarate, Argentina",
                    "$1/night"
                )
            )
            _publications.value = fetchedPublicationModels
        }
    }
}
