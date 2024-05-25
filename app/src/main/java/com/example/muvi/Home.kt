package com.example.muvi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.muvi.databinding.ActivityMainBinding
import com.example.muvi.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

class Home : Fragment() {

   // private lateinit var homeViewModel: HomeFragmentViewModel
    private lateinit var binding : FragmentHomeBinding
    private var myContext: FragmentActivity? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
     //   return inflater.inflate(R.layout.fragment_home, container, false)
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding = FragmentHomeBinding.inflate(layoutInflater)
       // setContentView(binding.root)

      //  homeViewModel = ViewModelProvider(this).get(HomeFragmentViewModel::class.java)
      //  val root = inflater.inflate(R.layout.fragment_home, container, false)
        myContext=activity

        binding.viewPager.isSaveEnabled=false

        val adapter =ViewPagerAdapter(childFragmentManager,lifecycle)
        adapter.addFragment(HomeTabOne(), "All")
        adapter.addFragment(HomeTabTwo(), "Hindi")
        adapter.addFragment(HomeTabThree(), "English")

        adapter.notifyDataSetChanged()
        binding.viewPager.adapter =adapter
        TabLayoutMediator( binding.tabs,  binding.viewPager) { tab, position ->
            tab.text = adapter.getPageTitle(position)
            binding.viewPager.setCurrentItem(tab.position, true)
        }.attach()

        return binding.root

    }


}