package com.example.calculatorapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView

    // necessary variables for the mathermatical operations
    private var firstNum: Double? = null
    private var secondNum: Double? = null
    private var currentOperation: String? = null
    private var isNewOp = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.resultTextView)
    }

    // method for clicking one of the numbers
    fun onNumberClick(view: View) {
        if (isNewOp) {
            resultTextView.text = ""
            isNewOp = false
        }

        val button = view as Button
        var result = resultTextView.text.toString()

        if(button.text == "." && result.contains('.')) {
            // do nothing if '.' is clicked twice i dont want multiple decimal points in a number
        } else {
            result += button.text
            resultTextView.text = result
        }
    }

    // method for clicking on an operator
    fun onOperatorClick(view: View) {
        firstNum = resultTextView.text.toString().toDoubleOrNull()
        isNewOp = true
        val button = view as Button
        currentOperation = button.text.toString()
    }

    // equal is the button that activates the mathematical operation
    fun onEqualClick(view: View) {
        secondNum = resultTextView.text.toString().toDoubleOrNull()
        if (firstNum != null && secondNum != null && currentOperation != null) {
            when (currentOperation) {
                "+" -> resultTextView.text = (firstNum!! + secondNum!!).toString()
                "-" -> resultTextView.text = (firstNum!! - secondNum!!).toString()
                "X" -> resultTextView.text = (firstNum!! * secondNum!!).toString()
                "÷" -> if (secondNum != 0.0) {
                    resultTextView.text = (firstNum!! / secondNum!!).toString()
                } else {
                    resultTextView.text = "Error"
                }
            }
        }
        firstNum = resultTextView.text.toString().toDoubleOrNull()
        currentOperation = null
        isNewOp = true
    }

    // C button logic
    fun onClearClick(view: View) {
        resultTextView.text = "0"
        firstNum = null
        secondNum = null
        currentOperation = null
        isNewOp = true
    }

    // turns the positive value to its negative or vice versa
    fun onToggleSignClick(view: View) {
        val value = resultTextView.text.toString().toDoubleOrNull()
        if (value != null) {
            resultTextView.text = (-value).toString()
        }
    }

    // divides the number by 100
    fun onPercentageClick(view: View) {
        val value = resultTextView.text.toString().toDoubleOrNull()
        if (value != null) {
            resultTextView.text = (value / 100).toString()
        }
    }



}