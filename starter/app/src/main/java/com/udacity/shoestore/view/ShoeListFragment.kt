package com.udacity.shoestore.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ItemShoeBinding
import com.udacity.shoestore.model.Shoe
import com.udacity.shoestore.viewmodel.ShoeStoreViewModel
import timber.log.Timber

class ShoeListFragment : Fragment() {
    private lateinit var binding: FragmentShoeListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_list, container, false)

        binding.fabShoeDetail.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                R.id.action_shoeListFragment_to_shoeDetailFragment
            )
        )

        val viewModel by activityViewModels<ShoeStoreViewModel>()

        viewModel.shoeList.observe(viewLifecycleOwner) {
            for (shoe in it) {
                binding.shoeListLayout.addView(createShoeView(shoe))
            }
        }

        return binding.root
    }

    private fun createShoeView(shoe: Shoe): View {
        val db = DataBindingUtil.inflate<ItemShoeBinding>(LayoutInflater.from(context), R.layout.item_shoe, binding.shoeListLayout, false)
        db.item = shoe
        return db.root
    }
}