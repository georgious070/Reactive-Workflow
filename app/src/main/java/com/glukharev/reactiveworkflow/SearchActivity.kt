package com.glukharev.reactiveworkflow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.glukharev.framework.ScreenActionPool
import com.glukharev.reactiveworkflow.databinding.ActivityMainBinding
import com.glukharev.reactiveworkflow.databinding.ViewProgressBinding
import com.glukharev.reactiveworkflow.databinding.ViewSearchBinding
import com.glukharev.reactiveworkflow.progress.ProgressComponent
import com.glukharev.reactiveworkflow.progress.ProgressReducer
import com.glukharev.reactiveworkflow.progress.ProgressView
import com.glukharev.reactiveworkflow.search.*
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

    private val searchRepository = SearchRepository(RetrofitINST.getApi())

    private val searchInteractor = SearchInteractor(searchRepository)
    private val searchReducer = SearchReducer()

    private val progressReducer = ProgressReducer()

    /**
     * Activity/fragment scope
     */
    private val sharedActionPool = ScreenActionPool()

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


    object RetrofitINST {

        private val gson = GsonBuilder()
            .setLenient()
            .create()

        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://run.mocky.io/v3/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        private val service: SearchAPI = retrofit.create(SearchAPI::class.java)

        fun getApi() = service
    }
}