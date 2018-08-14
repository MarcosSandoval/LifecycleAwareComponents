package com.example.marcos.lifecycleawarecomponents

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivityLifecycleAware : AppCompatActivity() {

    private var auditHelper: AuditHelperLifecycleAware? = null
    private val TAG = "AUDIT"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var action1: (String) -> Unit = {
            Log.i(TAG, it)
        }

        var action2: (String) -> Unit = {
            var dialog = AlertDialog.Builder(this)
            with(dialog){
                setPositiveButton(getString(R.string.ok_button), null)
                setMessage(it)
                show()
            }

        }

        auditHelper = AuditHelperLifecycleAware(lifecycle,action1, action2)

        //Set listener for the buttons
        btn_action1.setOnClickListener { auditHelper?.reportAction(String.format(getString(R.string.report_action) , 1))}
        btn_action2.setOnClickListener { auditHelper?.reportAction(String.format(getString(R.string.report_action) , 2))}
        btn_action3.setOnClickListener { auditHelper?.reportAction(String.format(getString(R.string.report_action) , 3)) }
        lifecycle.addObserver(auditHelper!!)
        var customLifecycleOwner = CustomLifecycleOwner(context = this)
    }


    override fun onStart() {
        super.onStart()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }
}