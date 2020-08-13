package com.glukharev.framework

import kotlinx.coroutines.CoroutineScope

interface Action

interface ScreenActionPool {

    fun bindToScope(context: CoroutineScope?)

    fun pushSharedAction(action: Action)

    fun subscribeSharedAction(observer: (Action) -> Unit)
}