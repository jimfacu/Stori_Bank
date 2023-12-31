package com.mobilenik.storibank.Views.RegisterFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.mobilenik.storibank.BaseFragment
import com.mobilenik.storibank.Common.Constants
import com.mobilenik.storibank.Data.Model.UserRegister
import com.mobilenik.storibank.R
import com.mobilenik.storibank.Utils.DialogManager
import com.mobilenik.storibank.Utils.EventObserver
import com.mobilenik.storibank.databinding.FragmentStep1RegisterBinding

class Step1RegisterFragment : BaseFragment() {

    private lateinit var binding: FragmentStep1RegisterBinding

   private val viewModel:RegisterViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentStep1RegisterBinding.inflate(layoutInflater)
        return  binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initviews()
        setOberservers()
    }

    private fun setOberservers() {
        viewModel.userInformation.observe(viewLifecycleOwner,EventObserver{
            finishCallService()
            showMessage("Usuario registrado con exito!. Continue con los siguientes pasos"
                ,object : DialogManager.IListener {
                override fun onClick() {
                    findNavController().navigate(R.id.action_step1RegisterFragment_to_step2RegisterFragment)
                }
            })

        })


        viewModel.error.observe(viewLifecycleOwner,EventObserver{ error ->
            finishCallService()
            showMessage(error)
        })
    }

    private fun initviews() {
        binding.btnContinue.setOnClickListener{
            initializeCallService()

            val body = UserRegister(binding.etName.text.toString(),
                binding.etLastName.text.toString(),binding.etEmail.text.toString(),
            binding.etPassword.text.toString(),Constants.FIREBASE_FIELD_BALANCE_VALUE)
            viewModel.registerUser(body)
        }
    }

    private fun initializeCallService() {
        showProgress()
        binding.btnContinue.isEnabled = false
        binding.btnContinue.setCardBackgroundColor( ContextCompat.getColor(requireContext(), R.color.greyStori))
    }

    private fun finishCallService(){
        hideProgress()
        binding.btnContinue.isEnabled = true
        binding.btnContinue.setCardBackgroundColor( ContextCompat.getColor(requireContext(), R.color.colorStoriBank))
    }
}