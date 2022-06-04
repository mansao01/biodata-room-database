package com.mansao.biodataroomdatabase.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mansao.biodataroomdatabase.databinding.ActivityMainBinding
import com.mansao.biodataroomdatabase.repository.ProfileAdapter
import com.mansao.biodataroomdatabase.viewmodel.MainViewModel
import com.mansao.biodataroomdatabase.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ProfileAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainViewModel = obtainViewModelFactory(this)
        mainViewModel.getAllData()
            .observe(this) { profileList ->
                adapter.setListProfile(profileList)
                if (profileList.isNotEmpty()){
                    binding.tvNoData.visibility = View.INVISIBLE
                }else{
                    binding.tvNoData.visibility  = View.VISIBLE
                }
            }

        adapter = ProfileAdapter()

        binding.apply {
            rvProfile.layoutManager = LinearLayoutManager(this@MainActivity)
            rvProfile.setHasFixedSize(true)
            rvProfile.adapter = adapter


            fabAdd.setOnClickListener {
                val intent = Intent(this@MainActivity, ProfileAddUpdateActivity::class.java)
                startActivity(intent)
            }
        }

    }

    private fun obtainViewModelFactory(activity: AppCompatActivity): MainViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[MainViewModel::class.java]
    }
}