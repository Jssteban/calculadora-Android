package com.adso.calculadora

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.adso.calculadora.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() , View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var currentNumber = StringBuilder()
    private var firstNumber = 0.0
    private var operator = ' '

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.number1.setOnClickListener(this)
        binding.number2.setOnClickListener(this)
        binding.number3.setOnClickListener(this)
        binding.number4.setOnClickListener(this)
        binding.number5.setOnClickListener(this)
        binding.number6.setOnClickListener(this)
        binding.number7.setOnClickListener(this)
        binding.number8.setOnClickListener(this)
        binding.number9.setOnClickListener(this)
        binding.number0.setOnClickListener(this)
        binding.suma.setOnClickListener(this)
        binding.resta.setOnClickListener(this)
        binding.mutiplicacion.setOnClickListener(this)
        binding.division.setOnClickListener(this)
        binding.igual.setOnClickListener(this)
        binding.borrarTodo.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.number1 -> appendNumber("1")
            R.id.number2 -> appendNumber("2")
            R.id.number3 -> appendNumber("3")
            R.id.number4 -> appendNumber("4")
            R.id.number5 -> appendNumber("5")
            R.id.number6 -> appendNumber("6")
            R.id.number7 -> appendNumber("7")
            R.id.number8 -> appendNumber("8")
            R.id.number9 -> appendNumber("9")
            R.id.number0 -> appendNumber("0")
            R.id.suma -> setOperator('+')
            R.id.resta -> setOperator('-')
            R.id.mutiplicacion -> setOperator('*')
            R.id.division -> setOperator('/')
            R.id.igual -> calculate()
            R.id.borrarTodo -> clear()
        }
    }

    private fun appendNumber(number: String) {
        currentNumber.append(number)
        updateDisplay()
    }

    private fun setOperator(op: Char) {
        if (currentNumber.isNotEmpty()) {
            firstNumber = currentNumber.toString().toDouble()
            operator = op
            currentNumber.clear()
        }
    }

    private fun calculate() {
        if (currentNumber.isNotEmpty()) {
            val secondNumber = currentNumber.toString().toDouble()
            val result = when (operator) {
                '+' -> firstNumber + secondNumber
                '-' -> firstNumber - secondNumber
                '*' -> firstNumber * secondNumber
                '/' -> firstNumber / secondNumber
                else -> 0.0
            }
            currentNumber.clear()
            currentNumber.append(result)
            updateDisplay()
        }
    }

    private fun clear() {
        currentNumber.clear()
        firstNumber = 0.0
        operator = ' '
        updateDisplay()
    }

    private fun updateDisplay() {
        binding.editTextResult.setText(currentNumber.toString())
    }
}
