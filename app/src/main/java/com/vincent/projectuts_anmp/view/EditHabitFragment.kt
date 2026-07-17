package com.vincent.projectuts_anmp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.vincent.projectuts_anmp.databinding.FragmentEditHabitBinding
import com.vincent.projectuts_anmp.model.Habit
import com.vincent.projectuts_anmp.viewmodel.HabitViewModel

class EditHabitFragment : Fragment() {
    private var _binding: FragmentEditHabitBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HabitViewModel
    private var habitId: Int = 0

    private val icons = listOf(
        "Books" to android.R.drawable.ic_menu_today,
        "Water" to android.R.drawable.ic_menu_view,
        "Exercise" to android.R.drawable.ic_menu_edit,
        "Meditation" to android.R.drawable.ic_menu_compass
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditHabitBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[HabitViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        habitId = EditHabitFragmentArgs.fromBundle(requireArguments()).habitId

        setupSpinnerAdapter()

        viewModel.getHabitById(habitId).observe(viewLifecycleOwner) { habit ->
            habit?.let { populateFields(it) }
        }

        binding.btnSubmit.setOnClickListener {
            submitChanges()
        }
    }

    private fun setupSpinnerAdapter() {
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            icons.map { it.first }
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerIcon.adapter = adapter
    }

    private fun populateFields(habit: Habit) {
        binding.editHabitName.setText(habit.name)
        binding.editDescription.setText(habit.description)
        binding.editGoal.setText(habit.goal.toString())
        binding.editUnit.setText(habit.unit)

        val iconIndex = icons.indexOfFirst { it.second == habit.icon }
        if (iconIndex >= 0) {
            binding.spinnerIcon.setSelection(iconIndex)
        }
    }

    private fun submitChanges() {
        val name = binding.editHabitName.text.toString()
        val description = binding.editDescription.text.toString()
        val goalStr = binding.editGoal.text.toString()
        val unit = binding.editUnit.text.toString()
        val iconIndex = binding.spinnerIcon.selectedItemPosition

        if (name.isNotEmpty() && description.isNotEmpty() && goalStr.isNotEmpty() && unit.isNotEmpty()) {
            val goal = goalStr.toIntOrNull() ?: 0
            val iconRes = icons[iconIndex].second

            val updatedHabit = Habit(
                id = habitId,
                name = name,
                description = description,
                goal = goal,
                unit = unit,
                icon = iconRes
            )
            viewModel.update(updatedHabit)

            Toast.makeText(context, "Habit updated successfully", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        } else {
            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
