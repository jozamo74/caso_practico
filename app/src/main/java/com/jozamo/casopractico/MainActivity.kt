package com.jozamo.casopractico

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(v: View) {
        when(v.id) {
            R.id.btn_add -> { goToOperationActivity('+')}
            R.id.btn_sub -> {goToOperationActivity('-')}
            R.id.btn_multi -> {goToOperationActivity('*')}
            R.id.btn_div -> {goToOperationActivity('/')}
        }
    }

    private fun goToOperationActivity(operation: Char) {
        val intent = Intent(this, OperationActivity::class.java)
        intent.putExtra("OPERATION", operation)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.i("VALOR", "$requestCode $resultCode")
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) finish()
        }

    }

}