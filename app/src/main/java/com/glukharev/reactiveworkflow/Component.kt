package com.glukharev.reactiveworkflow

import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import com.glukharev.framework.Interactor

/**
 * Component is core-intermediate layer for :
 *
 * -receiving actions from UI layer;
 * -sending/observing actions to Interactor layer;
 * -sending/observing common actions between different components on current screen (${FeatureName}CommonScreenActionPool);
 *
 * -creating states via Reducer and sending them to UI layer;
 *
 * -?AppState shared across all app?;
 *
 */
abstract class Component : ViewModel() {
    abstract fun bindToView()
    abstract fun wire()
    abstract fun unWire()

    override fun onCleared() {
        super.onCleared()
        unWire()
    }
}