package com.example.networklayer.presentation.characters

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.apollographql.apollo.api.Error
import com.example.networklayer.data.commun.onError
import com.example.networklayer.data.commun.onLoading
import com.example.networklayer.data.commun.onSuccess
import com.example.networklayer.databinding.ActivityCharactersBinding
import com.example.networklayer.domain.models.CharactersModel
import com.example.networklayer.domain.models.SingleCharacterModel
import com.example.networklayer.presentation.details.DetailsActivity
import com.example.networklayer.utils.CHARACTER_EXTRA
import com.example.networklayer.utils.hide
import com.example.networklayer.utils.show
import com.example.networklayer.utils.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersActivity : AppCompatActivity() {

    private val viewModel: CharactersViewModel by viewModel()
    private lateinit var binding: ActivityCharactersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initObserver()
        viewModel.getListCharacters(1)
    }

    private fun initObserver() {
        viewModel.resultListCharacters.observe(this) {
            it.onSuccess { list ->
                binding.progressCircular.hide()
                setListCharacters(list)
            }.onError { error ->
                binding.progressCircular.hide()
                when (error.messageResource) {
                    is Int -> toast(getString(error.messageResource))
                    is Error? -> {
                        error.messageResource?.let { errorMessage -> toast(errorMessage.message) }
                    }
                }
            }.onLoading {
                binding.progressCircular.show()
            }
        }
    }

    private fun setListCharacters(list: CharactersModel) {
        with(binding.rvRickAndMorty) {
            adapter = CharactersAdapter(list.results) { goToDetailsActivity(it) }
        }
    }

    private fun goToDetailsActivity(singleCharacterModel: SingleCharacterModel) {
        Intent(this, DetailsActivity::class.java).apply {
            putExtra(CHARACTER_EXTRA, singleCharacterModel)
            startActivity(this)
        }
    }
}