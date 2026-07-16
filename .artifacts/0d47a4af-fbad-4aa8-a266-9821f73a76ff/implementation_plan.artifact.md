# Implementation Plan - Fix KAPT Data Binding Error

The goal is to resolve the `:app:kaptDebugKotlin` build failure by correcting the package references in the Data Binding layout files.

## User Review Required

> [!IMPORTANT]
> The `Habit` class was being referenced in layout files using `com.vincent.projectuts_anmp.Habit`, but it is actually located in `com.vincent.projectuts_anmp.model.Habit`. I will update the layout files to use the correct fully qualified name.

## Proposed Changes

### Layout Files

#### [MODIFY] [item_habit.xml](file:///D:/anmp%20project/ANMP_ProjectUTS/app/src/main/res/layout/item_habit.xml)
- Update the `type` of the `habit` variable from `com.vincent.projectuts_anmp.Habit` to `com.vincent.projectuts_anmp.model.Habit`.

#### [MODIFY] [fragment_edit_habit.xml](file:///D:/anmp%20project/ANMP_ProjectUTS/app/src/main/res/layout/fragment_edit_habit.xml)
- Update the `type` of the `habit` variable from `com.vincent.projectuts_anmp.Habit` to `com.vincent.projectuts_anmp.model.Habit`.

## Verification Plan

### Automated Tests
- Run `:app:kaptDebugKotlin` to verify the annotation processing completes successfully.
- Run a full build using `./gradlew assembleDebug`.

### Manual Verification
- Verify that the IDE no longer shows "Cannot resolve symbol" in the XML files for the `habit` variable.
