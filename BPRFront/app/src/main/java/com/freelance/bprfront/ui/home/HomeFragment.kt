package com.freelance.bprfront.ui.home

import android.os.Bundle
import android.provider.ContactsContract.Data
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.freelance.bprfront.DataSource
import com.freelance.bprfront.R
import com.freelance.bprfront.adapter.WeekRoutineAdapter
import com.freelance.bprfront.databinding.FragmentHomeBinding
import com.freelance.bprfront.model.WeekRoutine
import com.freelance.bprfront.services.viewmodel.WeekRoutinesVM

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null;
    private lateinit var weekRoutineAdapter: WeekRoutineAdapter;
    private val binding get() = _binding!!

    private lateinit var viewModel: WeekRoutinesVM
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[WeekRoutinesVM::class.java]

        _binding = FragmentHomeBinding.bind(view)
        val  routines: WeekRoutine? = getAllRoutines()
        val allRoutines:List<WeekRoutine> = ArrayList()
        weekRoutineAdapter = WeekRoutineAdapter(allRoutines)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = weekRoutineAdapter
        //viewModel.fetchAllRoutines()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadItems() {
        val  routines: WeekRoutine? = getAllRoutines()
        val allRoutines:List<WeekRoutine> = ArrayList()

        if ( allRoutines != null) {
            weekRoutineAdapter.updateData(allRoutines)
        }
    }

    override fun onResume() {
        super.onResume()
        loadItems()

    }

    private fun getAllRoutines(): WeekRoutine? {
        var weekRoutine: WeekRoutine? = null
        viewModel.routines.observe(viewLifecycleOwner, Observer { routines ->
            // Actualiza la UI con los posts
            weekRoutine = routines
        })
        return weekRoutine
    }

}