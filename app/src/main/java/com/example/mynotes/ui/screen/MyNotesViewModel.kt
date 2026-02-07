package com.example.mynotes.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.mynotes.MyNotesApplication
import com.example.mynotes.data.MyNotesRepository
import com.example.mynotes.data.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MyNotesViewModel(
    private val notesRepository: MyNotesRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(MyNotesUiState())
    val uiState = _uiState.asStateFlow()



companion object {
    val Factory: ViewModelProvider.Factory = viewModelFactory {
        initializer {
            val repository = (this[APPLICATION_KEY] as MyNotesApplication).container.notesRepository
            MyNotesViewModel(repository)
        }
    }
}

}

data class MyNotesUiState(
    val notes: List<Note> = emptyList()
)