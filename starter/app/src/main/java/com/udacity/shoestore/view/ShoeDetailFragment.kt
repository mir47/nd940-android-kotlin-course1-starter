package com.udacity.shoestore.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.model.Shoe
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
            }
        }

        binding.inputShoeName.doAfterTextChanged {
            if (!it.isNullOrBlank()) {
                binding.inputShoeNameLayout.error = null
                viewModel.resetError()
            }
        }

        binding.inputCompany.doAfterTextChanged {
            if (!it.isNullOrBlank()) {
                binding.inputCompanyLayout.error = null
                viewModel.resetError()
            }
        }

        binding.inputShoeSize.doAfterTextChanged {
            if (!it.isNullOrBlank()) {
                binding.inputShoeSizeLayout.error = null
                viewModel.resetError()
            }
        }

        binding.inputShoeDescription.doAfterTextChanged {
            if (!it.isNullOrBlank()) {
                binding.inputShoeDescriptionLayout.error = null
                viewModel.resetError()
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
}