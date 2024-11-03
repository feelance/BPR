package com.freelance.bprfront.ui.home

import android.os.Bundle
import android.provider.ContactsContract.Data
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.freelance.bprfront.DataSource
import com.freelance.bprfront.R
import com.freelance.bprfront.adapter.WeekRoutineAdapter
import com.freelance.bprfront.databinding.FragmentHomeBinding
import com.freelance.bprfront.model.WeekRoutine

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null;
    private lateinit var weekRoutineAdapter : WeekRoutineAdapter;
    private val binding get() = _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding=FragmentHomeBinding.bind(view)
        weekRoutineAdapter = WeekRoutineAdapter(DataSource.createDataSet())
        binding.recyclerView.layoutManager= LinearLayoutManager(requireContext())
        binding.recyclerView.adapter=weekRoutineAdapter
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadItems() {
        weekRoutineAdapter.updateData(DataSource.createDataSet())
    }

    override fun onResume() {
        super.onResume()
        loadItems()

    }

}