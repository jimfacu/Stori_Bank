package com.mobilenik.storibank.Views.RegisterFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mobilenik.storibank.BaseFragment
import com.mobilenik.storibank.R
import com.mobilenik.storibank.databinding.FragmentStep3RegisterBinding


class Step3RegisterFragment : BaseFragment() {

    private lateinit var binding:FragmentStep3RegisterBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentStep3RegisterBinding.inflate(layoutInflater)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        binding.btnFinishRegister.setOnClickListener {
            findNavController().navigate(R.id.action_step3RegisterFragment_to_startingFragment)
        }
    }
}