package com.mobilenik.storibank.Views.HomeFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobilenik.storibank.BaseFragment
import com.mobilenik.storibank.Common.Constants
import com.mobilenik.storibank.R
import com.mobilenik.storibank.Utils.DialogManager
import com.mobilenik.storibank.Utils.EventObserver
import com.mobilenik.storibank.databinding.FragmentHomeBinding


class HomeFragment : BaseFragment() {

    private lateinit var binding:FragmentHomeBinding

    private val viewModel: HomeViewModel by activityViewModels()

    private lateinit var adapter:MovesAdapters


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        setObservers()
    }

    private fun setObservers() {
        viewModel.userInformation.observe(viewLifecycleOwner,EventObserver{
            binding.tvNameUser.text = "Hola ${it.name}"
        })


        viewModel.moveList.observe(viewLifecycleOwner){ it ->
            if(it.isEmpty()){
                binding.rvMoves.visibility = View.GONE
                binding.tvEmptyList.visibility = View.VISIBLE
            }else{
                binding.rvMoves.layoutManager = LinearLayoutManager(activity)
                adapter = MovesAdapters(it)
                binding.rvMoves.adapter = adapter
                initAdapterClick()
            }

        }

        viewModel.error.observe(viewLifecycleOwner,EventObserver{
            showMessage(it,object : DialogManager.IListener {
                override fun onClick() {
                   requireActivity().finish()
                }
            })
        })
    }

    private fun initAdapterClick() {
        adapter.onItemClickListener = {
            val bundle = bundleOf(Constants.KEY_MOVES_BUNDLE to it)
            findNavController().navigate(R.id.action_homeFragment_to_moveDetailFragment,bundle)
        }
    }

    private fun initViews() {
        hideProgress()
    }
}