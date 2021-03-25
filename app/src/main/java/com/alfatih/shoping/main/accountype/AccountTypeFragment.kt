package com.alfatih.shoping.main.accountype

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.alfatih.shoping.databinding.AccountTypeFragmentBinding
import com.alfatih.shoping.home.HomeActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AccountTypeFragment : Fragment() {

    private lateinit var binding: AccountTypeFragmentBinding


    companion object {
        fun newInstance() = AccountTypeFragment()
    }

    private lateinit var viewModel: AccountTypeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = AccountTypeFragmentBinding.inflate(inflater, container, false)
        binding.shoperBtn.setOnClickListener {
            viewModel.changeAccount("shopper")
            addUser(viewModel.account.value)
            startActivity(Intent(requireContext(), HomeActivity::class.java))
        }
        binding.traderBtn.setOnClickListener {
            viewModel.changeAccount("trader")
            addUser(viewModel.account.value)
            startActivity(Intent(requireContext(), HomeActivity::class.java))

        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AccountTypeViewModel::class.java)
        // TODO: Use the ViewModel
    }

    fun addUser(accountType: String?) {
        // Access a Cloud Firestore instance from your Activity
        val db = Firebase.firestore
        // Add a new document with a generated ID
        val user = hashMapOf(
            "name" to FirebaseAuth.getInstance().currentUser?.displayName.toString(),
            "email" to FirebaseAuth.getInstance().currentUser?.email.toString(),
            "account_type" to accountType,
            "uid" to FirebaseAuth.getInstance().currentUser?.uid
        )
        db.collection("users").document(user["uid"]!!)
            .set(user)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(requireContext(),
                    "successful", Toast.LENGTH_SHORT).show()
                this.requireActivity().finish()
                //Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Toast.makeText(requireContext(),
                    "error: ${e}", Toast.LENGTH_SHORT).show()
            }
    }

}