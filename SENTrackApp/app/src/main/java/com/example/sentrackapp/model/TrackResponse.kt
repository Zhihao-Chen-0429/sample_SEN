package com.example.sentrackapp.model

data class TrackResponse(
    val page: Int,
    val results: List<TrackItem>,
    val total_pages: Int,
    val total_results: Int
)