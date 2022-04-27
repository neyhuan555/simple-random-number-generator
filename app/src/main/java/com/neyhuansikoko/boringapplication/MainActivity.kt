package com.neyhuansikoko.boringapplication

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.neyhuansikoko.boringapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var model: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        binding.apply {
            editTextMin.setText(model.min)
            editTextMax.setText(model.max)

            textViewResult.text = model.result

            buttonRoll.setOnClickListener {
                getRoll()
            }

            buttonSetDefault.setOnClickListener {
                editTextMin.setText(getString(R.string.default_min_value))
                editTextMax.setText(getString(R.string.default_max_value))
            }
        }
    }

    private fun updateModel() {
        model.min = binding.editTextMin.text.toString()
        model.max = binding.editTextMax.text.toString()
    }

    private fun checkField(): Boolean {
        updateModel()
        return when {
            model.checkEmpty() -> {
                Snackbar.make(binding.buttonRoll, getString(R.string.snackBar_emptyFieldMessage), Snackbar.LENGTH_LONG).show()
                false
            }
            model.checkInvalidRange() -> {
                Snackbar.make(binding.buttonRoll, getString(R.string.snackBar_invalidRangeMessage), Snackbar.LENGTH_LONG).show()
                false
            }
            else -> {
                true
            }
        }
    }

    private fun getRoll() {
        if (checkField()) {
            model.result = model.roll()
            binding.textViewResult.text = model.result
        }
    }
}