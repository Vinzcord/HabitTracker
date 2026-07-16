# Fix Edit Habit Flow and UI Consistency

The user is confused because there's no clear way to trigger the "Edit" action from the dashboard, and the Edit screen itself has several issues (DataBinding bugs, inconsistent title, and different architecture than Create Habit).

## Proposed Changes

### [Dashboard / Habit List]

#### [MODIFY] [item_habit.xml](file:///D:/anmp%20project/ANMP_ProjectUTS/app/src/main/res/layout/item_habit.xml)
- Add a dedicated "Edit" (pencil) icon button next to the habit title.
- Bind this button's click to `listener.onEditClick(habit)`.

#### [MODIFY] [HabitItemListener.kt](file:///D:/anmp%20project/ANMP_ProjectUTS/app/src/main/java/com/vincent/projectuts_anmp/view/HabitItemListener.kt)
- Rename `onTitleClick` to `onEditClick` to make the intent clearer.

#### [MODIFY] [dashboardFragment.kt](file:///D:/anmp%20project/ANMP_ProjectUTS/app/src/main/java/com/vincent/projectuts_anmp/view/dashboardFragment.kt)
- Update implementation of the renamed `onEditClick`.

### [Edit Habit Layout]

#### [MODIFY] [fragment_edit_habit.xml](file:///D:/anmp%20project/ANMP_ProjectUTS/app/src/main/res/layout/fragment_edit_habit.xml)
- Convert from `<layout>` (DataBinding) to a standard layout (ViewBinding).
- Change the header text from "Habit Data" to "Edit Habit".
- Add IDs to all input fields: `editHabitName`, `editDescription`, `editGoal`, `editUnit`.
- Remove DataBinding variables and expressions.

### [Edit Habit Fragment]

#### [MODIFY] [EditHabitFragment.kt](file:///D:/anmp%20project/ANMP_ProjectUTS/app/src/main/java/com/vincent/projectuts_anmp/view/EditHabitFragment.kt)
- Switch from `FragmentEditHabitBinding` (DataBinding) to `FragmentEditHabitBinding` (ViewBinding).
- Manually populate the fields when the habit data is loaded from the ViewModel.
- Read values from input fields on "Submit" (similar to `CreateHabitFragment`).
- Fix Spinner selection to reflect the current habit icon.

## Verification Plan

### Automated Tests
- Run `:app:compileDebugKotlin` to ensure no build errors.

### Manual Verification
1. Open the app and look for the new Edit icon on habit cards.
2. Click the Edit icon and verify it opens the correct habit.
3. Verify the screen says "Edit Habit" and fields are pre-filled.
4. Modify "Goal" or "Name" and click "Submit".
5. Verify changes are updated on the Dashboard.
