# Research Notes - KAPT Build Failure

## Issue Summary
The build fails at `:app:kaptDebugKotlin` with Data Binding errors:
`Cannot resolve type 'habit'`

## Root Cause Analysis
In `item_habit.xml` and `fragment_edit_habit.xml`, the `Habit` class is referenced with the wrong package:
`type="com.vincent.projectuts_anmp.Habit"`

The actual `Habit` class is located in:
`D:/anmp project/ANMP_ProjectUTS/app/src/main/java/com/vincent/projectuts_anmp/model/habit.kt`
And its package is `com.vincent.projectuts_anmp.model`.

Additionally, there are package mismatches in several Kotlin files (e.g., `HabitItemListener.kt`, `EditHabitListener.kt`, `dashboardFragment.kt`) where the package is declared as `com.vincent.projectuts_anmp` but the file is located in the `view` subpackage directory. While this might not cause the specific `kapt` error, it's inconsistent.

The error `Cannot resolve type 'habit'` (lowercase) likely refers to the variable name being used in an expression where the type could not be resolved, causing the compiler to report it this way.

## Proposed Fix
1. Update `item_habit.xml` to use `com.vincent.projectuts_anmp.model.Habit`.
2. Update `fragment_edit_habit.xml` to use `com.vincent.projectuts_anmp.model.Habit`.
3. (Optional but recommended) Correct the package declarations in Kotlin files to match their directory structure or move them to the root package. Given the current structure, moving them to `com.vincent.projectuts_anmp.view` or moving them up to `com.vincent.projectuts_anmp` is needed.

## Files to Modify
- [item_habit.xml](file:///D:/anmp%20project/ANMP_ProjectUTS/app/src/main/res/layout/item_habit.xml)
- [fragment_edit_habit.xml](file:///D:/anmp%20project/ANMP_ProjectUTS/app/src/main/res/layout/fragment_edit_habit.xml)
