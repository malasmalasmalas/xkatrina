# XKatrina

> **by fufufu** (rebuild)
### V260427 - Rebuild Update
+ **Migrate legacy APK to Gradle project** (rebuild from JADX output)
+ **Fix startup crash**: regenerate `C0978R` so resource IDs match the runtime
+ **Bottom navbar**: fix overlapping icon + label
+ **Removed**: edit username, wallpaper setting, Telegram FAB
+ **Removed**: OTP shortcut (buggy)
+ **Auto-install module**: Install Module button in Ritual & Scritual fragments, supports **Magisk / KSU / APatch** (auto-detect)
+ **New helper** `ModuleHelper`: `isInstalled()`, `extractZip()`, `install()`, `showInstallDialog()`
+ **Fix Cleaning / Timepick stuck**: check module first, if missing → install dialog (no longer stuck on "Mapping files and cache")
+ **Fix Build.prop Editor stuck**: check module on `onCreate`, prompt install if missing
+ **Fix Ritual Shortcut stuck on loading**: install button shows in "Module not active" card
+ **Fake GPS** (NEW): Google Maps picker, drag the marker / tap on map, city presets (Jakarta, Bandung, Tokyo, NY, London), foreground service injecting `LocationManager.setTestProviderLocation` every 1 second
+ **Layout `material_debug.xml`** + drawable `f821bg` (previously missing from resources)
+ **New permissions**: `ACCESS_MOCK_LOCATION`, `ACCESS_FINE_LOCATION`, `ACCESS_COARSE_LOCATION`
+ **New deps**: `play-services-maps:19.0.0`, `play-services-location:21.3.0`
+ **Multi-language onboarding** (Indonesian / English / Japanese)
+ **Localized markdown assets** (changelog & agreement)

### V240306
+ Added shortcuts
+ Made the app appear as installed from the Play Store

### V240228
+ Fix Katrina Module

### V240225
+ Added 342 new props, total now 7364
+ Mini Termux (failsafe session)
+ Firebase
+ Scanner
+ Barcode Maker
+ Prop To Barcode
+ New way to manipulate system.prop
+ Scan on-screen text to copy text
+ Bug fixes

### V231218
+ Crash AppFragmentActivity.java 4105 (Reported by fandre916)
+ Crash AppFragmentActivity.java 582 (Reported by rifky.reynanda.327)
+ Crash RitualFragmentActivity.java 2023 (Reported by ahmadramzisyafiul762)
+ Crash KatrinaIslandService.java 1645 (Reported by janetta.reloads)
+ Crash FSNavigator.java 209 (Reported by gambis94)
+ Crash TransactionTooLargeException (Reported by andiwy3)
+ XBackup convert sorted by initial backup date
+ Loader redesigned
+ Migrated all RootTools libraries to Libsu
+ App List remembers last used position
+ Vibrate action
+ Added backup shortcut
+ Added restore shortcut
+ Added Chip Slot to reorder backup slots
+ Redesigned shortcut to be more interactive

### V231211
+ Added 95 new props, total now 7022
+ Option to open app via link or deeplink after restore
+ Minor bug fixes
+ Removed Eternal (Malignant)
+ Coffee

### V231209
+ Crash KatrinaIslandService.java 872 (Reported by sofisdr45)
+ Crash DialogActivity.java 522 (Reported by gambis94)
+ Crash DownloadTask.java 387 (Reported by mcabe881)
+ Crash AppFragmentActivity.java 3154 (Reported by okiokta890)
+ Removed `ro.boot` line that blocked recovery reboot
+ Replaced all TimerTask with Handler
+ Migrated some RootTools to Libsu
+ Improved speed
+ Added random phone number copy/paste
+ Fixed OS version on Ritual
+ System Prop Editor

### V231207
+ Send file to Telegram bot
+ Save backup to Telegram bot
+ Fix line 4105 AppFragmentActivity (Reported by ukisnoer)
+ Fix line 4591 AppFragmentActivity (Reported by yohanriyadi)
+ Fix line 297 KatrinaIslandService (Reported by redranger, kopay)
+ Fix failure to write GMS wipe result
+ Fix Reboot dalvic cache
+ Fix Accessibility Service
+ Fix Katrina Island default margin 90
+ Fix user request unable to receive replies from fufufu
+ Fix random date
+ Fix crash due to invalid backup JSON

### V231202
+ AsyncTask logic update
+ Added prop editor without prop reselection
+ Crash report version + sender identifier
+ Migrated some RootTools to Libsu
+ Re-checked exception handling
+ Fix Blocker IFW file
+ Custom backup (prototype)

### V231128
+ Replaced some RootTools with topjohnwu Libsu
+ Enabled Blocker
+ Added 54 new props, total now 6927
+ Added vibration
+ Added color marks
+ Added OS up/down release menu
+ Added Rebackup (re-backup at the same slot number)
+ Added Open App shortcut
+ Added Wipe Data shortcut for several apps
+ Added Wipe Data list for several apps
+ Added Ritual shortcut
+ Added Airplane Mode in Ritual
+ Re-coded Timepick, disabled scanning some paths for speed
+ Redesigned shortcut page with carousel
+ Fix monet engine
+ Fix XBackup Convert
+ Fix Glide
+ Fix crash due to incomplete account registration
+ Fix shortcut link crash if no browser
+ Fix bug reading backup file (not tested due to random events)
+ Re-checked exception handling

### V231123
+ Fix reversed Backup
+ Note: backup processes between 00:00 - 12:00 on 2023-11-22 cannot be restored

### V231122
+ Sorry the restore was reversed
+ It should delete target data before restore but it deleted XKatrina data instead :(

### V231121
+ Switched /data/media to /storage/
+ Many bug fixes
+ Fix app list logic
+ Fix Eternal JSON
+ Re-coded Timepick
+ Added crash reports to fufufu
+ Added send-to-bot menu
+ Added Blocker (prototype)

### V231118
+ Fix login not connecting
+ Fix shell reboot command
+ Fix several buttons
+ Fix padding
+ Fix reading embedded apps
+ Fix app list logic
+ Fix restore process
+ Fix asset files
+ Code simplification

### V231115
+ Fix firebase on devices without Google Play services

### V1.1
+ Crash fix

### V1
+ Initial release
