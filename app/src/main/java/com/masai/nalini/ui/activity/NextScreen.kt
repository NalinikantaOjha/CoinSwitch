package com.masai.nalini.ui.activity

import com.masai.nalini.R



import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast

class NextScreen : AppCompatActivity() {
    private lateinit var editText1: EditText
    private lateinit var editText2: EditText
    private lateinit var editText3: EditText
    private lateinit var editText4: EditText
    private lateinit var linearLayout: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next_screen)
        //To hide action bar
        supportActionBar?.hide()
        editText1 = findViewById(R.id.imageview_circle1)
        editText2 = findViewById(R.id.imageview_circle2)
        editText3 = findViewById(R.id.imageview_circle3)
        editText4 = findViewById(R.id.imageview_circle4)
        linearLayout = findViewById(R.id.LinearLay)
        edit()

        var intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)


    }

    private fun edit() {
        editText1.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                editText2.requestFocus()
            }
        })
        editText2.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                editText3.requestFocus()
            }
        })
        editText3.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                editText3.requestFocus()
            }
        })
        Nexts()
    }
    private fun Nexts(){
        Handler().postDelayed({
            var intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }, 6000)

    }

}
