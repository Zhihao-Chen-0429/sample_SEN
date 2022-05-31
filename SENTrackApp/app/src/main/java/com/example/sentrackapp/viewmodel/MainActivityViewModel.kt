package com.example.sentrackapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sentrackapp.model.TrackResponse
import com.example.sentrackapp.services.FIRST_PAGE
import com.example.sentrackapp.services.SenTrackDBClient
import com.example.sentrackapp.services.SenTrackDBInterface
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainActivityViewModel(): ViewModel() {

    private val TAG = "MainActivityViewModel"

    private val trackApiService = SenTrackDBClient.buildService(SenTrackDBInterface::class.java)

    private val compositeDisposable = CompositeDisposable()

    val trackResponse = MutableLiveData<TrackResponse>()

    fun getTracksFromAPI(){
        compositeDisposable.add(
            trackApiService.getPopularTracks(FIRST_PAGE)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    trackResponse.postValue(it)
                },{
                    it.message?.let { error -> Log.e(TAG, error) }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}