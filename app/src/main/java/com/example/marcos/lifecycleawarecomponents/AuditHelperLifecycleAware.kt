package com.example.marcos.lifecycleawarecomponents

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent

class AuditHelperLifecycleAware(private val lifecycle: Lifecycle ,private val action1: (String) -> Unit, private val action2: (String) -> Unit) : LifecycleObserver{


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun auditStarted(){
        action1.invoke("App started")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun auditPause(){
        action1.invoke("App paused")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun auditStop(){
        action1.invoke("App stopped")
    }

    fun reportAction(action: String){
        if(lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)){
            action2.invoke(action)
        }
    }
}