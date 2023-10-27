package edu.ischool.lton2.tipcalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.text.InputFilter
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    var amount: Int? = 0;
    var input:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var textInput: EditText = findViewById(R.id.editTextNumber)
        var tipButton: Button = findViewById(R.id.button)

        tipButton.setOnClickListener {
            Toast.makeText(this, calculateTip(), Toast.LENGTH_SHORT).show()
        }

        textInput.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                Log.i("Tip Calculator", s.toString())
                textInput.removeTextChangedListener(this)
                Log.i("Tip Calculator", "amount set to: $amount")
                if (s?.contains(".")!!) {

                }
                if (s.contains(".") && s.subSequence(s.indexOf(".")+1, s.length).length > 2) {
                    textInput.setText("$ ${s.toString().replace(Regex("[^\\d.]"), "").subSequence(0,s.indexOf(".") + 1)}")
                } else {
                    textInput.setText("$ ${s.toString().replace(Regex("[^\\d.]"), "")}")
                }
                input = textInput.text.toString()
                Log.i("Tip Calculator","input changed to : $input")
                tipButton.isEnabled = textInput.text.isNotEmpty()
                textInput.setSelection(textInput.text.length)
                textInput.addTextChangedListener(this)
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })






    }

    fun convertToPennies(s:String?): Int?  {
        var stripped=s.toString().replace(Regex("[^\\d.]"), "")
        Log.i("Tip Calculator", stripped)
        Log.i("Tip Calculator", "converting $s to pennies")
        if (stripped.contains(".")) {
            return stripped?.toDouble()?.toInt()?.times(100)
        }
        return stripped?.toDouble()?.toInt()

    }

    fun convertToDollars(s: Int): String {
        Log.i("Tip Calculator", "Pennies: $s")
        if (s > 100) {
            if (s % 10 == 0) {
                return "${s.toDouble() / 100}0"
            } else {
                return "${s.toDouble() / 100}"
            }
        } else {
            if (s < 10) {
                return "0.0$s"
            } else {
                return "0.$s"
            }

        }

    }

    fun calculateTip():String {
        // convert to integer then calculate 15% of that
        var stripped= input.replace(Regex("[^\\d.]"), "")
        Log.i("Tip Calculator","stripped value: $stripped")
        val tip = stripped.toDouble()?.times(.15)?.times(100)?.toInt()!!
        return "$ ${convertToDollars(tip)}"

    }

}