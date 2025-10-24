package com.example.responsi1mobileh1d023016

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.card.MaterialCardView

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Cari tombol-tombol (card) berdasarkan ID-nya di XML
        val cardHistory: MaterialCardView = view.findViewById(R.id.card_history)
        val cardCoach: MaterialCardView = view.findViewById(R.id.card_coach)
        val cardSquad: MaterialCardView = view.findViewById(R.id.card_squad)

        // Beri aksi klik untuk tombol Sejarah
        cardHistory.setOnClickListener {
            // Pindah ke halaman Sejarah
            findNavController().navigate(R.id.action_homeFragment_to_historyFragment)
        }

        // Beri aksi klik untuk tombol Pelatih
        cardCoach.setOnClickListener {
            // Pindah ke halaman Pelatih
            findNavController().navigate(R.id.action_homeFragment_to_coachFragment)
        }

        // Beri aksi klik untuk tombol Skuad
        cardSquad.setOnClickListener {
            // Pindah ke halaman Skuad
            findNavController().navigate(R.id.action_homeFragment_to_squadFragment)
        }
    }
}