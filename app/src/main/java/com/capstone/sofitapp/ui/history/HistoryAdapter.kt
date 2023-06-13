package com.capstone.sofitapp.ui.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.capstone.sofitapp.R

class HistoryAdapter(private val historyDataList: MutableList<String>, private val listener: OnHistoryItemClickListener):
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>(){

    interface OnHistoryItemClickListener {
        fun onHistoryItemClick(historyData: String)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTanggalTest: TextView = itemView.findViewById(R.id.tv_tanggal_test)
        val tvWaktuTest: TextView = itemView.findViewById(R.id.tv_waktu_test)
        val tvHasilTest: TextView = itemView.findViewById(R.id.tv_hasil_test)
        val btnHapusHistory: ImageView = itemView.findViewById(R.id.tv_hapus)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_card_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val historyData = historyDataList[position]
        val splitData = historyData.split(", ")

        holder.tvTanggalTest.text = splitData[0]
        holder.tvWaktuTest.text = splitData[1]
        holder.tvHasilTest.text = splitData[2]

        holder.btnHapusHistory.setOnClickListener {
            listener.onHistoryItemClick(historyData)
        }
    }

    override fun getItemCount(): Int {
        return historyDataList.size
    }
}