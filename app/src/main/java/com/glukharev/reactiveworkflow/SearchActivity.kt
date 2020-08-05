package com.glukharev.reactiveworkflow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.glukharev.reactiveworkflow.databinding.ActivityMainBinding
import com.glukharev.reactiveworkflow.databinding.ViewProgressBinding
import com.glukharev.reactiveworkflow.databinding.ViewSearchBinding
import com.glukharev.reactiveworkflow.progress.ProgressComponent
import com.glukharev.reactiveworkflow.progress.ProgressReducer
import com.glukharev.reactiveworkflow.progress.ProgressView
import com.glukharev.reactiveworkflow.search.SearchComponent
import com.glukharev.reactiveworkflow.search.SearchInteractor
import com.glukharev.reactiveworkflow.search.SearchReducer
import com.glukharev.reactiveworkflow.search.SearchView

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var searchBinding: ViewSearchBinding
    private lateinit var progressBinding: ViewProgressBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
        Init view bindings
         */
        binding = ActivityMainBinding.inflate(layoutInflater)
        progressBinding = ViewProgressBinding.bind(binding.root)
        searchBinding = ViewSearchBinding.bind(binding.root)

        setContentView(binding.root)


        /*
        Wire components
         */
        Deps.getSearchComponent(searchBinding).apply {
            wire()
            bindToView()
        }

        Deps.getProgressComponent(progressBinding).apply {
            wire()
            bindToView()
        }
    }
}

object Deps {

    private val searchInteractor = SearchInteractor()
    private val searchReducer = SearchReducer()

    private val progressReducer = ProgressReducer()

    /**
     * Activity/fragment scope
     */
    private val sharedActionPool = BlahBlahScreenActionPool()

    fun getSearchComponent(viewSearchBinding: ViewSearchBinding) = SearchComponent(
        searchInteractor,
        searchReducer,
        SearchView(viewSearchBinding),
        sharedActionPool
    )

    fun getProgressComponent(viewProgressBinding: ViewProgressBinding) = ProgressComponent(
        progressReducer,
        ProgressView(viewProgressBinding),
        sharedActionPool
    )
}