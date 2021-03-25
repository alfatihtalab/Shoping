package com.alfatih.shoping.home.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alfatih.shoping.database.UsersDatabase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SettingViewModel : ViewModel() {

    var userDatabase = UsersDatabase()


    private var _imageUrl = MutableLiveData<String>()
    private var _name = MutableLiveData<String>()
    private var _email = MutableLiveData<String>()
    private var _acountType = MutableLiveData<String>()

    init {

        _email.value = FirebaseAuth.getInstance().currentUser?.email.toString()
        _name.value = FirebaseAuth.getInstance().currentUser?.displayName.toString()
        _imageUrl.value = FirebaseAuth.getInstance().currentUser?.photoUrl.toString()
        getUserData("users", FirebaseAuth.getInstance()
            .currentUser?.uid.toString())
    }

    val username: LiveData<String> get() = _name
    val email: LiveData<String> get() = _email
    val photoUrl: LiveData<String> get() = _imageUrl
    val accoutType: LiveData<String> get() = _acountType

    open fun getUserData(collection: String, document: String?) {
        val db = Firebase.firestore
        val docRef = db.collection(collection).document(document!!)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    _acountType.value = document.data?.get("account_type").toString()
                } else {
                    _acountType.value = "none"
                }
            }
            .addOnFailureListener { exception ->
                //Log.d(TAG, "get failed with ", exception)
            }

    }

}