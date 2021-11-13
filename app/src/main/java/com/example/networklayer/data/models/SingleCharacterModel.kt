package com.example.networklayer.data.models

import android.os.Parcelable
import androidx.room.Entity
import com.example.networklayer.data.models.EpisodeModel
import kotlinx.parcelize.Parcelize

@Entity(tableName = "character", primaryKeys = ["id"])
@Parcelize
data class SingleCharacterModel(
    val id: String,
    val name: String,
    val image: String,
    val episode: List<EpisodeModel>
) : Parcelable