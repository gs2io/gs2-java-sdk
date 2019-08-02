# gs2-java-sdk

Game Server Services(https://gs2.io) を Java で利用するためのSDKです。

## Game Server Services とは

> Game Server Services(GS2) とはモバイルゲーム開発に特化したバックエンドサーバサービス(BaaS)です。
>
>   ゲーム開発に必要なネットワーク機能をコンポーネント化してサービスとして提供します。 ゲーム内から必要な一部のコンポーネント単位で利用することができるよう設計されており、手軽に・手頃な価格で・高性能なサーバ機能を利用できます。

[GS2-Document](https://app.gs2.io/docs/index.html) より

## Getting Started

SDKを利用するには GS2 のクレデンシャルが必要です。
[はじめかた](https://app.gs2.io/docs/index.html?java#get-start) の手順に従ってクレデンシャルを発行してください。

### 動作条件

- Java8+
- Maven2+

## SDK のインストール

すべてのサービスの GS2-SDK をインストール

```xml
<dependencies>
  <dependency>
    <groupId>io.gs2</groupId>
    <artifactId>gs2-java-sdk</artifactId>
  </dependency>
</dependencies>
```

GS2-SDK for Java はサービスごとにSDKをインストールして利用できます。
アプリケーションに必要なサービスのSDKを選択し、インストールして利用してください。

```xml
<dependencies>
  <dependency>
    <groupId>io.gs2.account</groupId>
    <artifactId>gs2-java-sdk-account</artifactId>
  </dependency>
  <dependency>
    <groupId>io.gs2.auth</groupId>
    <artifactId>gs2-java-sdk-auth</artifactId>
  </dependency>
  <dependency>
    <groupId>io.gs2.deploy</groupId>
    <artifactId>gs2-java-sdk-deploy</artifactId>
  </dependency>
</dependencies>
```

### 初期化

GS2-Account の HTTP & 同期処理 の例

```java
import io.gs2.core.model.Region;
import io.gs2.core.model.BasicGs2Credential;
import io.gs2.core.rest.Gs2RestSession;
import io.gs2.account.rest.Gs2AccountRestClient;

Gs2RestSession session = new Gs2RestSession(
    Region.AP_NORTHEAST_1,
    new BasicGs2Credential(
        'your client id',
        'your client secret'
    )
);
session.connect();
Gs2AccountRestClient client = new Gs2AccountRestClient(session);
```

その他のサービス・通信方式の初期化処理は [ドキュメント](https://app.gs2.io/docs/index.html?java#gs2-sdk-account-initialize) を参照ください

## ビルド方法

```bash
mvn clean install -Dgpg.skip=true
```

## フィードバック

フィードバックは [GS2-Feedback](https://github.com/gs2io/gs2-feedback/issues) にお願いします。

*本プロジェクトのコードは gs2-java-sdk-core 以外は全て自動生成されているため、個別に Pull-Request を頂いても対応できません。*

## SDK の詳細仕様

https://app.gs2.io/docs/index.html#gs2-sdk-gs2-sdk