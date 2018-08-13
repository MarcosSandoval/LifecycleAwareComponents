package com.example.marcos.lifecycleawarecomponents

/**
 * Created by marcos on 8/13/18.
 */
class AuditHelper(private val action1: (String) -> Unit, private val action2: (String) -> Unit) {

    fun auditStarted(){
        action1.invoke("App started")
    }

    fun auditPause(){
        action1.invoke("App paused")
    }

    fun auditStop(){
        action1.invoke("App stopped")
    }

    fun reportAction(action: String){
        action2.invoke(action)
    }

}