package com.alfatih.shoping.home.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.alfatih.shoping.R
import com.alfatih.shoping.databinding.SettingFragmentBinding
import com.google.firebase.auth.FirebaseAuth

class SettingFragment : Fragment() {
    val currentUser = FirebaseAuth.getInstance().currentUser


    companion object {
        fun newInstance() = SettingFragment()
    }
    private lateinit var viewModel: SettingViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding = SettingFragmentBinding.inflate(inflater, container, false)
        //This callback will only be called when MyFragment is at least Started.
        //This callback will only be called when MyFragment is at least Started.
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            //LauncherActivity().finish()
            // Handle the back button event
            findNavController().navigate(R.id.homeScreenFragment)
        }
        //val args= WelcomeArgs.fromBundle(requireArguments())
        viewModel = ViewModelProvider(this).get(SettingViewModel::class.java)

        binding.viewModel = viewModel

        binding.setLifecycleOwner(this)

        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }


}