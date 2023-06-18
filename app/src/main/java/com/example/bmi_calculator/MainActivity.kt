package com.example.bmi_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightText = findViewById<EditText>(R.id.et_weight)
        val heighttext = findViewById<EditText>(R.id.et_height)
        val btnCalculate = findViewById<Button>(R.id.bt_calculate)

        btnCalculate.setOnClickListener {
            val weight = weightText.text.toString()
            val height = heighttext.text.toString()
            if (validateInput(weight, height)) {
                    val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))
                    val bmi2digit = String.format("%.2f", bmi).toFloat()
                    displayResult(bmi2digit)
            }
        }
    }

    private fun validateInput(weight: String?, height: String?) : Boolean{

        return when {
            weight.isNullOrEmpty()->{
                Toast.makeText(this,"Weight is Empty", Toast.LENGTH_SHORT).show()
                return false
            }
            height.isNullOrEmpty()->{
                Toast.makeText(this,"Height is Empty", Toast.LENGTH_SHORT).show()
                return false
            }
            else -> {
                return true
            }
        }
    }
    private fun displayResult(bmi: Float) {
        val resultIndex = findViewById<TextView>(R.id.tv_index)
        val resultDescription = findViewById<TextView>(R.id.tv_result)
        val info = findViewById<TextView>(R.id.tv_info)

        resultIndex.text = bmi.toString()
        info.text = "( Normal range is 18.5 - 24.9 )"

        var resultText = ""
        var color = 0

        when {
            bmi<18.5-> {
                resultText = "Under weight"
                color = R.color.under_weight
            }
            bmi in 18.5..24.9-> {
                resultText= "Healthy"
                color = R.color.normal
            }
            bmi in 25.00..29.99->{
                resultText = "Over weight"
                color = R.color.over_weight
            }
        }
        resultDescription.setTextColor(ContextCompat.getColor(this,color))
        resultDescription.text = resultText
    }

}