# ユーザー情報及び回数券管理アプリケーション

## 作成目的・機能
私が勤めているパーソナルジムが紙媒体で回数券を管理しているため、その課題を解決するために作成しました。

ユーザー及びユーザーが購入している回数券の閲覧、登録、更新、削除などのCROUD処理を実装しております。
現在はユーザーのトレーニング内容を記述できる、カルテ機能の実装に取り組んでいます。


##  使用技術 

### バックエンド
- **言語:** Java 21
- **フレームワーク:** Spring Boot 3.5.3
- **データベース:** MySQL / H2 Database (テスト用)
- **テスト:** JUnit 5
- **ビルド:** Gradle

##  API仕様 

| メソッド | エンドポイント | 説明 |
| :--- | :--- | :--- |
| **GET** | `/api/userList` | ユーザー情報とそのユーザーが持つ回数券情報リストを全件取得します。 |
| **GET** | `/api/userList/{userId}` | 特定(名前で指定)のユーザー情報を取得します。 |
| **POST** |  `/api/newUser` | 新しいユーザー情報を追加します。 |
| **POST** |  `/api/newTickets` | 既存のユーザーに紐づく新しい回数券情報を追加します。 |
| **PUT** |  `/api/updateUser` | ユーザー情報を更新します。 |
| **PUT** |  `/api/updateTickets` | 回数券情報を更新します。 |
| **DELETE** |  `/api/deleteUserInfo` | 特定のユーザー情報とそのユーザーが持つ回数券情報を一括削除します。 |

## ポストマン入力時のAPI動作確認

### ・新しいユーザー及び回数券の登録
<img src="https://github.com/user-attachments/assets/7b86b229-9020-4475-b73b-c7249820e2e0" width="500" alt="ユーザー登録APIの成功レスポンス">
<img src="https://github.com/user-attachments/assets/a660027c-ee26-4a28-ba74-141a512bd0ca" width="500" alt="回数券登録APIの成功レスポンス">

### ・全件ユーザー情報及び特定のユーザー(名前での指定)情報の取得
<img src="https://github.com/user-attachments/assets/09c20a56-973e-4f86-be33-e270d1d2c221" width="500" alt="全件ユーザー情報APIの成功レスポンス">
<img src="https://github.com/user-attachments/assets/a901aded-b60c-44dc-a90a-ebc9f31c636b" width="500" alt="特定のユーザー情報APIの成功レスポンス">

### ・既存のユーザー情報及び回数券の更新
<img src="https://github.com/user-attachments/assets/88f0b200-3923-4f38-9098-669adbf8c1fc" width="500" alt="ユーザー情報更新APIの成功レスポンス">
<img src="https://github.com/user-attachments/assets/59e7725a-0cf5-47a2-8763-29ee8cb75029" width="500" alt="回数券情報更新APIの成功レスポンス">

### ・既存のユーザー情報及び回数券の更新確認
<img src="https://github.com/user-attachments/assets/dab42958-8fc6-4339-a427-21897838b733" width="500" alt="ユーザー情報更新確認">

### ・特定のユーザー情報とそのユーザーが持つ回数券情報を一括削除
<img src="https://github.com/user-attachments/assets/cac25746-8233-4a0e-8dc5-6e5140c2f3ab" width="500" alt="ユーザー情報の一括削除の成功レスポンス">


### ・特定のユーザー情報とそのユーザーが持つ回数券情報を一括削除の確認
<img src="https://github.com/user-attachments/assets/74fbb739-cb06-4242-bf0e-03e6ac4ae120" width="500" alt="ユーザー情報一括削除確認">

## ポストマン入力時のバリデーションエラー確認
<img src="https://github.com/user-attachments/assets/9ecd0c16-9723-4396-84af-3ca9e009291b" width="500" alt="ユーザーオブジェクトのバリデーションエラー">
<img src="https://github.com/user-attachments/assets/8fbaddde-24ac-4d7a-bfc2-d05eec2b0173" width="500" alt="チケットオブジェクトのバリデーションエラー">

## テスト結果
<img src="https://github.com/user-attachments/assets/625b4fef-eb94-4352-bbb7-aaba940468a1">

## 開発で特に重点を置いた点
###  DTOクラスのバリデーションテスト
・@ParameterizedTest、@MethodSourceを使用し、1つのメソッドで複数の入力値エラーのテストができるように設計しました。
 今後フィールドの追加やバリデーション内容が増えた際にその値だけを追加するだけで良いため、冗長なコードを削減できます。

### CIの導入
・GitHub Actionsを設定し、コミットごとに自動でテストが実行される環境を構築しました。
