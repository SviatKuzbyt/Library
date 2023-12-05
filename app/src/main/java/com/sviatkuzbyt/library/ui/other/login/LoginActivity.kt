package com.sviatkuzbyt.library.ui.other.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import com.sviatkuzbyt.library.R
import com.sviatkuzbyt.library.databinding.ActivityLoginBinding
import com.sviatkuzbyt.library.ui.elements.makeToast
import com.sviatkuzbyt.library.ui.main.MainActivity
import com.sviatkuzbyt.library.ui.other.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
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

        binding.registerButton.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.passwordText.setOnEditorActionListener { _, actionId, _ ->
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
            binding.phoneText.text.toString(),
            binding.passwordText.text.toString()
        )
    }
}