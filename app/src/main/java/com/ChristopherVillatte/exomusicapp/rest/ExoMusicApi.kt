package com.ChristopherVillatte.exomusicapp.rest

import com.ChristopherVillatte.exomusicapp.model.items.TrackListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

//        Tab 1:
//        https://itunes.apple.com/search?term=rock&amp;media=music&amp;entity=song&amp;limit=50
//
//        Tab 2:
//        https://itunes.apple.com/search?term=classick&amp;media=music&amp;entity=song&amp;limit=50
//
//        Tab 3:
//        https://itunes.apple.com/search?term=pop&amp;media=music&amp;entity=song&amp;limit=50
//   Classic Tab should load:
//        https://itunes.apple.com/search?term=classick&amp;media=music&amp;entity=song&amp;limit=50
//
//        Pop Tab should load:
//        https://itunes.apple.com/search?term=pop&amp;media=music&amp;entity=song&amp;limit=50
//
//        Rock Tab should load:
//        https://itunes.apple.com/search?term=rock&amp;media=music&amp;entity=song&amp;limit=50

interface ExoMusicApi {

    @GET(SEARCH)
    suspend fun getTrackListByGenre(
       @Query("term") term: String,
       @Query("amp;media", encoded = true) media: String = "music",
       @Query("amp;entity", encoded = true) entity: String = "song",
       @Query("amp;limit", encoded = true) limit: String = "50",
    ): Response<TrackListResponse>


    companion object {

        const val BASE_URL = "https://itunes.apple.com/"
        private const val SEARCH = "search"

    }
}