package com.mobilenik.storibank.Views.RegisterFragments

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.mobilenik.storibank.R
import com.mobilenik.storibank.Utils.EventObserver
import com.mobilenik.storibank.Utils.UtilsInterface
import com.mobilenik.storibank.databinding.FragmentStep2RegisterBinding


class Step2RegisterFragment : Fragment() {

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
            //Mostramos un mensaje de exito al guardar la foto del usuario
            findNavController().navigate(R.id.action_step2RegisterFragment_to_step3RegisterFragment)
        })
    }

    private fun initViews() {
        binding.btnTakePicture.setOnClickListener {
            takePicture.takePicture()
        }

    }

    fun reciveImageUrl(imageUrl:Uri){
        viewModel.savePictureUser(imageUrl)
    }
}