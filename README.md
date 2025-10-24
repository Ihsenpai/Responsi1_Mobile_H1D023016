# Responsi 1 Mobile (H1D023016)

## Identitas Praktikan

- **Nama:** [Fatihul Ihsan Al Ghoni]
- **NIM:** H1D023016
- **Shift Baru:** ["B"]
- **Shift Asal:** ["D"]

## Video Demo

[**Tonton Video Demo Aplikasi (Klik Di Sini)**](https://drive.google.com/file/d/1dhplgiGhJT9qJjOWvVUpcxA2tvhCePwz/view?usp=sharing)

## Penjelasan Alur Data

Alur data aplikasi ini dimulai saat sebuah Fragment (misal, `CoachFragment` atau `SquadFragment`) membutuhkan data. Fragment tersebut akan mengamati (observe) `LiveData` yang ada di dalam `TeamViewModel`.

1.  **ViewModel**: Saat `TeamViewModel` dibuat, fungsi `init`-nya memanggil `fetchTeamData()`.
2.  **API Call**: `fetchTeamData()` menggunakan *Coroutine* (`viewModelScope.launch`) untuk menjalankan tugas di background. Di dalamnya, ia memanggil `ApiClient.apiService.getTeamDetails(teamId = 70, apiToken = "TOKEN_API_KAMU")`.
3.  **Retrofit**: `ApiClient` (yang sudah dikonfigurasi dengan Retrofit dan Gson) menerjemahkan panggilan itu menjadi *request* HTTP GET ke `https://api.football-data.org/v4/teams/70` dengan *header* `X-Auth-Token`.
4.  **Data Response**: API mengembalikan data dalam format JSON.
5.  **Gson**: Library Gson secara otomatis mengubah (parse) JSON tersebut menjadi objek data Kotlin yang sudah kita buat (`TeamResponse`).
6.  **LiveData**: `ViewModel` menerima objek `TeamResponse` ini dan men-submit nilainya ke `_teamData` (sebuah `MutableLiveData`).
7.  **UI Update**: Karena Fragment sedang mengamati (observe) `teamData` (versi `LiveData` yang *public*), Fragment akan otomatis ter-notifikasi bahwa ada data baru. Fragment kemudian mengambil data tersebut (misal, `teamResponse.squad`) dan memberikannya ke `PlayerAdapter` untuk ditampilkan di `RecyclerView`.
