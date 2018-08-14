package com.example.marcos.lifecycleawarecomponents

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var auditHelper: AuditHelper? = null
    private val TAG = "AUDIT"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        /**
         * Action that we want to execute when the user start, pause and stop the app.
         * To keep the example single we are just logging the message, but this can be a request to server,
         * database operation, etc.
         * This action is send as parameter to the AuditHelper class
         */
        var action1: (String) -> Unit = {
            Log.i(TAG, String.format(getString(R.string.report_from_main_activity) , it))
        }


        /**
         * Action to execute if a custom operation is done by the user, in this case we are showing a dialog
         * but it could be a complex operations
         * This action is send as parameter to the AuditHelper class
         */
        var action2: (String) -> Unit = {
            var dialog = AlertDialog.Builder(this)
            with(dialog){
                setPositiveButton(getString(R.string.ok_button), null)
                setMessage(String.format(getString(R.string.report_from_main_activity) , it))
                show()
            }

        }

        auditHelper = AuditHelper(action1, action2)

        //Set listener for the buttons
        btn_action1.setOnClickListener { auditHelper?.reportAction(String.format(getString(R.string.report_action) , 1))}
        btn_action2.setOnClickListener { auditHelper?.reportAction(String.format(getString(R.string.report_action) , 2))}
        btn_action3.setOnClickListener { auditHelper?.reportAction(String.format(getString(R.string.report_action) , 3)) }
    }


    override fun onStart() {
        /**
         * We report when the app starts
         */
        auditHelper?.auditStarted()
        super.onStart()
    }

    override fun onPause() {
        /**
         * Report when the app is paused
         */
        auditHelper?.auditPause()
        super.onPause()
    }

    override fun onStop() {
        /**
         * Report when the app is stopped
         */
        auditHelper?.auditStop()
        super.onStop()
    }
}
