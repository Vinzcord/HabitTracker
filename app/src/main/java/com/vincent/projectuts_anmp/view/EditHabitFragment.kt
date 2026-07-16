package com.vincent.projectuts_anmp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.vincent.projectuts_anmp.databinding.FragmentEditHabitBinding
import com.vincent.projectuts_anmp.model.Habit
import com.vincent.projectuts_anmp.viewmodel.HabitViewModel

class EditHabitFragment : Fragment(), EditHabitListener {
    private lateinit var binding: FragmentEditHabitBinding
    private lateinit var viewModel: HabitViewModel
    private var habitId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_habit, container, false)
        viewModel = ViewModelProvider(this)[HabitViewModel::class.java]
        
        habitId = arguments?.getInt("habitId") ?: 0
        
        viewModel.getHabitById(habitId).observe(viewLifecycleOwner) { habit ->
            habit?.let {
                binding.habit = it
                setupSpinner(it.icon)
            }
        }
        
        binding.listener = this
        return binding.root
    }

    private fun setupSpinner(selectedIcon: Int) {
        val icons = arrayOf("Books", "Water", "Exercise", "Meditation")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, icons)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerIcon.adapter = adapter
        
        // Map icon resource ID back to spinner index or just set a default for now
        // Ideally, Habit would store the index or string
        binding.spinnerIcon.setSelection(0) 
    }

    override fun onSubmit(habit: Habit) {
        viewModel.update(habit)
        Toast.makeText(context, "Habit updated successfully", Toast.LENGTH_SHORT).show()
        findNavController().popBackStack()
    }
}
