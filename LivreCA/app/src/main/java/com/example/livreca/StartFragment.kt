package com.example.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.livreca.R


class StartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newHereButton: Button = view.findViewById(R.id.btn_new_here)
        val alreadyHaveAccountButton: Button = view.findViewById(R.id.btn_already_have_account)

        newHereButton.setOnClickListener {
            // Handle click event for "Sunt nou aici"
        }

        alreadyHaveAccountButton.setOnClickListener {
            // Handle click event for "Am deja un cont"
        }
    }
}
