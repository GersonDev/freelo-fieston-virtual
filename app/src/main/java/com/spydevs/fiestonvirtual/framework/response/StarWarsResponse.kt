package com.spydevs.fiestonvirtual.framework.response

import com.google.gson.annotations.SerializedName

data class StarWarsResponse(
    @SerializedName("characters")
    val characters: List<String>? = null,
    @SerializedName("created")
    val created: String? = null,
    @SerializedName("director")
    val director: String? = null,
    @SerializedName("edited")
    val edited: String? = null,
    @SerializedName("episode_id")
    val episodeId: Int? = null,
    @SerializedName("opening_crawl")
    val openingCrawl: String? = null,
    @SerializedName("planets")
    val planets: List<String>? = null,
    @SerializedName("producer")
    val producer: String? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("species")
    val species: List<String>? = null,
    @SerializedName("starships")
    val starships: List<String>? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("vehicles")
    val vehicles: List<String>? = null
)