# Free My TV

Android TV home screen for the TVTOKH32SM (Android 9). It replaces the factory launcher and screensaver without
uninstalling anything.

## What is the APK?

`FreeMyTV.apk` is the launcher app (package `io.freemytv`). Install it on the TV with `adb`; then open it and switch the
home screen from the app. Everything can be reverted from the same screen.

## Requirements

- **TV:** Android TV / Google TV 8.0+ (API 26). Tested on the TVTOKH32SM (Android 9).
- **JDK 17** (Temurin recommended). Newer JDKs fail with build-tools 34 (`Unsupported class file major version`).
- **Android SDK:** command-line tools, `build-tools;34.0.0`, `platforms;android-34` and `platform-tools` (for `adb`).
- **Windows:** Git for Windows (use Git Bash or `run_build.bat`).
- **Optional:** a free [TMDB](https://www.themoviedb.org/settings/api) key for trending picks.

Set `JAVA_HOME` and `ANDROID_HOME` before building.

## Build

```bash
git clone https://github.com/Simongonza73/TVTOKH32SM-Tv-Tokyo-Launcher.git
cd TVTOKH32SM-Tv-Tokyo-Launcher
./build.sh
```

The first run creates `config.properties`. Edit it, then build again:

```properties
# Optional: free TMDB API key
tmdb_key=

tmdb_region=US
latitude=40.7128
longitude=-74.0060
city=

# Bump this before every reinstall
version_code=2
```

On Windows, run `run_build.bat`. The result is `FreeMyTV.apk` in the project folder.

Compatibility:
- Use JDK 17 and build-tools 34.0.0.
- Keep `freemytv.keystore`; updates must use the same signing key.

## Install

```bash
adb connect 192.168.1.50:5555
adb install -r FreeMyTV.apk
```

On the TV, open **Free My TV → Home Screen → Free My TV** and accept the USB debugging prompt.

To start the launcher early after power-on (this TV), run once:

```bash
adb shell settings put secure enabled_accessibility_services com.hisilicon.rmservice/com.hisilicon.rmservice.HiRMService:io.freemytv/.BootHomeService
```

To update, increase `version_code` in `config.properties`, run `./build.sh` and install again with `adb install -r`.

To return to the factory home screen, choose **Home Screen → Original** in the app.
