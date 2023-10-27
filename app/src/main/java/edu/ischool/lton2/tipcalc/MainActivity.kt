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
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    var amount: Int? = 0;
    var isFormatting = false;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var textInput: EditText = findViewById(R.id.editTextNumber)
        var tipButton: Button = findViewById(R.id.button)


        textInput.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                amount = convertToPennies(s.toString())
                if (!isFormatting) {
                    isFormatting = true
                    if (s.toString().startsWith("$")) {

                    }
                    textInput.setText("$ ${s.toString()}")
                    isFormatting = false
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })




    }

    fun convertToPennies(s:String?): Int?  {
        Log.i("Tip Calculator", "converting $s to pennies")
        return s?.toDouble()?.times(100)?.toInt()

    }

    fun convertToDollars() {}

    fun calculateTip(s:String?):String {
        // convert to integer then calculate 15% of that
        val tip = amount * .15
        return "$ ${tip.toString()}"
    }
}