package com.udacity.shoestore.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        binding.inputEmail.doAfterTextChanged {
            if (!it.isNullOrBlank()) {
                binding.inputEmailLayout.error = null
            }
        }

        binding.inputPassword.doAfterTextChanged {
            if (!it.isNullOrBlank()) {
                binding.inputPasswordLayout.error = null
            }
        }

        binding.buttonLogin.setOnClickListener(::next)

        binding.buttonSignUp.setOnClickListener(::next)

        return binding.root
    }

    private fun next(view: View) {
        if (validateInputs()) {
            view.findNavController()
                .navigate(LoginFragmentDirections
                    .actionLoginFragmentToWelcomeFragment(binding.inputEmail.text.toString()))
        }
    }

    private fun validateInputs(): Boolean {
        var valid = true
        if (binding.inputEmail.text.isNullOrEmpty()) {
            binding.inputEmailLayout.error = getString(R.string.login_email_error)
            valid = false
        }

        if (binding.inputPassword.text.isNullOrEmpty()) {
            binding.inputPasswordLayout.error = getString(R.string.login_password_error)
            valid = false
        }

        return valid
    }
}