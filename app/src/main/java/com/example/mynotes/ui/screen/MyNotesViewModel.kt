package com.example.mynotes.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.mynotes.MyNotesApplication
import com.example.mynotes.data.MyNotesRepository
import com.example.mynotes.data.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MyNotesViewModel(
    private val notesRepository: MyNotesRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(MyNotesUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getNotes()
    }

    fun getNotes() {
        viewModelScope.launch {
            notesRepository.getNotes().collect { notes ->
                _uiState.update { curState ->
                    curState.copy(notes = notes)
                }
            }
        }
    }

    fun createNote(name: String, desc: String) {
        viewModelScope.launch {
            val note = Note(name = name, description = desc)
            notesRepository.addNote(note)
        }
    }

    fun updateNote(note: Note) {
        val newNote = note.also { it.lastInteraction = System.currentTimeMillis() }
        viewModelScope.launch {
            notesRepository.updateNote(newNote)
        }
    }

    fun selectNote(note: Note?) {
        _uiState.update { curState ->
            curState.copy(selectedNote = note)
        }
    }

    fun updateNoteCreationDialogFlag(isActive: Boolean) {
        _uiState.update { curState ->
            curState.copy(isNoteCreationDialogActive = isActive)
        }
    }

    fun updateNoteDialogFlag(isActive: Boolean) {
        _uiState.update { curState ->
            curState.copy(isNoteDialogActive = isActive)
        }
    }

    fun checkNote(note: Note): Boolean = note.id in _uiState.value.deletionList

    fun deletionModClick(note: Note){
        _uiState.update {
            val newList = it.deletionList.toMutableList()
            if(checkNote(note)) newList.remove(note.id) else newList.add(note.id)
            it.copy(deletionList = newList)
        }
    }

    fun turnOnDeletionMod(note: Note){
        _uiState.update { curState ->
            val newList = curState.deletionList.toMutableList()
            newList.add(note.id)
            curState.copy(deletionMod = true, deletionList = newList)
        }
    }

    fun turnOffDeletionMod(){
        _uiState.update { curState ->
            curState.copy(
                deletionMod = false,
                deletionList = emptyList()
            )
        }
    }

    fun deleteNotes(){
        viewModelScope.launch {
            notesRepository.deleteNotes(_uiState.value.deletionList)
        }
        turnOffDeletionMod()
    }
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val repository =
                    (this[APPLICATION_KEY] as MyNotesApplication).container.notesRepository
                MyNotesViewModel(repository)
            }
        }
    }

}

data class MyNotesUiState(
    val notes: List<Note> = emptyList(),
    val isNoteCreationDialogActive: Boolean = false,
    val isNoteDialogActive: Boolean = false,
    val selectedNote: Note? = null,
    val deletionMod: Boolean = false,
    val deletionList: List<Int> = emptyList()
)