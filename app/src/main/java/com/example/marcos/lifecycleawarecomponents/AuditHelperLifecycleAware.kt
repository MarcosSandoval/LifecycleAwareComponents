package com.example.marcos.lifecycleawarecomponents

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent

class AuditHelperLifecycleAware(private val lifecycle: Lifecycle ,private val action1: (String) -> Unit, private val action2: (String) -> Unit) : LifecycleObserver{


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun auditStarted(){
        action1.invoke("App started")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun auditCreated(){
        action1.invoke("App created")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun auditPaused(){
        action1.invoke("App paused")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun auditStopped(){
        action1.invoke("App stopped")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun auditDestroyed(){
        action1.invoke("App destroyed")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun auditResumed(){
        action1.invoke("App resumed")
    }

    fun reportAction(action: String){
        if(lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)){
            action2.invoke(action)
        }
    }
}