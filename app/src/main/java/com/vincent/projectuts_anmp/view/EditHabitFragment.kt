package com.vincent.projectuts_anmp.view

import android.R
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
import com.vincent.projectuts_anmp.EditHabitListener
import com.vincent.projectuts_anmp.databinding.FragmentEditHabitBinding
import com.vincent.projectuts_anmp.model.Habit
import com.vincent.projectuts_anmp.viewmodel.HabitViewModel

class EditHabitFragment : Fragment(), EditHabitListener {
    private lateinit var binding: FragmentEditHabitBinding
    private lateinit var viewModel: HabitViewModel
    private var habitId: Int = 0

    private val icons = listOf(
        "Books" to R.drawable.ic_menu_today,
        "Water" to R.drawable.ic_menu_view,
        "Exercise" to R.drawable.ic_menu_edit,
        "Meditation" to R.drawable.ic_menu_compass
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, com.vincent.projectuts_anmp.R.layout.fragment_edit_habit, container, false)
        viewModel = ViewModelProvider(this)[HabitViewModel::class.java]

        habitId = EditHabitFragmentArgs.fromBundle(requireArguments()).habitId

        setupSpinnerAdapter()

        viewModel.getHabitById(habitId).observe(viewLifecycleOwner) { habit ->
            habit?.let {
                binding.habit = it
                selectSpinnerIcon(it.icon)
            }
        }

        binding.listener = this
        return binding.root
    }

    private fun setupSpinnerAdapter() {
        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.simple_spinner_item,
            icons.map { it.first }
        )
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.spinnerIcon.adapter = adapter
    }

    private fun selectSpinnerIcon(iconRes: Int) {
        val index = icons.indexOfFirst { it.second == iconRes }
        if (index >= 0) {
            binding.spinnerIcon.setSelection(index)
        }
    }

    override fun onSubmit(habit: Habit) {
        val selectedIconRes = icons[binding.spinnerIcon.selectedItemPosition].second
        val updatedHabit = habit.copy(icon = selectedIconRes)

        viewModel.update(updatedHabit)
        Toast.makeText(context, "Habit updated successfully", Toast.LENGTH_SHORT).show()
        findNavController().popBackStack()
    }
}