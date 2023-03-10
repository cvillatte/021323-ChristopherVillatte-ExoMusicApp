package com.ChristopherVillatte.exomusicapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ChristopherVillatte.exomusicapp.model.items.TrackListResponse
import com.ChristopherVillatte.exomusicapp.rest.ExoMusicRepository
import com.ChristopherVillatte.exomusicapp.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExoMusicViewModel @Inject constructor(
    private val exoMusicRepository: ExoMusicRepository,

): ViewModel() {

    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    private val genres = arrayListOf("classick", "rock", "pop")
    var trackUri : String = ""
    private var isInitialized = false

    init {
        if(!isInitialized) {
            getTrackList()
            isInitialized = true
        }
    }

    private val _classicTrackList: MutableLiveData<UIState<TrackListResponse>> = MutableLiveData(UIState.LOADING)
    val classicTrackList: LiveData<UIState<TrackListResponse>> get() = _classicTrackList

    private val _rockTrackList: MutableLiveData<UIState<TrackListResponse>> = MutableLiveData(UIState.LOADING)
    val rockTrackList: LiveData<UIState<TrackListResponse>> get() = _rockTrackList

    private val _popTrackList: MutableLiveData<UIState<TrackListResponse>> = MutableLiveData(UIState.LOADING)
    val popTrackList: LiveData<UIState<TrackListResponse>> get() = _popTrackList

    private fun getTrackList() {
        genres.forEach { genre ->
            viewModelScope.launch(ioDispatcher) {
                exoMusicRepository.getTracksByGenre(genre).collect() {
                    when(genre) {
                        "classick" -> _classicTrackList.postValue(it)
                        "rock" -> _rockTrackList.postValue(it)
                        "pop" -> _popTrackList.postValue(it)
                    }
                }
            }

        }
    }

}