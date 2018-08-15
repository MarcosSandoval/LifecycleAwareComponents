package com.example.marcos.lifecycleawarecomponents

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivityLifecycleAware : AppCompatActivity() {

    private var auditHelper: AuditHelperLifecycleAware? = null
    private val TAG = "AUDIT"

    /**
     * We get the lifecycle of the current activity and
     * stored it on a variable
     */
    private var activityLifecycle = lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var action1: (String) -> Unit = {
            Log.i(TAG, String.format(getString(R.string.report_from_main_activity_lifecycle_aware) , it))
        }

        var action2: (String) -> Unit = {
            var dialog = AlertDialog.Builder(this)
            with(dialog){
                setPositiveButton(getString(R.string.ok_button), null)
                setMessage(String.format(getString(R.string.report_from_main_activity_lifecycle_aware) , it))
                show()
            }

        }

        auditHelper = AuditHelperLifecycleAware(activityLifecycle,action1, action2)

        //Set listener for the buttons
        btn_action1.setOnClickListener { auditHelper?.reportAction(String.format(getString(R.string.report_action) , 1))}
        btn_action2.setOnClickListener { auditHelper?.reportAction(String.format(getString(R.string.report_action) , 2))}
        btn_action3.setOnClickListener { auditHelper?.reportAction(String.format(getString(R.string.report_action) , 3))

            /**
             * The magic happens here, we are indicating we want our AuditHelperLifecycleAware
             * instance to be notified of the state changes of this LifecycleOwner
             */
            activityLifecycle.addObserver(auditHelper!!)
       CustomLifecycleOwner(context = this)
    }
}