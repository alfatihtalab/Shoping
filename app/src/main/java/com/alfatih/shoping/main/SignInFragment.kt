package com.alfatih.shoping.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.alfatih.shoping.R
import com.alfatih.shoping.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {

    private lateinit var bindingSignInBinding: FragmentSignInBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingSignInBinding = FragmentSignInBinding.inflate(inflater,container, false)

        return bindingSignInBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}