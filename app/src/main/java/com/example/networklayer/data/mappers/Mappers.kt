package com.example.networklayer.data.mappers

import com.example.apollo.GetCharactersQuery
import com.example.networklayer.domain.models.CharactersModel
import com.example.networklayer.domain.models.EpisodeModel
import com.example.networklayer.domain.models.InfoModel
import com.example.networklayer.domain.models.SingleCharacterModel

fun GetCharactersQuery.Info.mapToDomainModel() = InfoModel(pages ?: 0, count ?: 0, next ?: 0)

fun GetCharactersQuery.Episode.mapToDomainModel() = EpisodeModel(id ?: "", name ?: "")

fun GetCharactersQuery.Result.mapToDomainModel() = SingleCharacterModel(
    id ?: "",
    name ?: "",
    image ?: "",
    episode?.map { it!!.mapToDomainModel() } ?: emptyList()
)

fun GetCharactersQuery.Characters.mapToDomainModel() = CharactersModel(
    info?.mapToDomainModel() ?: InfoModel(),
    results?.map { it!!.mapToDomainModel() } ?: emptyList()
)