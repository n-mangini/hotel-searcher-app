package com.ua.hotel_searcher_app.publication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ua.hotel_searcher_app.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PublicationViewModel @Inject constructor() : ViewModel() {
    private var _publications = MutableStateFlow(listOf<PublicationModel>())
    val publications = _publications.asStateFlow()

    init {
        fetchPublications()
    }

    private fun fetchPublications() {
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
