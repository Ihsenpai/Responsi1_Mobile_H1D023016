package com.example.responsi1mobileh1d023016

import com.google.gson.annotations.SerializedName

data class TeamResponse(
    @SerializedName("name") val name: String?,
    @SerializedName("crest") val crest: String?,
    @SerializedName("coach") val coach: CoachData?,
    @SerializedName("squad") val squad: List<PlayerData>?
)

data class CoachData(
    @SerializedName("name") val name: String?,
    @SerializedName("dateOfBirth") val dateOfBirth: String?,
    @SerializedName("nationality") val nationality: String?
)

data class PlayerData(
    @SerializedName("name") val name: String?,
    @SerializedName("position") val position: String?,
    @SerializedName("dateOfBirth") val dateOfBirth: String?,
    @SerializedName("nationality") val nationality: String?
)