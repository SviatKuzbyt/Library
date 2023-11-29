package com.sviatkuzbyt.library.ui.other.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.viewModels
import com.sviatkuzbyt.library.R
import com.sviatkuzbyt.library.ui.elements.makeToast

class RegisterActivity : AppCompatActivity() {

    private val viewModel: RegisterViewModel by viewModels()
    private val phoneText: EditText by lazy { findViewById(R.id.phoneTextReg) }
    private val passwordText: EditText by lazy { findViewById(R.id.passwordTextReg) }
    private val nameText: EditText by lazy { findViewById(R.id.nameText) }
    private val addressText: EditText by lazy { findViewById(R.id.addressText) }
    private val planSpinner: Spinner by lazy { findViewById(R.id.planSpinner) }
    private val createAccountButton: Button by lazy { findViewById(R.id.createAccountButton) }
    private val cancelButton: Button by lazy { findViewById(R.id.cancelButton) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        createAccountButton.setOnClickListener {
            viewModel.register(
                phoneText.text.toString(),
                passwordText.text.toString(),
                nameText.text.toString(),
                addressText.text.toString(),
                planSpinner.selectedItemPosition
            )
        }

        viewModel.result.observe(this){
            when(it){
                RegisterResult.AlreadyRegistered -> makeToast(R.string.already_reg, this)
                RegisterResult.Error -> makeToast(R.string.error, this)
                RegisterResult.NoData -> makeToast(R.string.input_data, this)
                RegisterResult.Successful -> {
                    makeToast(R.string.successfulReg, this)
                    finish()
                }
            }
        }

        cancelButton.setOnClickListener { finish() }
    }
}