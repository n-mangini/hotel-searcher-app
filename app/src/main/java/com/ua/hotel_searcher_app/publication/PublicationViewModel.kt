package com.ua.hotel_searcher_app.publication

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ua.hotel_searcher_app.R
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

    /*    private var _loadPublication = MutableStateFlow(false)
        val loadPublication = _loadPublication.asStateFlow()

        private val _publication = MutableStateFlow(listOf<Publication>())
        val publication = _publication.asStateFlow()

        private val _showRetry = MutableStateFlow(false)
        val showRetry = _showRetry.asStateFlow()*/


    private var _publications = MutableStateFlow(listOf<Publication>())
    val publications = _publications.asStateFlow()

    init {
        fetchPublications()
        //loadPublication()
    }

    private fun fetchPublications() {
        viewModelScope.launch {
            val fetchedPublications = listOf(
                Publication(
                    "Cozy Apartment",
                    "A beautiful place to stay in the city center.",
                    "New York",
                    "$120/night",
                    imageResId = R.drawable.image_1
                ),
                Publication(
                    "Modern Loft",
                    "Spacious and bright loft with modern amenities.",
                    "Los Angeles",
                    "$200/night",
                    imageResId = R.drawable.image_1
                ),
                Publication(
                    "Beach House",
                    "Enjoy the sea breeze at this beachfront property.",
                    "Zarate, Argentina",
                    "$1/night",
                    imageResId = R.drawable.image_1
                )
            )
            _publications.value = fetchedPublications
        }
    }

    /*    private fun loadPublication() {
            _loadPublication.value = true
            apiServiceImpl.getPublications(
                context = context,
                onSuccess = {
                    viewModelScope.launch {
                        _publication.emit(it.sortedByDescending { it.title })
                    }
                    _showRetry.value = false
                },
                onFail = {
                    _showRetry.value = true
                },
                loadingFinished = {
                    _loadPublication.value = false
                }
            )
        }*/
}
