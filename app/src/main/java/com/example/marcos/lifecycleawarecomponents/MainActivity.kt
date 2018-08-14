package com.example.marcos.lifecycleawarecomponents

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var auditHelper: AuditHelper? = null
    private val TAG = "AUDIT"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var action1: (String) -> Unit = {
            Log.i(TAG, String.format(getString(R.string.report_from_main_activity) , it))
        }

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
        auditHelper?.auditStarted()
        super.onStart()
    }

    override fun onPause() {
        auditHelper?.auditPause()
        super.onPause()
    }

    override fun onStop() {
        auditHelper?.auditStop()
        super.onStop()
    }
}
