package edu.uw.ischool.annietu8.tipcalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private val TAG: String = "MainActivity"
    private lateinit var tipButton : Button
    private lateinit var billEditText : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tipButton = findViewById(R.id.tipButton)
        billEditText = findViewById(R.id.editTextNumber)


        billEditText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val input = s.toString().replace("$", "").toDoubleOrNull() ?: 0.0
                // tipButton.isEnabled = input.isNotBlank()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val input = s.toString().replace("$", "").toDoubleOrNull() ?: 0.0
                tipButton.isEnabled = input > 0.0            }
        })

        tipButton.setOnClickListener {
            val input = billEditText.text.toString()
            if (input.isNotBlank()) {
                val serviceCharge = input.toDouble()
                val tipAmount = serviceCharge * 0.15 // 15% tip
                val formattedTip = String.format("$%.2f", tipAmount)
                showTipToast(formattedTip)
            }
        }
    }
    private fun showTipToast(formattedTip: String) {
        Toast.makeText(this, "Tip: $formattedTip", Toast.LENGTH_SHORT).show()
    }
}