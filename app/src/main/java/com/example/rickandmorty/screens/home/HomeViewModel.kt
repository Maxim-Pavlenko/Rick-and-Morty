package com.example.rickandmorty.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.recyclerview_advanced.entities.Character
import com.example.rickandmorty.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    private val _charactersFlow = MutableStateFlow<PagingData<Character>>(PagingData.empty())
    val characterFlow: StateFlow<PagingData<Character>> = _charactersFlow.asStateFlow()
    fun getAllCharacters() {
        viewModelScope.launch {
            /*CollectLatest - Она используется для сбора данных из Flow,
            при этом обеспечивает актуальность (latest) данных и предотвращает накопление старых данных.*/
            repository.getAllCharacters().collectLatest { pagingData ->
                _charactersFlow.value = pagingData
            }
        }
    }
}