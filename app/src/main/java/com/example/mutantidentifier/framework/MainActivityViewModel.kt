package com.example.mutantidentifier.framework

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mutantidentifier.usecases.Mutant

class MainActivityViewModel : ViewModel() {

    private val _mutantAnswerVisibility = MutableLiveData<Boolean>()
    val mutantAnswerVisibility : LiveData<Boolean> get() = _mutantAnswerVisibility

    private val _isValidInput = MutableLiveData<Boolean>()
    val isValidInput : LiveData<Boolean> get() = _isValidInput

    fun onButtonVerifyIsMutant(matrix: String){
        val mutant = Mutant()
        _mutantAnswerVisibility.value = mutant.onUserClickVerifyAdnIsMutant(matrix)
    }
    fun verifyIsValidInput(matrix: String){
        val mutant = Mutant()
        _isValidInput.value = mutant.onUserAskValidInput(matrix)
    }
}