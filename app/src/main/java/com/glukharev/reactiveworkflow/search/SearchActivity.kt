package com.glukharev.reactiveworkflow.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.glukharev.reactiveworkflow.R
import kotlinx.android.synthetic.main.activity_main.*

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // to DI
        val interactor = SearchInteractor()
        val reducer = SearchReducer()
        val view = SearchView()

        val component = SearchComponent(interactor, reducer, view)

        component.wire()
        component.bindToView(containerView)
    }
}