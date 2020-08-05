package com.glukharev.reactiveworkflow.search

import android.annotation.SuppressLint
import com.glukharev.framework.View
import com.glukharev.reactiveworkflow.databinding.ViewSearchBinding

class SearchView(
    private val searchBinding: ViewSearchBinding
) : View<SearchUiState, SearchInteractorInputAction>() {

    override fun initUserInteraction() {
        searchBinding.apply {
            button.setOnClickListener { sendAction(SearchInteractorInputAction.StartLoadingAction) }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun render(state: SearchUiState) {
        searchBinding.apply {
            when (state) {
                SearchUiState.Loading -> {
                    text.text = "loading..."
                }
                SearchUiState.Error -> {
                    text.text = "Error"
                }
                is SearchUiState.ShowContent -> {
                    text.text = state.text
                }
            }
        }
    }
}