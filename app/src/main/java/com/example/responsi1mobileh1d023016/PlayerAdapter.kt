package com.example.responsi1mobileh1d023016

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class PlayerAdapter(
    private var players: List<PlayerData>,
    private val onPlayerClicked: (PlayerData) -> Unit
) : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    // 1. Membuat ViewHolder (membuat 'kerangka' layout dari XML)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_player, parent, false)
        return PlayerViewHolder(view)
    }

    // 2. Menghubungkan data dengan ViewHolder (mengisi data & memberi warna)
    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = players[position]
        holder.bind(player)
    }

    // 3. Memberi tahu RecyclerView ada berapa total item
    override fun getItemCount(): Int = players.size

    // Fungsi untuk meng-update data di adapter
    fun updateData(newPlayers: List<PlayerData>) {
        players = newPlayers
        notifyDataSetChanged() // Memberi tahu adapter datanya berubah
    }

    // --- Inner Class ViewHolder ---
    // Kelas  referensi ke view di dalam item_player.xml

    inner class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvPlayerName: TextView = itemView.findViewById(R.id.tv_player_name)
        private val tvPlayerNationality: TextView = itemView.findViewById(R.id.tv_player_nationality)
        private val cardPlayer: MaterialCardView = itemView.findViewById(R.id.card_player)

        fun bind(player: PlayerData) {
            tvPlayerName.text = player.name
            tvPlayerNationality.text = player.nationality

            val color = when (player.position) {
                // GOALKEEPER
                "Goalkeeper" -> "#FFFF00" // Kuning

                // DEFENDER (semua jenis bek)
                "Defence", "Left-Back", "Centre-Back", "Right-Back" -> "#0000FF" // Biru

                // MIDFIELDER (semua jenis gelandang)
                "Midfield", "Central Midfield", "Defensive Midfield", "Attacking Midfield" -> "#008000" // Hijau

                // FORWARD (semua jenis penyerang)
                "Offence", "Right Winger", "Centre-Forward", "Left Winger" -> "#FF0000" // Merah

                // Default
                else -> "#808080" // Abu-abu
            }
            // Atur warna latar belakang kartu
            cardPlayer.setCardBackgroundColor(Color.parseColor(color))

            // Atur warna teks agar kontras
            if (player.position == "Goalkeeper") {
                tvPlayerName.setTextColor(Color.BLACK)
                tvPlayerNationality.setTextColor(Color.BLACK)
            } else {
                tvPlayerName.setTextColor(Color.WHITE)
                tvPlayerNationality.setTextColor(Color.WHITE)
            }

            // Menambahkan aksi klik pada seluruh kartu
            itemView.setOnClickListener {
                onPlayerClicked(player)
            }
        }
    }
}