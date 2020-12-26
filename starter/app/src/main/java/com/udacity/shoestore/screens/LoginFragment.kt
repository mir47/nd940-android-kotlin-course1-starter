package com.udacity.shoestore.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false)
        binding.buttonSignUp.setOnClickListener { view ->
            view.findNavController()
                .navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(binding.inputEmail.text.toString()))
        }
        binding.buttonLogin.setOnClickListener { view ->
            view.findNavController()
                .navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(binding.inputEmail.text.toString()))
        }
        return binding.root
    }

    // TODO: save email and password state, maybe use a "remember me" checkbox
}