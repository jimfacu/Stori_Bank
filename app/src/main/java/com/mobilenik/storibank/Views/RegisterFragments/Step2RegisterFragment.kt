package com.mobilenik.storibank.Views.RegisterFragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.mobilenik.storibank.BaseFragment
import com.mobilenik.storibank.R
import com.mobilenik.storibank.Utils.DialogManager
import com.mobilenik.storibank.Utils.EventObserver
import com.mobilenik.storibank.Utils.UtilsInterface
import com.mobilenik.storibank.databinding.FragmentStep2RegisterBinding


class Step2RegisterFragment : BaseFragment() {

    private lateinit var binding:FragmentStep2RegisterBinding
    private lateinit var takePicture:UtilsInterface

    private val viewModel:RegisterViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentStep2RegisterBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        try {
            takePicture = activity as UtilsInterface
        } catch (e: ClassCastException) {
            throw ClassCastException(activity.toString() + " must implement TextClicked")
        }

        initViews()
        setObservers()
    }

    private fun setObservers() {
        viewModel.userPicture.observe(viewLifecycleOwner,EventObserver{
            finishCallService()
            showMessage(it, object : DialogManager.IListener {
                override fun onClick() {
                    findNavController().navigate(R.id.action_step2RegisterFragment_to_step3RegisterFragment)
                }
            })
        })

        viewModel.error.observe(viewLifecycleOwner,EventObserver{
            finishCallService()
            showMessage(it)
        })
    }

    private fun initViews() {
        binding.btnTakePicture.setOnClickListener {
            initializeCallService()
            takePicture.takePicture()
        }

    }

    fun reciveImageUrl(imageUrl:Uri){
        viewModel.savePictureUser(imageUrl)
    }

    private fun initializeCallService() {
        showProgress()
        binding.btnTakePicture.isEnabled = false
        binding.btnTakePicture.setCardBackgroundColor( ContextCompat.getColor(requireContext(), R.color.greyStori))
    }

    private fun finishCallService(){
        hideProgress()
        binding.btnTakePicture.isEnabled = true
        binding.btnTakePicture.setCardBackgroundColor( ContextCompat.getColor(requireContext(), R.color.colorStoriBank))
    }
}