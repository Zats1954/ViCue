package ru.zatsoft.vicuetest.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.zatsoft.vicuetest.network.Api
import ru.zatsoft.vicuetest.network.Klip

class KlipViewModel : ViewModel() {

    private val _status = MutableLiveData<String>()
    private val _photos = MutableLiveData<List<Klip>>()

    val status: LiveData<String> = _status
    val photos: LiveData<List<Klip>> = _photos
    var initPhoto: String?

    init {
        getPhotos()
        initPhoto = photos.value?.get(0)?.poster_url
    }

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