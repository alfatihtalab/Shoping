package com.alfatih.shoping.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.addCallback
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.alfatih.shoping.LauncherActivity
import com.alfatih.shoping.R
import com.alfatih.shoping.auth.AuthActivity
import com.alfatih.shoping.auth.UserFirebase
import com.alfatih.shoping.databinding.FragmentWelcomeBinding
import com.alfatih.shoping.home.HomeActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth


class Welcome : Fragment() {
    private lateinit var _binding: FragmentWelcomeBinding
    private val firebaseAuth = FirebaseAuth(FirebaseApp.getInstance())
    private val userFirebase = UserFirebase()

    private val images = arrayOf(R.drawable.doodles,R.drawable.doodles,R.drawable.doodles)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

            _binding = FragmentWelcomeBinding.inflate(inflater, container, false)

                val adabter = StarterAdabter(images)
                val viewPager2 = _binding.viewPager
                val tabLayout = _binding.tabLayout

                viewPager2.adapter = adabter
                viewPager2.setPageTransformer(ZoomOutPageTransformer())
                TabLayoutMediator(tabLayout, viewPager2) { tab, posion ->
                }.attach()
        _binding.joinBtn.setOnClickListener {
            userFirebase.signIn(requireContext(),requireActivity())
        }

        // The callback can be enabled or disabled here or in the lambda
        return _binding.root
    }

    // [START auth_fui_result]
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == RC_SIGN_IN) {
//            val response = IdpResponse.fromResultIntent(data)
//
//            if (resultCode == Activity.RESULT_OK) {
//                // Successfully signed in
//                val user = FirebaseAuth.getInstance().currentUser
//                findNavController().navigate(R.id.action_welcome_to_homeScreenFragment)
//                // ...
//            } else {
//                // Sign in failed. If response is null the user canceled the
//                // sign-in flow using the back button. Otherwise check
//                // response.getError().getErrorCode() and handle the error.
//                // ...
//            }
//        }
//    }
    // [END auth_fui_result]

    companion object {

        private const val RC_SIGN_IN = 123
    }


}