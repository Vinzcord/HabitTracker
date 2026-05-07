package com.vincent.projectuts_anmp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.vincent.anmp_projectuts.databinding.FragmentCreateHabitBinding

class CreateHabitFragment : Fragment() {
    private var _binding: FragmentCreateHabitBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HabitViewModel by viewModels()

    private val icons = listOf(
        "Fitness" to android.R.drawable.ic_menu_today,
        "Water" to android.R.drawable.ic_menu_view,
        "Read" to android.R.drawable.ic_menu_edit,
        "Meditation" to android.R.drawable.ic_menu_compass
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateHabitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            icons.map { it.first }
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerIcon.adapter = adapter

        binding.btnCreateHabit.setOnClickListener {
            val name = binding.editHabitName.text.toString()
            val description = binding.editDescription.text.toString()
            val goalStr = binding.editGoal.text.toString()
            val unit = binding.editUnit.text.toString()
            val iconIndex = binding.spinnerIcon.selectedItemPosition

            if (name.isNotEmpty() && description.isNotEmpty() && goalStr.isNotEmpty() && unit.isNotEmpty()) {
                val goal = goalStr.toIntOrNull() ?: 0
                val iconRes = icons[iconIndex].second

                val newHabit = Habit(name, description, goal, unit, iconRes)
                viewModel.addHabit(newHabit)

                Toast.makeText(context, "Habit Created!", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            } else {
                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}