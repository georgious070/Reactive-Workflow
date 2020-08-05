package com.glukharev.reactiveworkflow.progress

import com.glukharev.framework.Action
import com.glukharev.framework.View
import com.glukharev.reactiveworkflow.databinding.ViewProgressBinding
import com.glukharev.utils.gone
import com.glukharev.utils.visible

class ProgressView(
    private val progressBinding: ViewProgressBinding
) : View<ProgressUiState, Action>() {

    override fun render(state: ProgressUiState) {
        progressBinding.apply {
            when (state) {
                ProgressUiState.Show -> {
                    progress.visible()
                }
                ProgressUiState.Hide -> {
                    progress.gone()
                }
            }
        }
    }

    override fun initUserInteraction() {

    }
}