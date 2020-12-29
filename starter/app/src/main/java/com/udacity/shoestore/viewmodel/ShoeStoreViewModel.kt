package com.udacity.shoestore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.model.Shoe
import java.lang.NumberFormatException

class ShoeStoreViewModel : ViewModel() {
    var shoeItem: Shoe? = null
    var shoeName = ""
    var shoeCompany = ""
    var shoeSize = ""
    var shoeDescription = ""

    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    private val _showError = MutableLiveData<Boolean>()
    val showError: LiveData<Boolean>
        get() = _showError

    private val _completeScreen = MutableLiveData<Boolean>()
    val completeScreen: LiveData<Boolean>
        get() = _completeScreen

    init {
        _shoeList.value = mutableListOf()
    }

    fun save() {
        shoeItem = validateInputs()
        shoeItem?.let {
            _shoeList.value?.add(it)
            completeScreen()
        } ?: run {
            _showError.value = true
        }
    }

    fun completeScreen() {
        _completeScreen.value = true
    }

    fun resetScreen() {
        _completeScreen.value = false
    }

    fun resetError() {
        _showError.value = false
    }

    private fun validateInputs(): Shoe? {
        val size = try {
            shoeSize.toDouble()
        } catch (e: NumberFormatException) {
            -1.0
        }

        return if (!(shoeName.isEmpty() || size == -1.0 || shoeCompany.isEmpty() || shoeDescription.isEmpty())) {
            Shoe(shoeName, size, shoeCompany, shoeDescription)
        } else null
    }
}