package com.vincent.projectuts_anmp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vincent.projectuts_anmp.databinding.FragmentDashboardBinding
import com.vincent.projectuts_anmp.model.Habit
import com.vincent.projectuts_anmp.viewmodel.HabitViewModel

class DashboardFragment : Fragment(), HabitItemListener {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HabitViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[HabitViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = HabitListAdapter(this)

        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = adapter

        viewModel.habits.observe(viewLifecycleOwner) { habits ->
            adapter.submitList(habits)
        }

        binding.fabAdd.setOnClickListener {
            val action = DashboardFragmentDirections.actionDashboardFragmentToCreateHabitFragment()
            findNavController().navigate(action)
        }
    }

    override fun onPlusClick(habit: Habit) {
        viewModel.updateProgress(habit, 1)
    }

    override fun onMinusClick(habit: Habit) {
        viewModel.updateProgress(habit, -1)
    }

    override fun onEditClick(habit: Habit) {
        val action = DashboardFragmentDirections.actionDashboardFragmentToEditHabitFragment(habit.id)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
