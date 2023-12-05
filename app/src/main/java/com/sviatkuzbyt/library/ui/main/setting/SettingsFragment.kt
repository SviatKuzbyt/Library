package com.sviatkuzbyt.library.ui.main.setting

import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sviatkuzbyt.library.R
import com.sviatkuzbyt.library.data.database.DatabaseManager
import com.sviatkuzbyt.library.data.other.CurrentUserManager
import com.sviatkuzbyt.library.databinding.FragmentReadingBinding
import com.sviatkuzbyt.library.databinding.FragmentSettingsBinding
import com.sviatkuzbyt.library.ui.elements.recycleradapters.LabelDataAdapter
import com.sviatkuzbyt.library.ui.main.MainActivity
import com.sviatkuzbyt.library.ui.main.setting.changeaccount.ChangeAccountActivity
import com.sviatkuzbyt.library.ui.other.login.LoginActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SettingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        if(DatabaseManager.changeAccountSetting == true)
            viewModel.loadUserData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.accountData.layoutManager = LinearLayoutManager(requireContext())
        binding.textAbout.movementMethod = LinkMovementMethod.getInstance()

        viewModel.accountDataList.observe(viewLifecycleOwner){
            binding.accountData.adapter = LabelDataAdapter(it, requireContext())
        }

        binding.changeAccountParam.setOnClickListener {
            startActivity(Intent(requireContext(), ChangeAccountActivity::class.java))
        }

        binding.logOut.setOnClickListener {
            lifecycleScope.launch {
                CurrentUserManager(requireContext()).changeUser(CurrentUserManager.NO_LOGIN)
                withContext(Dispatchers.Main){
                    startActivity(Intent(requireActivity(), LoginActivity::class.java))
                    requireActivity().finish()
                }
            }
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}