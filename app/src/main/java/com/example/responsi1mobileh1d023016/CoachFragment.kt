package com.example.responsi1mobileh1d023016

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels


class CoachFragment : Fragment() {

    private val viewModel: TeamViewModel by activityViewModels()

    private lateinit var tvName: TextView
    private lateinit var tvDob: TextView
    private lateinit var tvNationality: TextView
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_coach, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Hubungkan variabel Kotlin dengan ID di XML, btw comment semua code sengaja biar msal saya buka code ga lupa fungsinya
        tvName = view.findViewById(R.id.tv_coach_name)
        tvDob = view.findViewById(R.id.tv_coach_dob)
        tvNationality = view.findViewById(R.id.tv_coach_nationality)
        progressBar = view.findViewById(R.id.progress_bar_coach)

        observeViewModel()
    }

    private fun observeViewModel() {
        // Mengamati status loading
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        // Mengamati data pelatih
        viewModel.teamData.observe(viewLifecycleOwner) { teamResponse ->
            if (teamResponse != null) {
                val coach = teamResponse.coach
                if (coach != null) {
                    tvName.text = coach.name
                    tvDob.text = coach.dateOfBirth
                    tvNationality.text = coach.nationality
                } else {
                    // Jika data coach null
                    tvName.text = "Data pelatih tidak tersedia"
                }
            }
        }

        // Mengamati pesan error
        viewModel.errorMessage.observe(viewLifecycleOwner) { error ->
            if (error.isNotEmpty()) {
                Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
            }
        }
    }
}