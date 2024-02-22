package com.example.rickandmorty.screens.Details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recyclerview_advanced.entities.Character
import com.example.rickandmorty.data.repository.Repository
import com.example.rickandmorty.emtities.Episode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonDetailsViewModel @Inject constructor(
    private val repository: Repository
):ViewModel() {

    private val _characters: MutableStateFlow<Character?> = MutableStateFlow(null)
    val character: StateFlow<Character?> = _characters.asStateFlow()

    private val _episods: MutableStateFlow<List<Episode>> = MutableStateFlow(emptyList())
    val episods: StateFlow<List<Episode>> = _episods.asStateFlow()
    fun getCharacter(id: Int) {
        viewModelScope.launch {
            _characters.value = repository.getCharacter(id)
            getEpisod()
        }
    }

    fun getEpisod() {
        viewModelScope.launch {
            val episodeNumbers = extractNumbersFromUrls(_characters.value!!.episode)
            Log.d("episodeNumbers", "${episodeNumbers}")
            _episods.value = repository.getEpisod(episodeNumbers)
        }
    }

    fun extractNumbersFromUrls(urls: List<String>): List<Int> {
        val regex = Regex("""\d$""") // Регулярное выражение для извлечения цифр в конце строки
        return urls.mapNotNull {  url ->
            regex.find(url)?.value?.toInt()
        }
    }
}