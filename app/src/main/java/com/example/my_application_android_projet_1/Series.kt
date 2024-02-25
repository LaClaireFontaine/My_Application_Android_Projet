package com.example.my_application_android_projet_1

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Series(
    val name: String,
    val nameserie : String,
    val nbepi: String,
    val annee: String,
    val histoire: String,
    val personnages: List<String>,
    val episodes: List<String>,
) : Parcelable

fun generateFakeSerie() = Series(
    name = "Agents of S.H.I.E.L.D.",
    nameserie = "Marvel",
    nbepi = "136 épisodes",
    annee = "2013",
    histoire = "Bla Bla Bla",
    personnages = listOf("Jojo Armani", "Franheska Trello", "Blue Menfeld"),
    episodes = listOf("Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5"),
)