package com.alfatih.shoping.home.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class SettingViewModel : ViewModel() {

    private var _imageUrl = MutableLiveData<String>()
    private var _name = MutableLiveData<String>()
    private var _email = MutableLiveData<String>()
    private var _acountType = MutableLiveData<String>()

    init {
        _email.value = FirebaseAuth.getInstance().currentUser?.email.toString()
        _name.value = FirebaseAuth.getInstance().currentUser?.displayName.toString()
        _imageUrl.value = FirebaseAuth.getInstance().currentUser?.photoUrl.toString()
        _acountType.value = "shoper"
    }

    val username: LiveData<String> get() = _name
    val email: LiveData<String> get() = _email
    val photoUrl: LiveData<String> get() = _imageUrl
    val accoutType: LiveData<String> get() = _acountType


}