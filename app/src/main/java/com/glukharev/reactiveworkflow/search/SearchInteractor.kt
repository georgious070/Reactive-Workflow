package com.glukharev.reactiveworkflow.search

import com.glukharev.framework.Action
import com.glukharev.framework.Interactor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET


class SearchInteractor constructor(private val repository: SearchRepository) : Interactor() {

    override fun handleAction(inputAction: Action?): Flow<Action?>? {
        return flow {
            emit(SearchInteractorAction.StartLoadFromNetwork)
            val dataBackAction = repository.doSideEffect()
            emit(processAction(dataBackAction))
        }
    }

    /**
     * make it as optional fun in Interactor
     */
    private fun processAction(dataBackAction: Action?): Action? {
        return when (dataBackAction) {
            is SearchInteractorInputAction.PageLoaded -> {
                SearchInteractorAction.SearchResult(searchResult = "RANDOM test test SEARCH")
            }
            else -> {
                null
            }
        }
    }
}

class SearchRepository(private val retrofitService: SearchAPI) {

    suspend fun doSideEffect(): Action? {
        // todo add coroutine callback here
        retrofitService.getSearchResults().enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>?, response: Response<Any>?) {
                return
            }

            override fun onFailure(call: Call<Any>?, t: Throwable?) {

            }
        })
        return null
    }
}


interface SearchAPI {

    @GET("5e2d9673-2916-4930-958f-5132287180b5")
    fun getSearchResults(): retrofit2.Call<Any>
}