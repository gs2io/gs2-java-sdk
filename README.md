[⇒README in English](README-en.md)

# gs2-java-sdk

Game Server Services(https://gs2.io) を Java で利用するためのSDKです。

## Game Server Services とは

Game Server Services(GS2) とはゲーム開発に特化したバックエンドサーバサービス(BaaS)です。

GS2は、ゲーム開発者の効率化を目指して生まれた汎用ゲームサーバーのソリューションであり、Games as a Service(GaaS) や Live Gaming などをサポートしています。

このサービスでは、プレイヤーデータの柔軟な管理やデータ分析が可能であり、ゲーム内の資源の流通や消費量を適切に分析して健全な環境を維持することができます。
さらに、ストーリー進行管理や所持品管理などの機能を提供し、ゲームの収益化やプレイヤーエンゲージメントの向上に貢献します。
GS2は、オンライン機能をサポートし、ゲーム開発者がデータの分析や経済管理を容易に行えるようにすることで、ゲームの成功を支援します。

## Getting Started

SDKを利用するには GS2 のクレデンシャルが必要です。
[GS2のセットアップ](https://docs.gs2.io/ja/get_start/tutorial/setup_gs2/) の手順に従ってクレデンシャルを発行してください。

### 動作条件

- Java8+
- Maven2+

[⇒GS2の利用を開始 - SDK - 各種プログラミング言語](https://docs.gs2.io/ja/get_start/#%E5%90%84%E7%A8%AE%E3%83%97%E3%83%AD%E3%82%B0%E3%83%A9%E3%83%9F%E3%83%B3%E3%82%B0%E8%A8%80%E8%AA%9E)

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

## SDK の詳細仕様

各種サービス・通信方式の詳細は

 [⇒API リファレンス](https://docs.gs2.io/ja/api_reference/)

 [⇒API リファレンス - 初期化処理](https://docs.gs2.io/ja/api_reference/initialize/)
 
をご参照ください。

*本プロジェクトのコードは gs2-java-sdk-core 以外は全て自動生成されているため、個別に Pull-Request を頂いても対応できません。*
