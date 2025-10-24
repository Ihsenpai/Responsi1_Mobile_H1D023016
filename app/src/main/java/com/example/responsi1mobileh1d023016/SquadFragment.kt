package com.example.responsi1mobileh1d023016

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SquadFragment : Fragment() {

    private val viewModel: TeamViewModel by activityViewModels()
    private lateinit var playerAdapter: PlayerAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_squad, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = view.findViewById(R.id.progress_bar_squad)
        recyclerView = view.findViewById(R.id.recycler_view_squad)

        // Siapkan RecyclerView
        setupRecyclerView()

        observeViewModel()
    }

    private fun setupRecyclerView() {
        // Buat adapter-nya, berikan daftar kosong dulu
        // dan siapkan aksi klik-nya
        playerAdapter = PlayerAdapter(emptyList()) { player ->
            // Saat pemain diklik, panggil fungsi untuk menampilkan dialog
            showPlayerDetail(player)
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = playerAdapter
    }

    private fun observeViewModel() {
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.teamData.observe(viewLifecycleOwner) { teamResponse ->
            if (teamResponse != null && teamResponse.squad != null) {
                // Update data di adapter saat data dari API datang
                playerAdapter.updateData(teamResponse.squad)
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { error ->
            if (error.isNotEmpty()) {
                Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
            }
        }
    }

    // Fungsi pindah ke halaman/dialog detail
    private fun showPlayerDetail(player: PlayerData) {
        // Kita akan buat "action" ini di langkah terakhir
        val action = SquadFragmentDirections.actionSquadFragmentToPlayerDetailDialogFragment(
            playerName = player.name ?: "N/A",
            playerDob = player.dateOfBirth ?: "N/A",
            playerNationality = player.nationality ?: "N/A",
            playerPosition = player.position ?: "N/A"
        )
        findNavController().navigate(action)
    }
}