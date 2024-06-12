package com.example.androidotpvalidator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.example.androidotpvalidator.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTextInputEditTextFeatures()
    }

    private fun setTextInputEditTextFeatures() {
        binding.apply {
            moveFocusToNextField(editTextOne, editTextTwo)
            moveFocusToNextField(editTextTwo, editTextThree)
            moveFocusToNextField(editTextThree, editTextFour)
            moveFocusToNextField(editTextFour, null)

            moveFocusToPreviousField(null, editTextOne)
            moveFocusToPreviousField(editTextOne, editTextTwo)
            moveFocusToPreviousField(editTextTwo, editTextThree)
            moveFocusToPreviousField(editTextThree, editTextFour)
        }
    }

    private fun moveFocusToNextField(
        currentEditText: TextInputEditText,
        nextEditText: TextInputEditText?,
    ) {
        if (currentEditText.id == binding.editTextOne.id) {
            currentEditText.requestFocus()
        }

        currentEditText.doAfterTextChanged {
            if (currentEditText.text.toString().isEmpty()) {
                currentEditText.requestFocus()
            } else {
                nextEditText?.requestFocus()
            }
        }
    }

    private fun moveFocusToPreviousField(previous: TextInputEditText?, current: TextInputEditText) {
        current.doAfterTextChanged {
            if (it.toString().isEmpty()) {
                previous?.requestFocus()
            }
        }
    }
}