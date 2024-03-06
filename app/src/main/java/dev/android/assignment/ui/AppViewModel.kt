package dev.android.assignment.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.android.assignment.intent.AppIntent
import dev.android.assignment.intent.AppState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AppViewModel : ViewModel() {
    val intent = Channel<AppIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow(AppState.initial())
    val state: StateFlow<AppState>
        get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            intent.consumeAsFlow().collect {
                when (it) {
                    is AppIntent.KeyPressed -> addKeyAndUpdateUiState(it.key)
                    AppIntent.DeletePressed -> deleteAndUpdateUiState()
                    AppIntent.ClearPressed -> clearAndUpdateUiState()
                    is AppIntent.OverflowError -> propagateError(it.message)
                }
            }
        }
    }

    private fun updateUiState(data: String) {
        _state.update { state -> state.copy(displayData = data, error = null) }
    }

    private fun addKeyAndUpdateUiState(key: String) {
        viewModelScope.launch {
            val updatedText = _state.value.displayData + key
            updateUiState(updatedText)
        }
    }

    private fun deleteAndUpdateUiState() {
        viewModelScope.launch {
            val currentText = _state.value.displayData
            if (currentText.isNotEmpty()) {
                val updatedText = _state.value.displayData.dropLast(1)
                updateUiState(updatedText)
            }else {
                propagateError("No text in display to delete!")
            }
        }
    }

    private fun clearAndUpdateUiState() {
        viewModelScope.launch {
            val currentText = _state.value.displayData
            if (currentText.isNotEmpty()) {
                updateUiState("")
            } else {
                propagateError("No text in display to clear!")
            }
        }
    }

    private fun propagateError(message: String?) {
        _state.update { state ->
            state.copy(error = message?.let { msg -> AppState.Error(msg) })
        }
    }
}