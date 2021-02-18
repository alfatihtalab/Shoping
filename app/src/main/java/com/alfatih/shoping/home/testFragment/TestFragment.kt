package com.alfatih.shoping.home.testFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.alfatih.shoping.R
import com.alfatih.shoping.databinding.FragmentTestBinding


class TestFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test, container, false)
    }
}

class TestTwoFragment: Fragment(){
    private lateinit var textView: TextView
    private lateinit var testBinding: FragmentTestBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        testBinding = FragmentTestBinding.inflate(inflater,container,false)

        testBinding.demoFragmentText.text = "Fragment 2"
        return testBinding.root
    }
}