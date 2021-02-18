package com.alfatih.shoping.home.setting

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.alfatih.shoping.LauncherActivity
import com.alfatih.shoping.R
import com.alfatih.shoping.databinding.SettingFragmentBinding

class SettingFragment : Fragment() {

    companion object {
        fun newInstance() = SettingFragment()
    }

    private lateinit var viewModel: SettingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding = SettingFragmentBinding.inflate(inflater,container,false)
        //This callback will only be called when MyFragment is at least Started.
        //This callback will only be called when MyFragment is at least Started.
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            //LauncherActivity().finish()
            // Handle the back button event
            findNavController().navigate(R.id.homeScreenFragment)
        }


        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SettingViewModel::class.java)


    }


}