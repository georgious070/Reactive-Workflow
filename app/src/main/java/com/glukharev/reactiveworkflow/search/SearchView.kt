package com.glukharev.reactiveworkflow.search

import com.glukharev.framework.View
import com.glukharev.utils.gone
import com.glukharev.utils.visible
import kotlinx.android.synthetic.main.activity_main.view.*

class SearchView : View<SearchUiState, SearchUiAction>() {

    override fun initUserInteraction() {
        containerView?.apply {
            button.setOnClickListener { sendAction(SearchUiAction.StartLoadingAction) }
        }
    }

    override fun render(state: SearchUiState) {
        containerView?.apply {
            when (state) {
                SearchUiState.Loading -> {
                    progress.visible()
                }
                SearchUiState.Error -> {
                    progress.gone()
                    text.text = "Error"
                }
                is SearchUiState.ShowContent -> {
                    progress.gone()
                    text.text = state.text
                }
            }
        }
    }
}