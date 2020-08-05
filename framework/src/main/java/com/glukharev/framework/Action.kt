package com.glukharev.framework

import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

interface Action

object IgnoreAction : Action

interface ScreenActionPool {

    fun bindToScope(context: CoroutineScope?)

    fun pushSharedAction(action: Action)

    fun subscribeSharedAction(observer: (Action) -> Unit)
}