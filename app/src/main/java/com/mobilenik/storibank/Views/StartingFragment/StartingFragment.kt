package com.mobilenik.storibank.Views.StartingFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.mobilenik.storibank.BaseFragment
import com.mobilenik.storibank.Data.Model.UserLogin
import com.mobilenik.storibank.R
import com.mobilenik.storibank.Utils.EventObserver
import com.mobilenik.storibank.databinding.FragmentStartingBinding

class StartingFragment : BaseFragment() {

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

        setObservers()
    }

    private fun setObservers() {
        viewModel.userInformation.observe(viewLifecycleOwner,EventObserver{
            findNavController().navigate(R.id.action_startingFragment_to_homeFragment)
        })


        viewModel.error.observe(viewLifecycleOwner,EventObserver{
            hideProgress()
            showMessage(it)
            })
        }


    private fun initViews() {

        binding.btnLogin.setOnClickListener {
            showProgress()
            viewModel.loginUser(UserLogin(binding.etemail.text.toString(),binding.etPassword.text.toString()))
        }

        binding.btnRegister.setOnClickListener{
            findNavController().navigate(R.id.action_startingFragment_to_step1RegisterFragment)
        }
    }

}