package com.example.sentrackapp.services

import com.example.sentrackapp.model.TrackResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SenTrackDBInterface {

    @GET(POPULAR_MOVIE_PATH)
    fun getPopularTracks(@Query("page") page: Int): Single<TrackResponse>

}