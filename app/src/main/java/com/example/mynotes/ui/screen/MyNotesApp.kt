package com.example.mynotes.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mynotes.R
import com.example.mynotes.data.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyNotesApp(
    vm: MyNotesViewModel = viewModel(factory = MyNotesViewModel.Factory)
) {
    val uiState by vm.uiState.collectAsState()
    Scaffold(
        topBar = {
            if (uiState.deletionMod) {
                DeletionTopAppBar(
                    itemsCount = uiState.deletionList.size,
                    onCancel = vm::turnOffDeletionMod,
                    onDelete = vm::deleteNotes
                )
            } else {
                MyNotesTopAppBar()
            }
        },
        floatingActionButton = {
            FAB(
                onFabClick = {
                    vm.updateNoteCreationDialogFlag(true)
                }
            )
        }
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(contentPadding)
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(4.dp))
            }
            items(uiState.notes, key = {it.id}) { note ->
                NoteCard(
                    note = note,
                    onNoteClick = {
                        vm.selectNote(note)
                        vm.updateNoteDialogFlag(true)
                    },
                    isInDeletionList = vm.checkNote(note),
                    onClickWhileDeletionMod = { vm.deletionModClick(note) },
                    isDeletionMod = uiState.deletionMod,
                    turnOnDeletionMode = { vm.turnOnDeletionMod(note) },
                    timeConvert = vm::convertTime
                )
            }
            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
        if (uiState.isNoteCreationDialogActive) {
            NoteCreateDialog(
                onNoteCreate = { name, desc ->
                    if (name.isNotBlank() || desc.isNotBlank()) {
                        vm.createNote(name, desc)
                    }
                    vm.updateNoteCreationDialogFlag(false)
                }
            )
        }
        if (uiState.isNoteDialogActive) {
            NoteDialog(
                note = uiState.selectedNote!!,
                onNoteUpdate = { newNote ->
                    if (newNote.name.isNotBlank() || newNote.description.isNotBlank()) {
                        vm.updateNote(newNote)
                    }
                    vm.updateNoteDialogFlag(false)
                    vm.selectNote(null)
                },
                onCancel = {
                    vm.updateNoteDialogFlag(false)
                    vm.selectNote(null)
                }
            )
        }


    }
}

@Composable
fun FAB(
    onFabClick: () -> Unit
) {
    FloatingActionButton(
        onClick = onFabClick
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = stringResource(R.string.add_note)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyNotesTopAppBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name)
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeletionTopAppBar(
    itemsCount: Int,
    onDelete: () -> Unit,
    onCancel: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(stringResource(R.string.chosen, itemsCount))
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        actions = {
            IconButton(
                onClick = onDelete
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = stringResource(R.string.delete)
                )
            }
            IconButton(
                onClick = onCancel
            ) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = stringResource(R.string.clear)
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteCreateDialog(
    onNoteCreate: (String, String) -> Unit
) {
    var name by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    BasicAlertDialog(
        onDismissRequest = { onNoteCreate(name, description) }
    ) {
        Card(
            shape = MaterialTheme.shapes.large,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainer
            )
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    placeholder = {
                        Text(stringResource(R.string.name))
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    placeholder = {
                        Text(stringResource(R.string.text))
                    },
                    minLines = 3,
                    modifier = Modifier.fillMaxWidth()
                )
                TextButton(
                    enabled = name.isNotBlank() || description.isNotBlank(),
                    onClick = {
                        onNoteCreate(name, description)
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(stringResource(R.string.create))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDialog(
    note: Note,
    onNoteUpdate: (Note) -> Unit,
    onCancel: () -> Unit
) {
    var newNote by rememberSaveable { mutableStateOf(note) }
    BasicAlertDialog(
        onDismissRequest = { onNoteUpdate(newNote) },
    ) {
        Card(
            shape = MaterialTheme.shapes.large,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainer
            )
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                OutlinedTextField(
                    value = newNote.name,
                    onValueChange = { newNote = newNote.copy(name = it) },
                    placeholder = {
                        Text(stringResource(R.string.name))
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = newNote.description,
                    onValueChange = { newNote = newNote.copy(description = it) },
                    placeholder = {
                        Text(stringResource(R.string.text))
                    },
                    minLines = 3,
                    modifier = Modifier.fillMaxWidth()
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.align(Alignment.End)
                ) {
                    TextButton(
                        onClick = onCancel
                    ) {
                        Text(stringResource(R.string.cancel))
                    }
                    TextButton(
                        enabled = newNote.name.isNotBlank() || newNote.description.isNotBlank(),
                        onClick = {
                            onNoteUpdate(newNote)
                        },
                    ) {
                        Text(stringResource(R.string.save))
                    }
                }
            }
        }
    }
}
@OptIn(
    ExperimentalFoundationApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun NoteCard(
    note: Note,
    onNoteClick: () -> Unit,
    onClickWhileDeletionMod: () -> Unit,
    isDeletionMod: Boolean,
    turnOnDeletionMode: () -> Unit,
    isInDeletionList: Boolean,
    timeConvert: (Long) -> String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = if (isDeletionMod) onClickWhileDeletionMod else onNoteClick,
                onLongClick = if (isDeletionMod) onClickWhileDeletionMod else turnOnDeletionMode
            ),
        colors = CardDefaults.cardColors(
            containerColor = if (isInDeletionList) MaterialTheme.colorScheme.outlineVariant
            else MaterialTheme.colorScheme.surfaceContainer
        ),
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),

        ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f)
            ) {
                if (note.name.isNotBlank()) {
                    Text(
                        text = note.name,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
                if (note.description.isNotBlank()) {
                    Text(
                        text = note.description,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Text(
                    text = timeConvert(note.lastInteraction),
                    style = MaterialTheme.typography.labelSmall
                )
            }
            if (isDeletionMod) {
                Checkbox(
                    checked = isInDeletionList,
                    onCheckedChange = { onClickWhileDeletionMod() }
                )
            }
        }

    }
}