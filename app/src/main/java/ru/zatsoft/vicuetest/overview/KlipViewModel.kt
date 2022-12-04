package ru.zatsoft.vicuetest.overview

import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.zatsoft.vicuetest.bindImage
import ru.zatsoft.vicuetest.network.Api
import ru.zatsoft.vicuetest.network.Klip

class KlipViewModel : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<String>()
    private val _photos = MutableLiveData<List<Klip>>()

    // The external immutable LiveData for the request status
    val status: LiveData<String> = _status
    val photos: LiveData<List<Klip>> = _photos
    var initPhoto: String?

    /**
     * Call getPhotos() on init so we can display status immediately.
     */
    init {
        getPhotos()
        initPhoto = photos.value?.get(0)?.poster_url
    }

    /**
     * Gets photos information from the API Retrofit service and updates the
     * [Klip] [List] [LiveData].
     */
    private fun getPhotos() {
        viewModelScope.launch {
            try{
                _photos.value = Api.retrofitService.getPhotos()
                _status.value = "Success: Klip properties retrieved"
            } catch(e:Exception){
                _status.value = "Failure: ${e.message}"
            }
        }
    }
}