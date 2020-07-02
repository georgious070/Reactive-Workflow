package com.glukharev.reactiveworkflow.search

sealed class SearchAction {
    object StartLoadingAction : SearchAction()
    data class Test(val string: String) : SearchAction()
}