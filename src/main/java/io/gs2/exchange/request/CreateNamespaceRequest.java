/*
 * Copyright 2016 Game Server Services, Inc. or its affiliates. All Rights
 * Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package io.gs2.exchange.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.exchange.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * ネームスペースを新規作成 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateNamespaceRequest extends Gs2BasicRequest<CreateNamespaceRequest> {

    /** ネームスペース名 */
    private String name;

    /**
     * ネームスペース名を取得
     *
     * @return ネームスペースを新規作成
     */
    public String getName() {
        return name;
    }

    /**
     * ネームスペース名を設定
     *
     * @param name ネームスペースを新規作成
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * ネームスペース名を設定
     *
     * @param name ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withName(String name) {
        setName(name);
        return this;
    }

    /** ネームスペースの説明 */
    private String description;

    /**
     * ネームスペースの説明を取得
     *
     * @return ネームスペースを新規作成
     */
    public String getDescription() {
        return description;
    }

    /**
     * ネームスペースの説明を設定
     *
     * @param description ネームスペースを新規作成
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * ネームスペースの説明を設定
     *
     * @param description ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** 交換結果の受け取りに待ち時間の発生する交換機能を利用するか */
    private Boolean enableAwaitExchange;

    /**
     * 交換結果の受け取りに待ち時間の発生する交換機能を利用するかを取得
     *
     * @return ネームスペースを新規作成
     */
    public Boolean getEnableAwaitExchange() {
        return enableAwaitExchange;
    }

    /**
     * 交換結果の受け取りに待ち時間の発生する交換機能を利用するかを設定
     *
     * @param enableAwaitExchange ネームスペースを新規作成
     */
    public void setEnableAwaitExchange(Boolean enableAwaitExchange) {
        this.enableAwaitExchange = enableAwaitExchange;
    }

    /**
     * 交換結果の受け取りに待ち時間の発生する交換機能を利用するかを設定
     *
     * @param enableAwaitExchange ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withEnableAwaitExchange(Boolean enableAwaitExchange) {
        setEnableAwaitExchange(enableAwaitExchange);
        return this;
    }

    /** 直接交換APIの呼び出しを許可する。許可しない場合はスタンプシート経由でしか交換できない */
    private Boolean enableDirectExchange;

    /**
     * 直接交換APIの呼び出しを許可する。許可しない場合はスタンプシート経由でしか交換できないを取得
     *
     * @return ネームスペースを新規作成
     */
    public Boolean getEnableDirectExchange() {
        return enableDirectExchange;
    }

    /**
     * 直接交換APIの呼び出しを許可する。許可しない場合はスタンプシート経由でしか交換できないを設定
     *
     * @param enableDirectExchange ネームスペースを新規作成
     */
    public void setEnableDirectExchange(Boolean enableDirectExchange) {
        this.enableDirectExchange = enableDirectExchange;
    }

    /**
     * 直接交換APIの呼び出しを許可する。許可しない場合はスタンプシート経由でしか交換できないを設定
     *
     * @param enableDirectExchange ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withEnableDirectExchange(Boolean enableDirectExchange) {
        setEnableDirectExchange(enableDirectExchange);
        return this;
    }

    /** 交換処理をジョブとして追加するキューのネームスペース のGRN */
    private String queueNamespaceId;

    /**
     * 交換処理をジョブとして追加するキューのネームスペース のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getQueueNamespaceId() {
        return queueNamespaceId;
    }

    /**
     * 交換処理をジョブとして追加するキューのネームスペース のGRNを設定
     *
     * @param queueNamespaceId ネームスペースを新規作成
     */
    public void setQueueNamespaceId(String queueNamespaceId) {
        this.queueNamespaceId = queueNamespaceId;
    }

    /**
     * 交換処理をジョブとして追加するキューのネームスペース のGRNを設定
     *
     * @param queueNamespaceId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withQueueNamespaceId(String queueNamespaceId) {
        setQueueNamespaceId(queueNamespaceId);
        return this;
    }

    /** 交換処理のスタンプシートで使用する暗号鍵GRN */
    private String keyId;

    /**
     * 交換処理のスタンプシートで使用する暗号鍵GRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getKeyId() {
        return keyId;
    }

    /**
     * 交換処理のスタンプシートで使用する暗号鍵GRNを設定
     *
     * @param keyId ネームスペースを新規作成
     */
    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    /**
     * 交換処理のスタンプシートで使用する暗号鍵GRNを設定
     *
     * @param keyId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withKeyId(String keyId) {
        setKeyId(keyId);
        return this;
    }

    /** ログの出力設定 */
    private LogSetting logSetting;

    /**
     * ログの出力設定を取得
     *
     * @return ネームスペースを新規作成
     */
    public LogSetting getLogSetting() {
        return logSetting;
    }

    /**
     * ログの出力設定を設定
     *
     * @param logSetting ネームスペースを新規作成
     */
    public void setLogSetting(LogSetting logSetting) {
        this.logSetting = logSetting;
    }

    /**
     * ログの出力設定を設定
     *
     * @param logSetting ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withLogSetting(LogSetting logSetting) {
        setLogSetting(logSetting);
        return this;
    }

}