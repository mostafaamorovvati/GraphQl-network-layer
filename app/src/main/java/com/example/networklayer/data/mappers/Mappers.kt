package com.example.networklayer.data.mappers

import com.example.apollo.GetCharactersQuery
import com.example.networklayer.data.models.CharactersModel
import com.example.networklayer.data.models.EpisodeModel
import com.example.networklayer.data.models.InfoModel
import com.example.networklayer.data.models.SingleCharacterModel

fun GetCharactersQuery.Info.mapToLocalModel() = InfoModel(pages ?: 0, count ?: 0, next ?: 0)

fun GetCharactersQuery.Episode.mapToLocalModel() = EpisodeModel(id ?: "", name ?: "")

fun GetCharactersQuery.Result.mapToLocalModel() = SingleCharacterModel(
    id ?: "",
    name ?: "",
    image ?: "",
    episode?.map { it!!.mapToLocalModel() } ?: emptyList()
)

fun GetCharactersQuery.Characters.mapToLocalModel() = CharactersModel(
    info?.mapToLocalModel() ?: InfoModel(),
    results?.map { it!!.mapToLocalModel() } ?: emptyList()
)
