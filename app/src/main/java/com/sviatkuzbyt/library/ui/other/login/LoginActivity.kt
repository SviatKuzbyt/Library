package com.sviatkuzbyt.library.ui.other.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import com.sviatkuzbyt.library.R
import com.sviatkuzbyt.library.ui.elements.makeToast
import com.sviatkuzbyt.library.ui.main.MainActivity
import com.sviatkuzbyt.library.ui.other.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    private val phoneText: EditText by lazy { findViewById(R.id.phoneTextReg) }
    private val passwordText: EditText by lazy { findViewById(R.id.passwordTextReg) }
    private val loginButton: Button by lazy { findViewById(R.id.loginButton) }
    private val registerButton: Button by lazy { findViewById(R.id.registerButton) }
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton.setOnClickListener {
            login()
        }

        viewModel.result.observe(this){
            when(it){
                LoginResult.Successful ->{
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
                LoginResult.NoData ->
                    makeToast(R.string.input_data, this)
                LoginResult.Failed ->
                    makeToast(R.string.login_failed, this)
            }
        }

        registerButton.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        passwordText.setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    login()
                    true
                }
                else -> false
            }
        }
    }

    private fun login(){
        viewModel.login(
            phoneText.text.toString(),
            passwordText.text.toString()
        )
    }
}