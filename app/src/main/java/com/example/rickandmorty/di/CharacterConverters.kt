package com.example.rickandmorty.di

import androidx.room.TypeConverter
import com.example.recyclerview_advanced.entities.Location
import com.example.recyclerview_advanced.entities.Origin

class CharacterConverters {
    @TypeConverter
    fun fromOrigin(origin: Origin): String {
        return "${origin.name},${origin.url}"
    }

    @TypeConverter
    fun toOrigin(originString: String): Origin {
        val parts = originString.split(",")
        return Origin(parts[0], parts[1])
    }

    @TypeConverter
    fun fromLocation(location: Location): String {
        return "${location.name},${location.url}"
    }

    @TypeConverter
    fun toLocation(locationString: String): Location {
        val parts = locationString.split(",")
        return Location(parts[0], parts[1])
    }

    @TypeConverter
    fun fromEpisode(episode: List<String>): String {
        return episode.joinToString(",")
    }

    @TypeConverter
    fun toEpisode(episodeString: String): List<String> {
        return episodeString.split(",")
    }
}