package com.udacity.shoestore.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.model.Shoe
import com.udacity.shoestore.viewmodel.ShoeStoreViewModel

class ShoeDetailFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding: FragmentShoeDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_detail, container, false)

        val viewModel by activityViewModels<ShoeStoreViewModel>()

        binding.buttonCancel.setOnClickListener {
            // TODO: show confirmation dialog
            binding.inputShoeNameLayout.error = "Error"
            binding.inputCompanyLayout.error = "Error"
            binding.inputShoeSizeLayout.error = "Error"
            binding.inputShoeDescriptionLayout.error = "Error"
        }

        binding.buttonSave.setOnClickListener {
            // TODO: show confirmation dialog
            it.findNavController().popBackStack()

            viewModel.addShoe(
                Shoe(
                    binding.inputShoeName.text.toString(),
                    binding.inputShoeSize.text.toString().toDouble(),
                    binding.inputCompany.text.toString(),
                    binding.inputShoeDescription.text.toString(),

                )
            )
        }

        return binding.root
    }
}