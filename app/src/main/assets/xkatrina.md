# XKatrina

> **oleh fufufu** (rebuild)
### V260427 - Rebuild Update
+ **Migrasi APK lama ke project Gradle** (rebuild dari hasil JADX)
+ **Fix crash startup**: regenerasi `C0978R` agar resource ID match runtime
+ **Bottom navbar**: perbaikan icon + label tumpang tindih
+ **Hapus fitur**: edit username, wallpaper setting, FAB Telegram
+ **Hapus fitur**: shortcut OTP (buggy)
+ **Auto-install Module**: tombol Install Module di Ritual & Scritual fragment, support **Magisk / KSU / APatch** (auto-detect)
+ **Helper baru** `ModuleHelper`: `isInstalled()`, `extractZip()`, `install()`, `showInstallDialog()`
+ **Fix stuck Cleaning / Timepick**: cek module dulu, kalau belum terpasang → dialog install (bukan stuck di "Memetakan file dan cache")
+ **Fix stuck Build.prop Editor**: cek module di `onCreate`, kalau belum terpasang → dialog install
+ **Fix stuck Ritual Shortcut Loading**: tombol install muncul di card "Module tidak aktif"
+ **Fake GPS** (BARU): Google Maps picker, drag marker / tap di peta, preset kota (Jakarta, Bandung, Tokyo, NY, London), foreground service yang inject `LocationManager.setTestProviderLocation` setiap 1 detik
+ **Layout `material_debug.xml`** + drawable `f821bg` (sebelumnya hilang dari resource)
+ **Permission baru**: `ACCESS_MOCK_LOCATION`, `ACCESS_FINE_LOCATION`, `ACCESS_COARSE_LOCATION`
+ **Dependency baru**: `play-services-maps:19.0.0`, `play-services-location:21.3.0`
+ **i18n scaffolding** untuk onboarding multi-bahasa

### V240306
+ Menambahkan shortcut
+ Membuat aplikasi terbaca diinstall dari PlayStore

### V240228
+ Fix Katrina Module

### V240225
+ Menambahkan 342 prop baru, total prop sekarang 7364
+ Mini Termux (failsafe session)
+ Firebase
+ Scanner
+ Barcode Maker
+ Prop To Barcode
+ Cara baru untuk manipulasi system.prop
+ Scan text di layar untuk menyalin text
+ Perbaikan bug

### V231218
+ Crash AppFragmentActivity.java 4105 (Report by fandre916)
+ Crash AppFragmentActivity.java 582 (Report by rifky.reynanda.327)
+ Crash RitualFragmentActivity.java 2023 (Report by ahmadramzisyafiul762)
+ Crash KatrinaIslandService.java 1645 (Report by janetta.reloads)
+ Crash FSNavigator.java 209 (Report by gambis94)
+ Crash TransactionTooLargeException (Report by andiwy3)
+ Convert XBackup diurutkan sesuai tanggal backup awal
+ Desain ulang loader
+ Migrasi seluruh perpustakaan RootTools menjadi Libsu
+ App List set posisi aplikasi terakhir dipakai
+ Vibrate action
+ Menambahkan shortcut backup
+ Menambahkan shortcut restore
+ Menambahkan Chip Slot untuk mengurutkan ulang slot backup
+ Desain ulang shortcut lebih interaktif

### V231211
+ Menambahkan 95 prop baru, total prop sekarang 7022
+ Pilihan membuka App dengan link atau deeplink setelah restore
+ Memperbaiki bug kecil
+ Menghapus Eternal (Malignant)
+ Coffee

