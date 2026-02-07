package com.example.mynotes.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults.contentPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mynotes.R
import com.example.mynotes.data.Note

@Composable
fun MyNotesApp(
    vm: MyNotesViewModel = viewModel(factory = MyNotesViewModel.Factory)
) {
    val uiState = vm.uiState.collectAsState()
    var isDialogActive by rememberSaveable { mutableStateOf(false) }
    Scaffold(
        topBar = {
            MyNotesTopAppBar()
        },
        floatingActionButton = {
            FAB(
                onFabClick = {
                    isDialogActive=true
                }
            )
        }
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize()
        ) { }
        if(isDialogActive){
            NoteDialog(
                onDialogDismiss = {
                    isDialogActive = false
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
            Text(stringResource(R.string.app_name))
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDialog(
    onDialogDismiss: () -> Unit
) {
    var name by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = onDialogDismiss
    ){
        Column{
            OutlinedTextField(
                value = name,
                onValueChange = {name = it},
                placeholder = {
                    Text(stringResource(R.string.name))
                },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = description,
                onValueChange = {description = it},
                placeholder = {
                    Text(stringResource(R.string.text))
                },
                minLines = 3,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}