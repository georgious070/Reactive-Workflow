package com.glukharev.reactiveworkflow.search

sealed class SearchState {
    object Loading : SearchState()
    data class Error(val message: String) : SearchState()
    data class Success(val items: List<String>) : SearchState()
}