package com.glukharev.reactiveworkflow.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.glukharev.framework.ObservableActionsSourceImpl
import com.glukharev.reactiveworkflow.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.delay

class SearchActivity : AppCompatActivity() {

    private val actionsSource: ObservableActionsSourceImpl<SearchAction> =
        ObservableActionsSourceImpl<SearchAction>(lifecycleScope)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            actionsSource.send(SearchAction.Test("button"))
        }

        lifecycleScope.launchWhenResumed {
            repeat(10) {
                delay(500)
                actionsSource.send(SearchAction.Test("send #1 - $it"))
            }
        }

        lifecycleScope.launchWhenResumed {
            repeat(10) {
                delay(500)
                actionsSource.send(SearchAction.Test("send #2 - $it"))
            }
        }

        lifecycleScope.launchWhenResumed {
            repeat(10) {
                delay(499)
                actionsSource.send(SearchAction.Test("send #3 - $it"))
            }
        }

        actionsSource.subscribe { action ->
            when (action) {
                is SearchAction.Test -> {
                    actionText.text = "${actionText.text}\nAction: ${action.string}"
                }
            }
        }
    }
}