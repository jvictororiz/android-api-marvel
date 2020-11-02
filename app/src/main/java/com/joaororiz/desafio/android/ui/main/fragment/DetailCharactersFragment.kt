package com.joaororiz.desafio.android.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.joaororiz.desafio.android.R
import com.joaororiz.desafio.android.databinding.FragmentDetailedCharactersBinding
import com.joaororiz.desafio.android.extension.getFantasticUrl
import com.joaororiz.desafio.android.extension.setImageFromUrl
import com.joaororiz.desafio.android.ui.adapter.ComicListAdapter
import com.joaororiz.desafio.android.viewModel.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_detailed_characters.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class DetailCharactersFragment : Fragment() {
    private lateinit var binding: FragmentDetailedCharactersBinding


    private val viewModel by sharedViewModel<MainViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailedCharactersBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.fragment = this
        binding.lifecycleOwner = this
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.findLocalComicsByCharacter()
        configViews()
        setupObservers()
    }

    private fun configViews() {
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
        viewModel.getSelectedCharacter()?.run {
            img_background.setImageFromUrl(this.thumbnail?.getFantasticUrl(), R.drawable.ic_round_account_circle)
        }
    }

    private fun setupObservers() {
        viewModel.load.observe(viewLifecycleOwner, Observer {
            binding.load.visibility = if (it) View.VISIBLE else View.GONE

        })
        viewModel.listAllComics.observe(viewLifecycleOwner, Observer { listComics ->
            binding.tvError.text = ""
            binding.rvComics.adapter = ComicListAdapter(listComics)

        })
        viewModel.error.observe(viewLifecycleOwner, Observer {
            tv_error.text = it
            tv_error.isVisible = true
        })
    }

}