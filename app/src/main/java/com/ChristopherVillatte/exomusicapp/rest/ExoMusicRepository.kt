package com.ChristopherVillatte.exomusicapp.rest

import com.ChristopherVillatte.exomusicapp.model.items.TrackListResponse
import com.ChristopherVillatte.exomusicapp.utils.FailureResponse
import com.ChristopherVillatte.exomusicapp.utils.NullTrackListResponse
import com.ChristopherVillatte.exomusicapp.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


interface ExoMusicRepository {
    fun getTracksByGenre(genre: String): Flow<UIState<TrackListResponse>>

}

class ExoMusicRepositoryImpl @Inject constructor(
    private val exoMusicApi: ExoMusicApi
): ExoMusicRepository {
    override fun getTracksByGenre(term: String): Flow<UIState<TrackListResponse>> = flow {
        emit(UIState.LOADING)
        try{
            val response = exoMusicApi.getTrackListByGenre(term)
            if(response.isSuccessful){
                response.body()?.let {
                    val temp = UIState.SUCCESS(it)
                    emit(temp)
                } ?: throw NullTrackListResponse()
            } else {
                throw FailureResponse(response.errorBody()?.string())
            }
        } catch (e: Exception) {
            emit(UIState.ERROR(e))
        }

    }
}

