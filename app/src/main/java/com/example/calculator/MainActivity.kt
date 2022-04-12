package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.core.text.isDigitsOnly
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
    var op = "*"
    var oldNumber = ""
    var isNewOp = true

    fun buttonEvent(view: View) {
        if (isNewOp){
            binding.editShowNumber.setText("")
        }
        isNewOp = false
        val buttonClicked = view as Button
        var buttonClickValue: String = binding.editShowNumber.text.toString()
        when (buttonClicked.id) {
            R.id.bu0 -> buttonClickValue += "0"
            R.id.bu1 -> buttonClickValue += "1"
            R.id.bu2 -> buttonClickValue += "2"
            R.id.bu3 -> buttonClickValue += "3"
            R.id.bu4 -> buttonClickValue += "4"
            R.id.bu5 -> buttonClickValue += "5"
            R.id.bu6 -> buttonClickValue += "6"
            R.id.bu7 -> buttonClickValue += "7"
            R.id.bu8 -> buttonClickValue += "8"
            R.id.bu9 -> buttonClickValue += "9"
            R.id.buDot ->
                //TODO:prevent adding more than dot
                buttonClickValue = "."
            R.id.buPlusOrMinus -> buttonClickValue = "-$buttonClickValue"
        }
        binding.editShowNumber.setText(buttonClickValue)
    }

    fun operatorEvent(view: View) {
        val buttonSelected = view as Button
        when(buttonSelected.id){
            R.id.buMinus -> op = "-"
            R.id.buPlus -> op = "+"
            R.id.buTimes -> op = "X"
            R.id.buDivision -> op = "/"
        }
        oldNumber = binding.editShowNumber.text.toString()
        isNewOp = true
    }

    fun buEqual(view:View){
        val newNumber = binding.editShowNumber.text.toString()
        var finalNumber:Double? = null
        when(op){
            "X" -> {
                finalNumber = oldNumber.toDouble() * newNumber.toDouble()
            }
            "+" -> {
                finalNumber = oldNumber.toDouble() + newNumber.toDouble()
            }
            "-" -> {
                finalNumber = oldNumber.toDouble() - newNumber.toDouble()
            }
            "/" -> {
                finalNumber = oldNumber.toDouble() / newNumber.toDouble()
            }
        }
        binding.editShowNumber.setText(finalNumber.toString())
        isNewOp = true

    }

    fun buPercent(view:View){
        var number = binding.editShowNumber.text.toString().toDouble()/100
        binding.editShowNumber.setText(number.toString())
    }
    fun buClean(view: View){
        binding.editShowNumber.setText("")
        isNewOp
    }
}