### V231209
+ Crash KatrinaIslandService.java 872 (Report by sofisdr45)
+ Crash DialogActivity.java 522 (Report by gambis94)
+ Crash DownloadTask.java 387 (Report by mcabe881)
+ Crash AppFragmentActivity.java 3154 (Report by okiokta890)
+ Hapus line ro.boot yang menghalangi reboot recovery
+ Mengganti seluruh TimerTask dengan Handler
+ Migrasi sebagian perpustakaan RootTools menjadi Libsu
+ Meningkatkan kecepatan
+ Menambahkan copas nomer telepon acak
+ Memperbaiki OS versi di Ritual
+ System Prop Editor

### V231207
+ Kirim file ke bot Telegram
+ Simpan backup ke bot Telegram
+ Perbaikan line 4105 AppFragmentActivity (Report by ukisnoer)
+ Perbaikan line 4591 AppFragmentActivity (Report by yohanriyadi)
+ Perbaikan line 297 KatrinaIslandService (Report by redranger, kopay)
+ Perbaikan gagal menulis hasil wipe GMS
+ Perbaikan Reboot dalvic cache
+ Perbaikan Accessibility Service
+ Perbaikan Katrina Island default margin 90
+ Perbaikan user request tidak dapat menerima balasan dari fufufu
+ Perbaikan random date
+ Perbaikan crash karena json backup tidak valid

### V231202
+ Pembaruan logic Asynctask
+ Menambahkan editor prop tanpa pemilihan prop ulang
+ Identifier versi dan pengirim laporan crash
+ Migrasi sebagian perpustakaan RootTools menjadi Libsu
+ Pengecekan ulang exception untuk menangani crash
+ Fix Blocker IFW file
+ Custom backup (prototipe)

### V231128
+ Mengganti sebagian perpustakaan RootTools menjadi perpustakaan topjohnwu Libsu
+ Mengaktifkan Pemblokir
+ Menambahkan 54 prop baru, total prop sekarang 6927 
+ Menambahkan getar
+ Menambahkan tanda warna
+ Menambahkan menu naik turun release (os)
+ Menambahkan Rebackup (backup ulang diposisi nomer yang sama)
+ Menambahkan shortcut buka aplikasi
+ Menambahkan shortcut hapus data untuk beberapa aplikasi
+ Menambahkan list hapus data beberapa aplikasi
+ Menambahkan shortcut ritual
+ Menambahkan Mode Pesawat pada ritual
+ Kode ulang Timepick, menonaktifkan pemindaian beberapa path untuk kecepatan
+ Desain ulang halaman shortcut dengan carousel
+ Perbaikan monet engine
+ Perbaikan XBackup Convert
+ Perbaikan Glide
+ Perbaikan crash karena registrasi akun tidak lengkap
+ Perbaikan shortcut link crash jika tidak ada browser
+ Perbaikan bug membaca file backup terlalu (Not tested because random events)
+ Pengecekan ulang exception untuk menangani crash

### V231123
+ Perbaikan Backup terbalik
+ Catatan: Proses backup antara jam 00.00 - 12.00 tanggal 22-11-2023 tidak dapat dipulihkan

### V231122
+ Maaf terbalik saat merestore
+ Seharusnya menghapus data target sebelum restore yang terjadi adalah penghapusan data XKatrina sebelum merestore :(

### V231121
+ Ganti /data/media menjadi /storage/
+ Perbaikan banyak bug
+ Perbaikan logika daftar aplikasi
+ Perbaikan json Eternal
+ Kode ulang Timepick
+ Menambahkan laporan crash ke fufufu
+ Menambahkan menu kirim ke bot
+ Menambahkan Pemblokir (prototipe)

### V231118
+ Perbaikan login tidak terhubung
+ Perbaikan perintah shell reboot
+ Perbaikan beberapa tombol
+ Perbaikan padding
+ Perbaikan membaca aplikasi yang tersemat
+ Perbaikan logika daftar aplikasi
+ Perbaikan proses restore
+ Perbaikan file asset
+ Penyederhanaan kode

### V231115
+ Perbaikan firebase pada perangkat tanpa Layanan Google Play

### V1.1
+ Perbaikan crash

### V1
+ Rilis awal