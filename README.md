# XKatrina

A powerful Android root toolkit for device customization and system modification.

## Overview

XKatrina is a comprehensive root toolkit designed for Android devices with Magisk, KernelSU (KSU), or APatch installed. It provides various utilities for system property editing, app management, fake GPS, and more.

## Features

### 1. Build.prop Editor
- Direct editing of system properties without requiring additional modules
- Randomize device fingerprint with hard random or prop random modes
- Support for multiple device profiles
- Automatic backup and restore functionality

### 2. Ritual Cleaner (Timepick/Cleaning)
- Clean cache and temporary files from /data and /storage
- Works directly without requiring XKatrina module
- Async task processing with progress indication
- Shortcut support for quick access

### 3. Fake GPS
- Google Maps integration for location selection
- Mock location provider for testing purposes
- Preset locations (Jakarta, Bandung, Tokyo, New York, London)
- Foreground service with notification
- Developer options shortcut

### 4. App Management
- Force stop applications
- Wipe app data and cache
- Create shortcuts for quick actions
- List system and user applications

### 5. Terminal Integration
- XTermod integration for command-line operations
- Script execution support
- Bootstrap package management

### 6. Multi-Language Support
- Indonesian (ID) - Default
- English (EN)
- Japanese (JA)

## Requirements

- Android 10+ (API Level 29+)
- Root access (Magisk/KSU/APatch)
- For Fake GPS: Developer Options enabled with mock location app selection

## Installation

1. Install the APK
2. Grant root permissions when prompted
3. For full functionality, install XKatrina Magisk module (optional for most features)

## Building from Source

### Prerequisites
- Android Studio or Gradle 8.7+
- Android SDK 34+
- Java 17+

### Build Steps

```bash
./gradlew :app:assembleDebug
```

The APK will be generated at `app/build/outputs/apk/debug/app-debug.apk`

## Project Structure

```
XKatrina/
├── app/
│   ├── src/main/
│   │   ├── java/com/fufufu/katrina/backup/
│   │   │   ├── MainActivity.java
│   │   │   ├── SystemPropActivity.java
│   │   │   ├── ShortcutExecutorActivity.java
│   │   │   ├── FakeGpsActivity.java
│   │   │   └── ...
│   │   ├── res/
│   │   │   ├── values/strings.xml
│   │   │   ├── values-en/strings.xml
│   │   │   ├── values-ja/strings.xml
│   │   │   └── layout/
│   │   └── assets/
│   │       ├── prop.json
│   │       ├── a.json
│   │       ├── b.json
│   │       ├── univ.zip (latest universal prop archive)
│   │       └── main.dex (Magisk module)
│   └── build.gradle
├── gradle/
└── settings.gradle
```

## Localization

All UI strings are externalized to string resources:
- `res/values/strings.xml` - Indonesian (default)
- `res/values-en/strings.xml` - English
- `res/values-ja/strings.xml` - Japanese

New translations can be added by creating `values-[locale]/strings.xml`

## Key Components

### ModuleHelper
Handles detection and installation of XKatrina Magisk module for features that require it.

### LocaleHelper
Manages application locale switching for multi-language support.

### LocalizedAssets
Loads localized markdown files (changelog, agreement) based on selected language.

### FakeGpsService
Foreground service for mock location injection using Android's LocationManager.

## Changelog

### Version 260427 (Current)
- Full localization support (ID/EN/JA)
- Removed hardcoded strings
- Fixed Timepick shortcut - works without module
- Fixed build.prop editor - direct editing without module
- Fake GPS with Google Maps integration
- Updated Material Design 3 components
- Removed OTP feature
- UI improvements and bug fixes

## Credits

Thanks to fufufu7 aka edo for creating the best app ever.

Original concept and development by fufufu7.

Rebuilt and enhanced by malas.

## License

This project is provided as-is for educational and personal use. Use at your own risk.

## Disclaimer

Modifying system properties and using root access can potentially harm your device. Always create backups before making changes. The developers are not responsible for any damage caused by using this application.

## Contact

For issues, suggestions, or contributions, please visit:
https://github.com/malasmalasmalas/xkatrina
