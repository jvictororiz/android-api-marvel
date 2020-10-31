package com.joaororiz.desafio.android.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.joaororiz.desafio.android.R
import com.joaororiz.desafio.android.databinding.FragmentCharacterListBinding
import com.joaororiz.desafio.android.extension.listenerEnd
import com.joaororiz.desafio.android.ui.adapter.CharacterListAdapter
import com.joaororiz.desafio.android.viewModel.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ListComicsFragment : Fragment() {
    private lateinit var binding: FragmentCharacterListBinding
    private val adapter by lazy {
        CharacterListAdapter()
    }

    private val viewModel by sharedViewModel<MainViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.fragment = this
        binding.lifecycleOwner = this
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupObservers()
        setupListeners()
    }


    private fun setupObservers() {
        viewModel.listAllCharacter.observe(viewLifecycleOwner, Observer {
            binding.tvError.isVisible = false
            adapter.updateList(it)
        })

        viewModel.error.observe(viewLifecycleOwner, Observer {
            binding.tvError.isVisible = true
        })

        viewModel.loadSwipe.observe(viewLifecycleOwner, Observer {
            binding.swipe.isRefreshing = it
        })
        viewModel.load.observe(viewLifecycleOwner, Observer {
            binding.progress.isVisible = it

        })
        viewModel.resetListCharacter.observe(viewLifecycleOwner, Observer {
            adapter.clearList()
        })


    }


    private fun setupListeners() {
        binding.rvCharacters.listenerEnd {
            viewModel.nextListCharacter()
        }

        binding.swipe.setOnRefreshListener {
            viewModel.listAllCharacter()
        }

        binding.tvError.setOnClickListener {
            viewModel.refreshListCharacter()
        }

        adapter.eventClick = {
            viewModel.selectCharacter(it)
            findNavController().navigate(R.id.action_listComicsFragment_to_detailCharactersFragment)
        }

        binding.rvCharacters.adapter = adapter

    }

}