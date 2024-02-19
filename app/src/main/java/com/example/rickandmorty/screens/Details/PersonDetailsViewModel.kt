package com.example.rickandmorty.screens.Details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recyclerview_advanced.entities.Character
import com.example.rickandmorty.data.repository.Repository
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
    fun getCharacter(id: Int) {
        viewModelScope.launch {
            _characters.value = repository.getCharacter(id)
        }
    }
}