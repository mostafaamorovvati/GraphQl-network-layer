package com.example.networklayer.presentation.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.networklayer.R
import com.example.networklayer.databinding.ActivityDetailsBinding
import com.example.networklayer.domain.models.SingleCharacterModel
import com.example.networklayer.utils.CHARACTER_EXTRA
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity : AppCompatActivity() {

    private val viewModel: DetailsViewModel by viewModel()
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getExtraCharacter()?.let {
            with(binding) {
                imgDetailsCharacter.load(it.image)
                tvDetailsCharacter.text =
                    getString(R.string.characters_episodes_appearance, it.name)
                rvEpisodes.adapter = EpisodesAdapter(it.episode)
            }
        }
    }

    private fun getExtraCharacter() =
        intent?.extras?.getParcelable(CHARACTER_EXTRA) as SingleCharacterModel?
}