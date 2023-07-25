package com.mobilenik.storibank.Views.MoveDetailFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mobilenik.storibank.Data.Model.Moves
import com.mobilenik.storibank.databinding.FragmentMoveDetailBinding

class MoveDetailFragment : Fragment() {

    private lateinit var binding:FragmentMoveDetailBinding
    private lateinit var move:Moves


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMoveDetailBinding.inflate(layoutInflater)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            arguments.let { bundle ->
                move = bundle!!.getParcelable("Move")!!

            }

        initViews()
    }

    private fun initViews() {
        binding.tvAmountMoveDetail.text = "$"+move.Monto
        binding.tvStateOrderDetail.text = if (move.Estado.equals("Enviado")) {
            "Tu transacción fue ${move.Estado} a ${move.Nombre}"
        } else {
            "La transacción fue ${move.Estado} de ${move.Nombre}"
        }



    }
}