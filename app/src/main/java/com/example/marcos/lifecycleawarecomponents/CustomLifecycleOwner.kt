package com.example.marcos.lifecycleawarecomponents

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistry
import android.content.Context
import android.support.annotation.NonNull
import android.support.v7.app.AlertDialog
import android.util.Log

class CustomLifecycleOwner(private val context: Context) : LifecycleOwner {

    private val TAG = "AUDIT 2"
    private var auditHelper: AuditHelperLifecycleAware? = null
    private var lifecycle: LifecycleRegistry = LifecycleRegistry(this)

    init {
        lifecycle.markState(Lifecycle.State.INITIALIZED)
        onCreate()
    }

    private fun onStart(){
        lifecycle.markState(Lifecycle.State.STARTED)
        onResume()
    }

    private fun onCreate(){

        var action1: (String) -> Unit = {
            Log.i(TAG, it)
        }

        var action2: (String) -> Unit = {
            var dialog = AlertDialog.Builder(context)
            with(dialog){
                setPositiveButton("OK", null)
                setMessage(it)
                show()
            }
        }

        auditHelper = AuditHelperLifecycleAware(lifecycle,action1, action2)
        lifecycle.addObserver(auditHelper!!)

        lifecycle.markState(Lifecycle.State.CREATED)
        onStart()
    }

    private fun onDestroy(){
        lifecycle.markState(Lifecycle.State.DESTROYED)
    }


    private fun onResume(){
        lifecycle.markState(Lifecycle.State.RESUMED)
        onDestroy()
    }


    @NonNull
    override fun getLifecycle(): Lifecycle {
        return lifecycle
    }
}