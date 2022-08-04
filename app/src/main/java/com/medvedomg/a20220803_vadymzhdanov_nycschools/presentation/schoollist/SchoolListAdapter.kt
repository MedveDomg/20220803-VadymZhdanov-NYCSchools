package com.medvedomg.a20220803_vadymzhdanov_nycschools.presentation.schoollist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.medvedomg.a20220803_vadymzhdanov_nycschools.databinding.SchoolViewholderBinding

class SchoolListAdapter(val openDialog: (SatScoreModel) -> Unit) :
    RecyclerView.Adapter<SchoolListAdapter.SchoolViewHolder>() {

    private var itemsList: MutableList<SchoolModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolViewHolder {
        val binding = SchoolViewholderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SchoolViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SchoolViewHolder, position: Int) {
        holder.bind(itemsList[position])
    }

    override fun getItemCount(): Int = itemsList.size

    fun setData(list: List<SchoolModel>) {
        itemsList.clear()
        itemsList.addAll(list)
        notifyDataSetChanged()
    }

    inner class SchoolViewHolder(val binding: SchoolViewholderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(schoolModel: SchoolModel) {
            with(binding) {
                tvName.text = "Name:\n${schoolModel.name}"
                tvOverview.text = "Overview:\n${schoolModel.overview}"
                tvLocation.text = "Location:\n${schoolModel.location}"
                clRoot.setOnClickListener {
                    this@SchoolListAdapter.openDialog(schoolModel.satScoreModel)
                }
            }
        }
    }
}