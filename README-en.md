[⇒日本語のREADMEへ](README.md)

# gs2-java-sdk

SDK for Game Server Services(https://gs2.io) in Java.

## What is Game Server Services?

Game Server Services(GS2) is a back-end server service (BaaS) specialized for game development.

GS2 is a general-purpose game server solution created to improve efficiency for game developers and supports Games as a Service (GaaS) and Live Gaming.

The service allows for flexible management of player data and data analysis, enabling proper analysis of in-game resource distribution and consumption to maintain a healthy environment.
In addition, it provides story progression management and possession management, contributing to game monetization and player engagement.
GS2 supports online functionality and makes it easy for game developers to analyze data and manage economics to help their games succeed.

## Getting Started

GS2 credentials are required to use the SDK.
Follow the instructions in [GS2 Setup](https://docs.gs2.io/en/get_start/tutorial/setup_gs2/) to issue the credential.

### Requirements

- Java8+
- Maven2+

[⇒Start using GS2 - SDK - Various programming languages](https://docs.gs2.io/en/get_start/#various-programming-languages)

## SDK Installation

Install GS2-SDK for all services

```xml
<dependencies>
  <dependency>
    <groupId>io.gs2</groupId>
    <artifactId>gs2-java-sdk</artifactId>
  </dependency>
</dependencies>
```

### Initialization

GS2-Account HTTP & Synchronization Example

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

## SDK detailed specifications

For details on the API for each service and communication method, please refer to the

 [⇒API Reference](https://docs.gs2.io/en/api_reference/) page.

For information on the initialization process, please refer to the

 [⇒API Reference - Initialization process](https://docs.gs2.io/en/api_reference/initialize/) page.

*All code in this project is auto-generated except for gs2-java-sdk-core, so we cannot respond to individual Pull-Requests. *
