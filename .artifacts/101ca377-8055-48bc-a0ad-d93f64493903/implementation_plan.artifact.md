# Implementation Plan - Fix Kapt Data Binding Errors

The project is failing to build due to `kapt` errors related to Data Binding. The error message `Cannot resolve type 'habit'` indicates that the Data Binding compiler cannot find the class specified for the `habit` variable in the layout files.

## Proposed Changes

The `Habit` model is located in the `com.vincent.projectuts_anmp.model` package, but the layout files are referencing it as `com.vincent.projectuts_anmp.Habit`.

### Layouts

#### [MODIFY] [fragment_edit_habit.xml](file:///D:/anmp%20project/ANMP_ProjectUTS/app/src/main/res/layout/fragment_edit_habit.xml)
- Update the `habit` variable type to `com.vincent.projectuts_anmp.model.Habit`.
- Fix the two-way binding for `habit.goal`. Since `habit.goal` is an `Int`, direct concatenation in `@={}` is not supported for two-way binding. I will change it to one-way binding for now or use a proper approach if possible, but the primary goal is to fix the build error. Actually, I'll change it to one-way binding `android:text="@{String.valueOf(habit.goal)}"` or similar to avoid the two-way binding error if it persists, but I'll first try just fixing the type.

#### [MODIFY] [item_habit.xml](file:///D:/anmp%20project/ANMP_ProjectUTS/app/src/main/res/layout/item_habit.xml)
- Update the `habit` variable type to `com.vincent.projectuts_anmp.model.Habit`.

## Verification Plan

### Automated Tests
- Run `./gradlew :app:kaptDebugKotlin` to verify that the data binding generation succeeds.
- Run a full build: `./gradlew assembleDebug`.

### Manual Verification
- N/A (Build fix)
