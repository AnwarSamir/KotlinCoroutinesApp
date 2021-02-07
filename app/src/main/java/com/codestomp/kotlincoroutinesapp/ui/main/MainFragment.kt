package com.codestomp.kotlincoroutinesapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

    val myScope = CoroutineScope(Dispatchers.Main + CoroutineName("AnwarScope"))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        myScope.launch {
            binding.progressCircular.visibility=View.VISIBLE
            val response=makeApiCall()
            if (response.isSuccessful)
            {
                binding.progressCircular.visibility=View.GONE
                buildRecipeRV(response.body()!!.data)
            }else{
                binding.progressCircular.visibility=View.GONE
            }

        }

        binding.message.setOnClickListener {
            Toast.makeText(requireActivity(),"this",Toast.LENGTH_LONG).show()
        }

    }

    suspend fun makeApiCall(): Response<RecipesResponse> {

        return withContext(Dispatchers.IO) {
            ApiInterface().getRecipes()
        }

    }





    private fun loadRecipesDataCall() {


        binding.progressCircular.visibility = View.VISIBLE
        ApiInterface().getRecipesCall().enqueue(object : Callback<RecipesResponse> {
            override fun onResponse(
                call: Call<RecipesResponse>,
                response: Response<RecipesResponse>
            ) {
                binding.progressCircular.visibility = View.GONE
                buildRecipeRV(response.body()!!.data)
            }

            override fun onFailure(call: Call<RecipesResponse>, t: Throwable) {
                binding.progressCircular.visibility = View.GONE
            }

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


    fun doHeavyTask() {
        for (i in 1..100000000000000) {
            val x = i * 22323
            val r = i * 22323
            val c = i * 22323
            val z = i * 22323
            val t = i * 22323


        }
    }

    override fun onDestroy() {
        super.onDestroy()

    }
}