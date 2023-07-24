package com.mobilenik.storibank.Views.StartingFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.mobilenik.storibank.R
import com.mobilenik.storibank.databinding.FragmentStartingBinding

class StartingFragment : Fragment() {

    private lateinit var binding:FragmentStartingBinding

    private val viewModel:StartingViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartingBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {

        binding.btnLogin.setOnClickListener {

        }

        binding.btnRegister.setOnClickListener{
            findNavController().navigate(R.id.action_startingFragment_to_step1RegisterFragment)

        }
    }
}