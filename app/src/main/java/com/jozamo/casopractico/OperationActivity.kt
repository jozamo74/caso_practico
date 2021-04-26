package com.jozamo.casopractico


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class OperationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operation)

        val btnExit = findViewById<Button>(R.id.btn_exit)
        val btnNew = findViewById<Button>(R.id.btn_new)
        val btnResult = findViewById<Button>(R.id.btn_calculate)
        val operation = findViewById<TextView>(R.id.tv_operation)
        val value1 = findViewById<EditText>(R.id.value1)
        val value2 = findViewById<EditText>(R.id.value2)
        val result = findViewById<TextView>(R.id.result)

        value1.requestFocus()


        intent.extras?.let { bundle ->
            operation.text = bundle.getChar("OPERATION").toString()
        }

        btnResult.setOnClickListener {
            if (value1.text.isNotEmpty() && value2.text.isNotEmpty()) {

                val v1: Double = value1.text.toString().toDouble()
                val v2: Double = value2.text.toString().toDouble()

                if (operation.text.toString() == "/" && v2 == 0.0) {
                    val divZero = getString(R.string.div_zero)
                    Toast.makeText(this, divZero,
                            Toast.LENGTH_SHORT).show()
                } else {

                    val op: Double = when(operation.text.toString()) {
                        "+" -> { v1 + v2 }
                        "-" -> { v1 - v2 }
                        "*" -> { v1 * v2 }
                        "/" -> { v1 / v2 }
                        else -> {0.0}
                    }
                    result.text = op.roundToThreeDecimalPlace().toString()
                    btnExit.isVisible = true
                    btnNew.isVisible = true
                }


            } else {
                val fieldsNotEmpty = getString(R.string.fields_not_empty)
                Toast.makeText(this, fieldsNotEmpty,
                        Toast.LENGTH_SHORT).show()
            }


        }

        btnNew.setOnClickListener {finish()}

        //Finish app
        btnExit.setOnClickListener { finishAffinity()}
    }

    private fun Double.roundToThreeDecimalPlace(): Double {
        val df = DecimalFormat("#.###", DecimalFormatSymbols(Locale.ENGLISH)).apply {
            roundingMode = RoundingMode.HALF_UP
        }
        return df.format(this).toDouble()
    }
}