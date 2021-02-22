package com.gasanovmagomed.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    private lateinit var height: EditText
    private lateinit var weight: EditText
    private var heightNum: Double = 0.0
    private var weightNum: Double = 0.0
    private lateinit var width: TextView
    private lateinit var image: ImageView
    private lateinit var countResult: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        height = findViewById(R.id.height)
        weight = findViewById(R.id.weight)
    }

    private fun showModal() {
        // create modal "builder"
        val builder = AlertDialog.Builder(this)
        val inflater: LayoutInflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.result_modal, null)
        width = dialogLayout.findViewById(R.id.wightText)
        image = dialogLayout.findViewById(R.id.catsAssociation)

        // show changeable data
        showChangingData()

        // show modal with "builder"
        with(builder) {
            setTitle("Result view")
            setPositiveButton("OK") { dialog, which ->
                height.text = null
                weight.text = null
            }
            setView(dialogLayout)
            show()
        }
    }

    fun countBMI(view: View) {
        //converting string data to double
        heightNum = height.text.toString().toDouble()
        weightNum = weight.text.toString().toDouble()
        //count BMI index
        val countVal = weightNum / ( heightNum * heightNum )*10000
        // write result in var with converting to string
        countResult = countVal.toString()
        // show modal here
        showModal()
    }

    private fun showChangingData(){
        when {
            countResult.toDouble() < 18.5 -> {
                width.setText(R.string.info)
                image.setImageResource(R.drawable.info)
            }
            countResult.toDouble() in 18.5..24.9 -> {
                width.setText(R.string.normal)
                image.setImageResource(R.drawable.checked)
            }
            countResult.toDouble() in 25.0..29.9 -> {
                width.setText(R.string.warning)
                image.setImageResource(R.drawable.warning)
            }
            countResult.toDouble() > 30 -> {
                width.setText(R.string.danger)
                image.setImageResource(R.drawable.danger)
            }
            else -> Toast.makeText(this, "Error something was wrong", Toast.LENGTH_SHORT).show()
        }
    }

}