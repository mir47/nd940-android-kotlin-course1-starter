package com.udacity.shoestore.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.viewmodel.ShoeStoreViewModel

class ShoeDetailFragment : Fragment() {
    private lateinit var binding: FragmentShoeDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)

        val viewModel by activityViewModels<ShoeStoreViewModel>()
        binding.viewModel = viewModel

        viewModel.showError.observe(viewLifecycleOwner) {
            if (it) showError()
        }

        viewModel.completeScreen.observe(viewLifecycleOwner) {
            if (it) {
                binding.inputShoeName.setText("")
                binding.inputCompany.setText("")
                binding.inputShoeSize.setText("")
                binding.inputShoeDescription.setText("")
                view?.findNavController()?.popBackStack()
                viewModel.resetScreen()
                closeKeyboard()
            }
        }

        listOf(
            binding.inputShoeNameLayout,
            binding.inputCompanyLayout,
            binding.inputShoeSizeLayout,
            binding.inputShoeDescriptionLayout
        ).forEach { textInputLayout ->
            textInputLayout.editText?.doAfterTextChanged { editable ->
                if (!editable.isNullOrBlank()) {
                    textInputLayout.error = null
                    viewModel.resetError()
                }
            }
        }

        return binding.root
    }

    private fun showError() {
        if (binding.inputShoeName.text.isNullOrEmpty()) {
            binding.inputShoeNameLayout.error = getString(R.string.shoe_detail_name_error)
        }

        if (binding.inputCompany.text.isNullOrEmpty()) {
            binding.inputCompanyLayout.error = getString(R.string.shoe_detail_company_error)
        }

        if (binding.inputShoeSize.text.isNullOrEmpty()) {
            binding.inputShoeSizeLayout.error = getString(R.string.shoe_detail_size_error)
        }

        if (binding.inputShoeDescription.text.isNullOrEmpty()) {
            binding.inputShoeDescriptionLayout.error = getString(R.string.shoe_detail_description_error)
        }
    }

    private fun closeKeyboard() {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}