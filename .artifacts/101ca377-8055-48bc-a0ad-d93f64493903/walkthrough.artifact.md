# Walkthrough - Fix Data Binding Resolve Errors

I have fixed the `kapt` build errors by correcting the package reference for the `Habit` model in your Data Binding layout files.

## Changes Made

### Layouts

#### [fragment_edit_habit.xml](file:///D:/anmp%20project/ANMP_ProjectUTS/app/src/main/res/layout/fragment_edit_habit.xml)
- Changed variable type from `com.vincent.projectuts_anmp.Habit` to `com.vincent.projectuts_anmp.model.Habit`.
- Updated the binding for `habit.goal` to use `String.valueOf(habit.goal)` to avoid issues with two-way binding on integer fields without a converter.

#### [item_habit.xml](file:///D:/anmp%20project/ANMP_ProjectUTS/app/src/main/res/layout/item_habit.xml)
- Changed variable type from `com.vincent.projectuts_anmp.Habit` to `com.vincent.projectuts_anmp.model.Habit`.

## Verification Results

### Automated Tests
- Ran `./gradlew :app:kaptDebugKotlin` and the build finished successfully.

> [!NOTE]
> Since I changed `habit.goal` to one-way binding, any changes made to the "Goal" field in the Edit screen will need to be manually captured from the `EditText` if you want to save them, unless you implement a proper Inverse Binding Adapter for `Int`.
