package com.alfatih.shoping.main.accountype

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AccountTypeViewModel : ViewModel() {
    val db = Firebase.firestore
    private var _account = MutableLiveData<String>()

    init {
        _account.value = "shoper"
    }

    val account: LiveData<String> get() = _account

    fun changeAccount(string: String) {
        _account.value = string
    }
}