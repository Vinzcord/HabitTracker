# Fix Unresolved Reference 'Habit' and Package Inconsistencies

The user is experiencing an `Unresolved reference 'Habit'` error in `EditHabitFragment.kt`. This is caused by a mismatch between the package declaration in `Habit.kt` and the imports used in other files, combined with general package inconsistencies in the project.

## Proposed Changes

### [Component: Model]
#### [MODIFY] [habit.kt](file:///D:/anmp%20project/ANMP_ProjectUTS/app/src/main/java/com/vincent/projectuts_anmp/model/habit.kt)
- Update package declaration to `com.vincent.projectuts_anmp.model`.

### [Component: View]
#### [MODIFY] [EditHabitFragment.kt](file:///D:/anmp%20project/ANMP_ProjectUTS/app/src/main/java/com/vincent/projectuts_anmp/view/EditHabitFragment.kt)
#### [MODIFY] [createHabitFragment.kt](file:///D:/anmp%20project/ANMP_ProjectUTS/app/src/main/java/com/vincent/projectuts_anmp/view/createHabitFragment.kt)
#### [MODIFY] [dashboardFragment.kt](file:///D:/anmp%20project/ANMP_ProjectUTS/app/src/main/java/com/vincent/projectuts_anmp/view/dashboardFragment.kt)
#### [MODIFY] [loginFragment.kt](file:///D:/anmp%20project/ANMP_ProjectUTS/app/src/main/java/com/vincent/projectuts_anmp/view/loginFragment.kt)
#### [MODIFY] [HabitListAdapter.kt](file:///D:/anmp%20project/ANMP_ProjectUTS/app/src/main/java/com/vincent/projectuts_anmp/view/HabitListAdapter.kt)
#### [MODIFY] [HabitDataSource.kt](file:///D:/anmp%20project/ANMP_ProjectUTS/app/src/main/java/com/vincent/projectuts_anmp/view/HabitDataSource.kt)
#### [MODIFY] [EditHabitListener.kt](file:///D:/anmp%20project/ANMP_ProjectUTS/app/src/main/java/com/vincent/projectuts_anmp/view/EditHabitListener.kt)
#### [MODIFY] [HabitItemListener.kt](file:///D:/anmp%20project/ANMP_ProjectUTS/app/src/main/java/com/vincent/projectuts_anmp/view/HabitItemListener.kt)

For all files above:
- Update package declaration to `com.vincent.projectuts_anmp.view`.
- Add `import com.vincent.projectuts_anmp.R` where resources are used.
- Ensure `import com.vincent.projectuts_anmp.model.Habit` is present where `Habit` is used.
- Fix any other cross-package imports (e.g., `HabitViewModel`).

### [Component: Layouts]
#### [MODIFY] [fragment_edit_habit.xml](file:///D:/anmp%20project/ANMP_ProjectUTS/app/src/main/res/layout/fragment_edit_habit.xml)
- Update variable type for `habit` to `com.vincent.projectuts_anmp.model.Habit`.
- Update variable type for `listener` to `com.vincent.projectuts_anmp.view.EditHabitListener`.

#### [MODIFY] [item_habit.xml](file:///D:/anmp%20project/ANMP_ProjectUTS/app/src/main/res/layout/item_habit.xml)
- Update variable type for `habit` to `com.vincent.projectuts_anmp.model.Habit`.
- Update variable type for `listener` to `com.vincent.projectuts_anmp.view.HabitItemListener`.

## Verification Plan

### Automated Tests
- Run `./gradlew :app:assembleDebug` to ensure the project compiles successfully.

### Manual Verification
- Verify that the IDE no longer reports unresolved references in the modified files.
