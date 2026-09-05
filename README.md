# Trading Assistant V3

V3 is an Android signal-assistant shell with the mobile dashboard, risk manager, notification permission, and a build workflow.

IMPORTANT: the APK build can be produced by Android Studio or GitHub Actions. The live market feed is not connected in this source package yet, so the displayed WAIT state is intentional.

To create the APK:
- Android Studio: open the project and Build > Generate App Bundle / APK > Generate APK.
- Or push the project to GitHub and run the `Build APK` workflow; download the generated APK artifact.

Android Studio's official documentation confirms APKs can be built/deployed from the IDE. The GitHub workflow is included to make the APK build repeatable.
