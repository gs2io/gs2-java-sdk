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

package io.gs2.inbox.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.inbox.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * 全ユーザに向けたメッセージを新規作成 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateGlobalMessageMasterRequest extends Gs2BasicRequest<CreateGlobalMessageMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return 全ユーザに向けたメッセージを新規作成
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 全ユーザに向けたメッセージを新規作成
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 全ユーザに向けたメッセージを新規作成
     * @return this
     */
    public CreateGlobalMessageMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** 全ユーザに向けたメッセージ名 */
    private String name;

    /**
     * 全ユーザに向けたメッセージ名を取得
     *
     * @return 全ユーザに向けたメッセージを新規作成
     */
    public String getName() {
        return name;
    }

    /**
     * 全ユーザに向けたメッセージ名を設定
     *
     * @param name 全ユーザに向けたメッセージを新規作成
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 全ユーザに向けたメッセージ名を設定
     *
     * @param name 全ユーザに向けたメッセージを新規作成
     * @return this
     */
    public CreateGlobalMessageMasterRequest withName(String name) {
        setName(name);
        return this;
    }

    /** 全ユーザに向けたメッセージの内容に相当するメタデータ */
    private String metadata;

    /**
     * 全ユーザに向けたメッセージの内容に相当するメタデータを取得
     *
     * @return 全ユーザに向けたメッセージを新規作成
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * 全ユーザに向けたメッセージの内容に相当するメタデータを設定
     *
     * @param metadata 全ユーザに向けたメッセージを新規作成
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * 全ユーザに向けたメッセージの内容に相当するメタデータを設定
     *
     * @param metadata 全ユーザに向けたメッセージを新規作成
     * @return this
     */
    public CreateGlobalMessageMasterRequest withMetadata(String metadata) {
        setMetadata(metadata);
        return this;
    }

    /** 開封時に実行する入手アクション */
    private List<AcquireAction> readAcquireActions;

    /**
     * 開封時に実行する入手アクションを取得
     *
     * @return 全ユーザに向けたメッセージを新規作成
     */
    public List<AcquireAction> getReadAcquireActions() {
        return readAcquireActions;
    }

    /**
     * 開封時に実行する入手アクションを設定
     *
     * @param readAcquireActions 全ユーザに向けたメッセージを新規作成
     */
    public void setReadAcquireActions(List<AcquireAction> readAcquireActions) {
        this.readAcquireActions = readAcquireActions;
    }

    /**
     * 開封時に実行する入手アクションを設定
     *
     * @param readAcquireActions 全ユーザに向けたメッセージを新規作成
     * @return this
     */
    public CreateGlobalMessageMasterRequest withReadAcquireActions(List<AcquireAction> readAcquireActions) {
        setReadAcquireActions(readAcquireActions);
        return this;
    }

    /** メッセージを受信したあとメッセージが削除されるまでの期間 */
    private TimeSpan expiresTimeSpan;

    /**
     * メッセージを受信したあとメッセージが削除されるまでの期間を取得
     *
     * @return 全ユーザに向けたメッセージを新規作成
     */
    public TimeSpan getExpiresTimeSpan() {
        return expiresTimeSpan;
    }

    /**
     * メッセージを受信したあとメッセージが削除されるまでの期間を設定
     *
     * @param expiresTimeSpan 全ユーザに向けたメッセージを新規作成
     */
    public void setExpiresTimeSpan(TimeSpan expiresTimeSpan) {
        this.expiresTimeSpan = expiresTimeSpan;
    }

    /**
     * メッセージを受信したあとメッセージが削除されるまでの期間を設定
     *
     * @param expiresTimeSpan 全ユーザに向けたメッセージを新規作成
     * @return this
     */
    public CreateGlobalMessageMasterRequest withExpiresTimeSpan(TimeSpan expiresTimeSpan) {
        setExpiresTimeSpan(expiresTimeSpan);
        return this;
    }

    /** 全ユーザに向けたメッセージの受信期限 */
    private Long expiresAt;

    /**
     * 全ユーザに向けたメッセージの受信期限を取得
     *
     * @return 全ユーザに向けたメッセージを新規作成
     */
    public Long getExpiresAt() {
        return expiresAt;
    }

    /**
     * 全ユーザに向けたメッセージの受信期限を設定
     *
     * @param expiresAt 全ユーザに向けたメッセージを新規作成
     */
    public void setExpiresAt(Long expiresAt) {
        this.expiresAt = expiresAt;
    }

    /**
     * 全ユーザに向けたメッセージの受信期限を設定
     *
     * @param expiresAt 全ユーザに向けたメッセージを新規作成
     * @return this
     */
    public CreateGlobalMessageMasterRequest withExpiresAt(Long expiresAt) {
        setExpiresAt(expiresAt);
        return this;
    }

}