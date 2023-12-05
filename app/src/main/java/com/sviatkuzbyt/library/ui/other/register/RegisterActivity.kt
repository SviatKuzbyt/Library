package com.sviatkuzbyt.library.ui.other.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.sviatkuzbyt.library.R
import com.sviatkuzbyt.library.databinding.ActivityAccountChangeBinding
import com.sviatkuzbyt.library.ui.elements.makeToast

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountChangeBinding

    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountChangeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.label.text = getString(R.string.register_label)
        binding.applyButton.text = getString(R.string.create_account)

        binding.applyButton.setOnClickListener {
            viewModel.register(
                binding.phoneTextChange.text.toString(),
                binding.passwordTextChange.text.toString(),
                binding.nameText.text.toString(),
                binding.addressText.text.toString(),
                binding.planSpinner.selectedItemPosition
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

        binding.cancelButton.setOnClickListener { finish() }
    }
}