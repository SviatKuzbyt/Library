package com.sviatkuzbyt.library.ui.main.setting.changeaccount

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.sviatkuzbyt.library.R
import com.sviatkuzbyt.library.databinding.ActivityAccountChangeBinding
import com.sviatkuzbyt.library.ui.elements.makeToast
import com.sviatkuzbyt.library.ui.other.register.RegisterViewModel

class ChangeAccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountChangeBinding

    private val viewModel: ChangeAccountViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountChangeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.label.text = getString(R.string.change_account_settings)
        binding.applyButton.text = getString(R.string.save)

        viewModel.user.observe(this){
            binding.phoneTextChange.setText(it.number)
            binding.passwordTextChange.setText(it.password)
            binding.nameText.setText(it.name)
            binding.addressText.setText(it.address)
            binding.planSpinner.setSelection(it.plan)
        }

        binding.applyButton.setOnClickListener {
            viewModel.update(
                binding.phoneTextChange.text.toString(),
                binding.passwordTextChange.text.toString(),
                binding.nameText.text.toString(),
                binding.addressText.text.toString(),
                binding.planSpinner.selectedItemPosition
            )
        }

        viewModel.successful.observe(this){
            if (it){
                makeToast(R.string.data_update, this)
                finish()
            } else
                makeToast(R.string.error, this)
        }

        binding.cancelButton.setOnClickListener {
            finish()
        }

    }
}