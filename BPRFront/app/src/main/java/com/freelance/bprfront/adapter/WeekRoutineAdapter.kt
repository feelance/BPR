package com.freelance.bprfront.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.freelance.bprfront.R
import com.freelance.bprfront.model.WeekRoutine

class WeekRoutineAdapter(private var weekRoutineList: List<WeekRoutine>) : RecyclerView.Adapter<WeekRoutineAdapter.WeekRoutineViewHolder>() {

    class WeekRoutineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.tvRoutineName)
        val notesTextView: TextView = itemView.findViewById(R.id.tvRoutineNotes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekRoutineViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.week_routine_item, parent, false)
        return WeekRoutineViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeekRoutineViewHolder, position: Int) {
        val weekRoutine = weekRoutineList[position]
        holder.nameTextView.text = weekRoutine.name
        holder.notesTextView.text = weekRoutine.notes
    }

    fun updateData(newItems: List<WeekRoutine>) {
        weekRoutineList = newItems
        notifyDataSetChanged() // Notificar al adaptador que los datos han cambiado
    }

    override fun getItemCount() = weekRoutineList.size
}
