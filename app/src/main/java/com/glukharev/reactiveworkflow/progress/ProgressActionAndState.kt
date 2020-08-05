package com.glukharev.reactiveworkflow.progress

sealed class ProgressUiState {

    object Show : ProgressUiState()
    object Hide : ProgressUiState()
}