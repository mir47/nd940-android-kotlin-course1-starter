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

        binding.inputShoeName.doAfterTextChanged {
            if (!it.isNullOrBlank()) {
                binding.inputShoeNameLayout.error = null
            }
        }

        binding.inputCompany.doAfterTextChanged {
            if (!it.isNullOrBlank()) {
                binding.inputCompanyLayout.error = null
            }
        }

        binding.inputShoeSize.doAfterTextChanged {
            if (!it.isNullOrBlank()) {
                binding.inputShoeSizeLayout.error = null
            }
        }

        binding.inputShoeDescription.doAfterTextChanged {
            if (!it.isNullOrBlank()) {
                binding.inputShoeDescriptionLayout.error = null
            }
        }

        binding.buttonCancel.setOnClickListener { it.findNavController().popBackStack() }

        binding.buttonSave.setOnClickListener {
            if (validateInputs()) {
                it.findNavController().popBackStack()
                viewModel.addShoe(
                    Shoe(
                        binding.inputShoeName.text.toString(),
                        binding.inputShoeSize.text.toString().toDouble(),
                        binding.inputCompany.text.toString(),
                        binding.inputShoeDescription.text.toString()
                    )
                )
            }
        }

        return binding.root
    }

    private fun validateInputs(): Boolean {
        var valid = true

        if (binding.inputShoeName.text.isNullOrEmpty()) {
            binding.inputShoeNameLayout.error = getString(R.string.shoe_detail_name_error)
            valid = false
        }

        if (binding.inputCompany.text.isNullOrEmpty()) {
            binding.inputCompanyLayout.error = getString(R.string.shoe_detail_company_error)
            valid = false
        }

        if (binding.inputShoeSize.text.isNullOrEmpty()) {
            binding.inputShoeSizeLayout.error = getString(R.string.shoe_detail_size_error)
            valid = false
        }

        if (binding.inputShoeDescription.text.isNullOrEmpty()) {
            binding.inputShoeDescriptionLayout.error = getString(R.string.shoe_detail_description_error)
            valid = false
        }

        return valid
    }
}