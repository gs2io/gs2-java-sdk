[日本語](README.md) | [English](README-en.md) | **한국어**

# gs2-java-sdk

Game Server Services(https://gs2.io)를 Java에서 사용하기 위한 SDK입니다.

## Game Server Services란?

Game Server Services(GS2)는 게임 개발에 특화된 서비스형 백엔드(BaaS)입니다.

GS2는 게임 개발의 효율을 높이기 위해 만들어진 범용 게임 서버 솔루션으로, Games as a Service(GaaS)와 Live Gaming 등을 지원합니다.

플레이어 데이터를 유연하게 관리하고 분석할 수 있습니다. 게임 내 자원이 어떻게 유통되고 소비되는지 분석해 게임 경제의 균형을 유지할 수 있습니다.
또한 스토리 진행 관리와 소지품 관리 등의 기능을 제공하여 게임의 수익화와 플레이어 참여도 향상에 기여합니다.
GS2는 온라인 기능을 지원하고, 게임 개발자가 데이터 분석과 경제 관리를 손쉽게 할 수 있도록 하여 게임의 성공을 돕습니다.

## 시작하기

SDK를 사용하려면 GS2 크리덴셜(클라이언트 ID와 클라이언트 시크릿)이 필요합니다.
[GS2 설정](https://docs.gs2.io/ko/get_start/tutorial/setup_gs2/) 절차에 따라 크리덴셜을 발급해 주세요.

### 동작 환경

- Java 8 이상
- Android에서 사용하는 경우 API 26(Android 8.0) 이상
- Maven2+

[⇒GS2 이용 시작 - SDK - 각종 프로그래밍 언어](https://docs.gs2.io/ko/get_start/#%ea%b0%81%ec%a2%85-%ed%94%84%eb%a1%9c%ea%b7%b8%eb%9e%98%eb%b0%8d-%ec%96%b8%ec%96%b4)

## SDK 설치

모든 서비스의 GS2 SDK를 설치하려면 다음 의존성을 추가하세요. `GS2_SDK_VERSION`을 사용하려는 SDK 버전으로 바꿔 주세요.

```xml
<dependencies>
  <dependency>
    <groupId>io.gs2</groupId>
    <artifactId>gs2-java-sdk</artifactId>
    <version>GS2_SDK_VERSION</version>
  </dependency>
</dependencies>
```

### 초기화

GS2-Account의 HTTP & 동기 처리 예시

```java
import io.gs2.core.model.Region;
import io.gs2.core.model.BasicGs2Credential;
import io.gs2.core.net.Gs2RestSession;
import io.gs2.account.Gs2AccountRestClient;

BasicGs2Credential credential = new BasicGs2Credential(
    "your client id",
    "your client secret"
);
Gs2RestSession session = new Gs2RestSession(credential, Region.AP_NORTHEAST_1);
session.open();
Gs2AccountRestClient client = new Gs2AccountRestClient(session);
```

## SDK 상세 사양

서비스별·통신 방식별 API는 [API 레퍼런스](https://docs.gs2.io/ko/api_reference/)를, SDK 초기화 방법은 [초기화 처리](https://docs.gs2.io/ko/api_reference/initialize/) 문서를 참고해 주세요.

*이 프로젝트의 코드는 gs2-java-sdk-core를 제외하고 모두 자동 생성되므로, 개별 풀 리퀘스트는 수용할 수 없습니다.*
