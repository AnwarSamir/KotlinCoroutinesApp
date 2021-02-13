package com.codestomp.kotlincoroutinesapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.codestomp.kotlincoroutinesapp.R
import com.codestomp.kotlincoroutinesapp.data.models.Data
import com.codestomp.kotlincoroutinesapp.data.models.RecipesResponse
import com.codestomp.kotlincoroutinesapp.data.network.ApiInterface
import com.codestomp.kotlincoroutinesapp.databinding.MainFragmentBinding
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainFragment : Fragment() {
    private lateinit var binding: MainFragmentBinding

    companion object {
        fun newInstance() = MainFragment()
    }




    lateinit var mainViewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mainViewModel=ViewModelProvider(this).get(MainViewModel::class.java)
        getData()
    }

    private fun getData() {
        binding.progressCircular.visibility=View.VISIBLE
        mainViewModel.recipes!!.observe(viewLifecycleOwner, Observer {recipesData->
            binding.progressCircular.visibility=View.GONE
            buildRecipeRV(recipesData.data)
        })
    }


    private fun buildRecipeRV(data: ArrayList<Data>) {
        val recipesAdapter = RecipesAdapter(data, object : RecipesAdapter.OnItemCLicked {
            override fun onClick(pos: Int) {
                TODO("Not yet implemented")
            }
        })

        binding.rvRecipes.adapter = recipesAdapter
    }

    override fun onDestroy() {
        super.onDestroy()

    }
}