package com.example.sentrackapp.model

import com.google.gson.annotations.SerializedName

data class TrackResponse(
    val page: Int,

    @SerializedName("results")
    val trackLists: List<TrackItem>,

    @SerializedName("total_pages")
    val totalPages: Int,

    @SerializedName("total_results")
    val totalResults: Int
)