package com.example.networklayer.presentation.location

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.networklayer.data.datasource.remote.commun.onError
import com.example.networklayer.data.datasource.remote.commun.onLoading
import com.example.networklayer.data.datasource.remote.commun.onSuccess
import com.example.networklayer.databinding.ActivityLocationBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LocationActivity : AppCompatActivity() {

    private val viwModel: LocationViewModel by viewModel()
    private lateinit var binding: ActivityLocationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initObserver()
        viwModel.getListLocations(1)
    }

    private fun initObserver() {
        viwModel.resultListCharacters.observe(this, {
            it.onSuccess { list ->
                for (i in list.results ?: emptyList()) {
                    Log.d("LOG_TAG", "onSuccess: ${i?.name}")
                }
            }.onError { error ->
                Log.d("LOG_TAG", "onError: ${error.message}")
            }.onLoading {
                Log.d("LOG_TAG", "onLoading")
            }
        })
    }
}