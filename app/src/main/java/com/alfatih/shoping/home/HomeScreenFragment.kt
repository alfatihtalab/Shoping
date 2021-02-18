package com.alfatih.shoping.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.addCallback
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder
import androidx.viewpager2.widget.ViewPager2
import com.alfatih.shoping.LauncherActivity
import com.alfatih.shoping.R
import com.alfatih.shoping.databinding.HomeScreenFragmentBinding
import com.alfatih.shoping.home.testFragment.TestFragment
import com.alfatih.shoping.home.testFragment.TestTwoFragment
import com.google.android.material.tabs.TabLayoutMediator


class HomeScreenFragment : Fragment() {

    companion object {
        fun newInstance() = HomeScreenFragment()
    }
    // When requested, this adapter returns a DemoObjectFragment,
    // representing an object in the collection.
    private lateinit var demoCollectionAdapter: DemoAdapter
    private lateinit var viewPager: ViewPager2

    private lateinit var viewModel: HomeScreenViewModel
    private lateinit var binding : HomeScreenFragmentBinding
    //val authActivity = AuthActivity()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ):View? {
        binding = HomeScreenFragmentBinding.inflate(inflater,container,false)


        //This callback will only be called when MyFragment is at least Started.
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            //LauncherActivity().finish()
            // Handle the back button event
            requireActivity().finish()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val fm_list = listOf<Fragment>(TestFragment(),TestTwoFragment())
        demoCollectionAdapter = DemoAdapter(fm_list,this)
        viewPager = binding.pager
        viewPager.adapter = demoCollectionAdapter
        val tabLayout = binding.tabLayout
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = "OBJECT ${(position + 1)}"
        }.attach()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeScreenViewModel::class.java)
        // TODO: Use the ViewModel
    }


}

class DemoAdapter(val fmList:List<Fragment>, fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = fmList.size
    override fun createFragment(position: Int): Fragment {
        val fm = fmList.get(position)
        return fm
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }


    override fun onBindViewHolder(
        holder: FragmentViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        super.onBindViewHolder(holder, position, payloads)
    }
}