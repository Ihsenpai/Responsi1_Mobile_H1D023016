package com.example.responsi1mobileh1d023016

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TeamViewModel : ViewModel() {

    private val API_TOKEN = "160067ae8b914b51b2830907db6d323d"

    private val _teamData = MutableLiveData<TeamResponse?>()
    val teamData: LiveData<TeamResponse?> = _teamData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    init {
        fetchTeamData()
    }

    fun fetchTeamData() {
        _isLoading.value = true

        // Menjalankan di background thread
        viewModelScope.launch {
            try {
                val response = ApiClient.apiService.getTeamDetails(
                    teamId = 70,
                    apiToken = API_TOKEN
                )

                if (response.isSuccessful) {
                    // Jika sukses, simpan data ke LiveData
                    _teamData.postValue(response.body())
                } else {
                    // Jika gagal (misal: token salah, server error)
                    _errorMessage.postValue("Gagal memuat data: ${response.message()}")
                }
            } catch (e: Exception) {
                // Jika tidak ada koneksi internet atau error lain
                _errorMessage.postValue("Error: ${e.message}")
            } finally {
                _isLoading.value = false // Selesai loading
            }
        }
    }
}