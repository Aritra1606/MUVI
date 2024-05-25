package com.example.muvi

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.muvi.adapter.MainAdapter
import com.example.muvi.databinding.FragmentHomeBinding
import com.example.muvi.databinding.FragmentHomeTab1Binding
import com.example.muvi.repository.MainRepository
import com.example.muvi.retrofit.RetrofitService
import com.example.muvi.viewmodel.MainViewModel
import com.example.muvi.viewmodel.MyViewModelFactory

class HomeTabOne : Fragment() {

    private val TAG = "HomeTabOne"

    private lateinit var binding : FragmentHomeTab1Binding
    private var myContext: FragmentActivity? = null

    lateinit var viewModel: MainViewModel

    private val retrofitService = RetrofitService.getInstance()
    val adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeTab1Binding.inflate(inflater, container, false)

        binding = FragmentHomeTab1Binding.inflate(layoutInflater)

        myContext=activity

        //
        viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(MainViewModel::class.java)
        binding.recyclerview.layoutManager = LinearLayoutManager(
            myContext,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        binding.recyclerview.adapter = adapter
        binding.recyclerview.isNestedScrollingEnabled = false


        binding.recyclerviewTwo.layoutManager = LinearLayoutManager(
            myContext,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        binding.recyclerviewTwo.adapter = adapter
        binding.recyclerviewTwo.isNestedScrollingEnabled = false


        binding.recyclerviewThree.layoutManager = LinearLayoutManager(
            myContext,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        binding.recyclerviewThree.adapter = adapter
        binding.recyclerviewThree.isNestedScrollingEnabled = false


        binding.recyclerviewFour.layoutManager = LinearLayoutManager(
            myContext,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        binding.recyclerviewFour.adapter = adapter
        binding.recyclerviewFour.isNestedScrollingEnabled = false

        viewModel.movieList.observe(requireActivity(), Observer {
            Log.d(TAG, "onCreate: $it")
            adapter.setMovieList(it)
        })

        viewModel.errorMessage.observe(requireActivity(), Observer {

        })
        viewModel.getAllMovies()
        //

        return binding.root
    }


}