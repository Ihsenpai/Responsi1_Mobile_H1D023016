package com.example.responsi1mobileh1d023016

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PlayerDetailDialogFragment : BottomSheetDialogFragment() {

    // Menerima argumen (data) yang dikirim dari SquadFragment
    private val args: PlayerDetailDialogFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Menghubungkan ke layout dialog
        return inflater.inflate(R.layout.fragment_player_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Cari semua TextView di layout dialog
        val tvName: TextView = view.findViewById(R.id.tv_detail_name)
        val tvDob: TextView = view.findViewById(R.id.tv_detail_dob)
        val tvNationality: TextView = view.findViewById(R.id.tv_detail_nationality)
        val tvPosition: TextView = view.findViewById(R.id.tv_detail_position)

        // Tampilkan data yang diterima ke TextView
        tvName.text = args.playerName
        tvDob.text = args.playerDob
        tvNationality.text = args.playerNationality
        tvPosition.text = args.playerPosition
    }
}