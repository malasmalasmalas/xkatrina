# XKatrina

> **fufufu 作** (リビルド版)
### V260427 - リビルド更新
+ **旧 APK を Gradle プロジェクトへ移行** (JADX 出力からのリビルド)
+ **起動クラッシュ修正**: `C0978R` を再生成し実行時のリソース ID と一致させる
+ **下部ナビバー**: アイコンとラベルが重なる問題を修正
+ **削除**: ユーザー名編集、壁紙設定、Telegram FAB
+ **削除**: OTP ショートカット (バグあり)
+ **モジュール自動インストール**: Ritual / Scritual で「Install Module」ボタン、**Magisk / KSU / APatch** に対応 (自動検出)
+ **新ヘルパー** `ModuleHelper`: `isInstalled()`、`extractZip()`、`install()`、`showInstallDialog()`
+ **Cleaning / Timepick のフリーズ修正**: 先にモジュール確認、未インストールならインストールダイアログ
+ **build.prop エディタのフリーズ修正**: `onCreate` でモジュール確認
+ **Ritual ショートカットのローディングフリーズ修正**: 「モジュール非有効」カードにインストールボタン
+ **フェイク GPS** (NEW): Google Maps ピッカー、マーカードラッグ / 地図タップ、都市プリセット (Jakarta、Bandung、Tokyo、NY、London)、1 秒ごとに `LocationManager.setTestProviderLocation` をインジェクトする前面サービス
+ **レイアウト `material_debug.xml`** + ドローアブル `f821bg` (以前リソースから欠落)
+ **新パーミッション**: `ACCESS_MOCK_LOCATION`、`ACCESS_FINE_LOCATION`、`ACCESS_COARSE_LOCATION`
+ **新依存関係**: `play-services-maps:19.0.0`、`play-services-location:21.3.0`
+ **多言語オンボーディング** (インドネシア語 / 英語 / 日本語)
+ **マークダウン資産のローカライズ** (変更履歴と利用規約)

### V240306
+ ショートカットを追加
+ Play ストア からインストールされたように見せる

### V240228
+ Katrina モジュールの修正

### V240225
+ 新規 prop を 342 個追加、合計 7364 個
+ Mini Termux (フェイルセーフセッション)
+ Firebase
+ スキャナ
+ バーコードメーカー
+ Prop から バーコードへ
+ system.prop 操作の新方式
+ 画面文字列のスキャンによるコピー
+ バグ修正

### V231218
+ AppFragmentActivity.java 4105 のクラッシュ (fandre916 報告)
+ AppFragmentActivity.java 582 のクラッシュ (rifky.reynanda.327 報告)
+ RitualFragmentActivity.java 2023 のクラッシュ (ahmadramzisyafiul762 報告)
+ KatrinaIslandService.java 1645 のクラッシュ (janetta.reloads 報告)
+ FSNavigator.java 209 のクラッシュ (gambis94 報告)
+ TransactionTooLargeException のクラッシュ (andiwy3 報告)
+ XBackup の変換を初回バックアップ日付順に整列
+ ローダーを再設計
+ RootTools ライブラリを Libsu に全面移行
+ アプリ一覧で最後に使用した位置を記憶
+ バイブアクション
+ バックアップショートカット追加
+ リストアショートカット追加
+ バックアップスロット並べ替え用 Chip Slot 追加
+ よりインタラクティブなショートカットの再設計

### V1
+ 初回リリース
