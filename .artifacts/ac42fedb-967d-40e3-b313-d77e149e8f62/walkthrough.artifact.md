# Walkthrough - Fixed Unresolved Reference 'insert'

The "Unresolved reference 'insert'" error was caused by missing methods in `HabitViewModel`. I have added these methods and improved the in-memory repository to handle ID generation correctly.

## Changes Made

### [Habit ViewModel]

#### [HabitViewModel.kt](file:///D:/anmp%20project/ANMP_ProjectUTS/app/src/main/java/com/vincent/projectuts_anmp/viewmodel/HabitViewModel.kt)
Added the missing `insert` and `updateProgress` methods to bridge the View with the Repository.

```kotlin
    fun insert(habit: Habit) {
        HabitRepository.addHabit(habit)
    }

    fun updateProgress(habit: Habit, delta: Int) {
        val currentList = habits.value ?: return
        val index = currentList.indexOfFirst { it.id == habit.id }
        if (index != -1) {
            HabitRepository.updateHabitProgress(index, delta)
        }
    }
```

### [Habit Repository]

#### [HabitDataSource.kt](file:///D:/anmp%20project/ANMP_ProjectUTS/app/src/main/java/com/vincent/projectuts_anmp/view/HabitDataSource.kt)
Updated `addHabit` to generate a unique ID for each new habit. This is necessary because the `update` functionality relies on the habit's ID to find it in the list.

```kotlin
    fun addHabit(habit: Habit) {
        val currentList = _habits.value ?: mutableListOf()
        val newId = if (currentList.isEmpty()) 1 else currentList.maxOf { it.id } + 1
        val habitWithId = habit.copy(id = newId)
        currentList.add(habitWithId)
        _habits.value = currentList
    }
```

## Verification Results

### Automated Tests
- Ran `:app:compileDebugKotlin` and the build finished successfully.
