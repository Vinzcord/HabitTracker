# Fix App Startup Issue and Activity Setup

The application cannot be opened because `MainActivity.kt` is empty (likely due to a Git merge issue) and there is a mismatch between the activity's package and its declaration in the manifest.

## Proposed Changes

### [Activity / Manifest]

#### [MODIFY] [MainActivity.kt](file:///D:/anmp%20project/ANMP_ProjectUTS/app/src/main/java/com/vincent/projectuts_anmp/view/MainActivity.kt)
- Restore the `MainActivity` class implementation. It will inherit from `AppCompatActivity` and set the content view to `activity_main`.
- Support navigation component setup if needed (though `activity_main.xml` already handles the `NavHostFragment`).

#### [MODIFY] [AndroidManifest.xml](file:///D:/anmp%20project/ANMP_ProjectUTS/app/src/main/AndroidManifest.xml)
- Update the `<activity>` tag for `MainActivity` to use the correct fully qualified name: `com.vincent.projectuts_anmp.view.MainActivity`.

### [Edit Habit Flow]

#### [MODIFY] [fragment_edit_habit.xml](file:///D:/anmp%20project/ANMP_ProjectUTS/app/src/main/res/layout/fragment_edit_habit.xml)
- Fix the two-way binding for the `goal` field. Currently, it uses `android:text="@={`` + habit.goal}"` which is invalid for two-way binding (you cannot bind back to a concatenated string).
- I will change it to use a custom InverseBindingAdapter or simply use ViewBinding in the Fragment to handle this conversion safely, or use a more standard DataBinding way if possible. However, the previous plan was to switch to ViewBinding for consistency.

## Verification Plan

### Automated Tests
- Run `:app:assembleDebug` to ensure the project builds.

### Manual Verification
- Deploy the app to a device/emulator.
- Verify that the app opens to the Login screen.
- Verify that the Dashboard opens after login.
