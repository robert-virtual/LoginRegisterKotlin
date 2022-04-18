package com.example.loginregister

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.loginregister.databinding.FragmentLoginBinding
import com.example.loginregister.databinding.FragmentRegsiterBinding


class RegsiterFragment : Fragment() {
    private var _binding: FragmentRegsiterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_regsiter, container, false)
        _binding = FragmentRegsiterBinding.inflate(inflater,container,false)

        val view = binding.root
        binding.linkIniciarSession.setOnClickListener {
            findNavController().navigateUp()
        }
        return view
    }


}