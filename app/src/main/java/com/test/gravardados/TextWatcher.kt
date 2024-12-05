package com.test.gravardados

import android.R
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class TextWatcher : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_text_watcher)
//
//        val editText = findViewById<EditText>(R.id.editTextDate)
//
//        val filters =
//            arrayOf(LengthFilter(10),  // Limita a quantidade de caracteres a 10 (formato dd/MM/yyyy)
//                InputFilter { source, start, end, dest, dstart, dend -> // Restringe caracteres para dígitos e barra "/"
//                    if (source == "") {
//                        return@InputFilter null
//                    }
//                    if (!Character.isDigit(source[0]) && source[0] != '/') {
//                        return@InputFilter ""
//                    }
//
//
//                    // Adiciona a barra automaticamente após 2 e 5 caracteres
//                    val builder = StringBuilder(dest)
//                    val count = dstart + end - start
//                    if (count == 2 || count == 5) {
//                        builder.insert(dstart + count, "/")
//                    }
//                    builder
//                }
//            )
//
//
//                val editText = findViewById<EditText>(R.id.editTextDate)
//
//                editText.addTextChangedListener(object : TextWatcher {
//                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
//
//                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                        val currentText = s.toString()
//                        if (currentText.length == 2 || currentText.length == 5) {
//                            if (currentText.lastOrNull() != '/') {
//                                editText.setText(currentText + "/")
//                                editText.setSelection(editText.text.length)
//                            }
//                        }
//                    }
//
//                    override fun afterTextChanged(s: Editable?) {}
//                })
//            }
        }
}