package com.alfatih.shoping.home.testFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.alfatih.shoping.R
import com.alfatih.shoping.auth.UserFirebase
import com.alfatih.shoping.database.Product
import com.alfatih.shoping.database.UsersDatabase
import com.alfatih.shoping.databinding.FragmentTestBinding
import com.alfatih.shoping.databinding.FragmentTwoTestBinding
import com.alfatih.shoping.home.testFragment.product.ProductAdabter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class TestFragment : Fragment() {
    val db = Firebase.firestore
    val email = FirebaseAuth.getInstance().currentUser?.email.toString()
    val productList = listOf<Product>(
        Product(R.drawable.doodles, "first", email),
        Product(R.drawable.doodles, "second", "this is deiscrioptino 2"),
        Product(R.drawable.doodles, "third", "this is deiscrioptino 3")
    )

    private lateinit var binding: FragmentTestBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTestBinding.inflate(inflater, container, false)
        val adapter = ProductAdabter(productList)
        binding.recyclerView.adapter = adapter


        return binding.root
    }
}

class TestTwoFragment: Fragment() {
    private lateinit var textView: TextView
    private lateinit var testBinding: FragmentTwoTestBinding
    private val usersDatabase = UsersDatabase()
    private lateinit var collection: String
    val userFirebase = UserFirebase()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        testBinding = FragmentTwoTestBinding.inflate(inflater, container, false)

        testBinding.buttonCollection.setOnClickListener {
            collection = testBinding.editTextCollection.text.toString()
            var userProfile = FirebaseAuth.getInstance().currentUser
            val user = hashMapOf(
                "name" to userProfile?.displayName.toString(),
                "email" to userProfile?.email.toString(),
                "born" to 1993
            )
            usersDatabase.addCollection(collection, user, requireContext())
        }
        return testBinding.root
    }
}