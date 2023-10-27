package edu.ischool.lton2.tipcalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var amount: Int = 0;
        var textInput: EditText = findViewById(R.id.editTextNumber)
        var tipButton: Button = findViewById(R.id.button)

        textInput.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                amount += 1
                Log.i("Tip Calculator", "Amount inputted changed to : $s")
            }
        })




    }

    fun convertToPennies(s:String?): String?  {
        return ""

    }

    fun calculateTip(s:String?):String {
        // convert to integer then calculate 15% of that
        return 0.toString()
    }
}