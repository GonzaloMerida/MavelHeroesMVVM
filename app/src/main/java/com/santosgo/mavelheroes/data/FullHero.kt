package com.santosgo.mavelheroes.data

import com.google.gson.annotations.SerializedName

data class FullHero(
    @SerializedName("response") val response: String,
    @SerializedName("id") val id: String,
    @SerializedName("name") val heroName: String,
    @SerializedName("powerstats") val powerstats: Powerstats,
    @SerializedName("biography") val biography: Biography,
    @SerializedName("appearance") val appearance: Appearance,
    @SerializedName("work") val work: Work,
    @SerializedName("connections") val connections: Connections,
    @SerializedName("image") val image: Image
) {
    fun toHero() : Hero {
        return Hero(
            name = heroName,
            power = if(powerstats.power == "null") 0  else powerstats.power.toInt(),
            intelligence = if(powerstats.intelligence == "null") 0  else powerstats.intelligence.toInt(),
            photo = image.url,
            description = work.occupation,
            id = id
        )
    }
}

data class Powerstats(
    @SerializedName("intelligence") val intelligence: String,
    @SerializedName("strength") val strength: String,
    @SerializedName("speed") val speed: String,
    @SerializedName("durability") val durability: String,
    @SerializedName("power") val power: String,
    @SerializedName("combat") val combat: String
)

data class Biography(
    @SerializedName("full-name") val fullName: String,
    @SerializedName("alter-egos") val alterEgos: String,
    @SerializedName("aliases") val aliases: List<String>,
    @SerializedName("place-of-birth") val placeOfBirth: String,
    @SerializedName("first-appearance") val firstAppearance: String,
    @SerializedName("publisher") val publisher: String,
    @SerializedName("alignment") val alignment: String
)

data class Appearance(
    @SerializedName("gender") val gender: String,
    @SerializedName("race") val race: String,
    @SerializedName("height") val height: List<String>,
    @SerializedName("weight") val weight: List<String>,
    @SerializedName("eye-color") val eyeColor: String,
    @SerializedName("hair-color") val hairColor: String
)

data class Work(
    @SerializedName("occupation") val occupation: String,
    @SerializedName("base") val base: String
)

data class Connections(
    @SerializedName("group-affiliation") val groupAffiliation: String,
    @SerializedName("relatives") val relatives: String
)

data class Image(
    @SerializedName("url") val url: String
)
