package com.sviatkuzbyt.library.ui.other.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import com.sviatkuzbyt.library.R
import com.sviatkuzbyt.library.ui.main.MainActivity
import com.sviatkuzbyt.library.ui.other.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    private val phoneText: EditText by lazy { findViewById(R.id.phoneText) }
    private val passwordText: EditText by lazy { findViewById(R.id.passwordText) }
    private val loginButton: Button by lazy { findViewById(R.id.loginButton) }
    private val registerButton: Button by lazy { findViewById(R.id.registerButton) }
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton.setOnClickListener {
            viewModel.login(
                phoneText.text.toString(),
                passwordText.text.toString()
            )
        }

        viewModel.result.observe(this){
            when(it){
                LoginResult.Successful ->{
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
                LoginResult.NoData ->
                    Toast.makeText(this, getString(R.string.input_data), Toast.LENGTH_LONG).show()
                LoginResult.Failed ->
                    Toast.makeText(this, getString(R.string.login_failed), Toast.LENGTH_LONG).show()
            }
        }

        registerButton.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}