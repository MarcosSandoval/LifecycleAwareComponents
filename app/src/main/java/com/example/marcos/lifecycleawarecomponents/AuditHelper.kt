package com.example.marcos.lifecycleawarecomponents

/**
 * Created by marcos on 8/13/18.
 */
class AuditHelper(private val action1: (String) -> Unit, private val action2: (String) -> Unit) {

    /**
     * Executed an action when the app starts
     */
    fun auditStarted(){
        action1.invoke("App started")
    }

    /**
     * Executed an action when the app is paused
     */
    fun auditPause(){
        action1.invoke("App paused")
    }

    /**
     * Executed an action when the app is stopped
     */
    fun auditStop(){
        action1.invoke("App stopped")
    }

    /**
     * Reports when any custom action is done
     */
    fun reportAction(action: String){
        action2.invoke(action)
    }
}