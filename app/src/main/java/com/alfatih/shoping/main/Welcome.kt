package com.alfatih.shoping.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alfatih.shoping.R
import com.alfatih.shoping.auth.UserFirebase
import com.alfatih.shoping.databinding.FragmentWelcomeBinding
import com.google.android.material.tabs.TabLayoutMediator


class Welcome : Fragment() {
    private lateinit var _binding: FragmentWelcomeBinding

    //private val firebaseAuth = FirebaseAuth(FirebaseApp.getInstance())
    private val userFirebase = UserFirebase()

    private val images =
        arrayOf(R.drawable.doodles, R.drawable.doodlesgroovy, R.drawable.doodleszombieing)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        //get args from accountTypeFragment


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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

    companion object {

        private const val RC_SIGN_IN = 123
    }


}