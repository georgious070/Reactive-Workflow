package com.glukharev.reactiveworkflow.progress

import com.glukharev.framework.Action

sealed class ProgressAction : Action {

    object Default : ProgressAction()
}

sealed class ProgressUiState {

    object Show : ProgressUiState()
    object Hide : ProgressUiState()
}