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
 * メッセージを新規作成 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class SendMessageByUserIdRequest extends Gs2BasicRequest<SendMessageByUserIdRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return メッセージを新規作成
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName メッセージを新規作成
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName メッセージを新規作成
     * @return this
     */
    public SendMessageByUserIdRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return メッセージを新規作成
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId メッセージを新規作成
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId メッセージを新規作成
     * @return this
     */
    public SendMessageByUserIdRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** メッセージの内容に相当するメタデータ */
    private String metadata;

    /**
     * メッセージの内容に相当するメタデータを取得
     *
     * @return メッセージを新規作成
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * メッセージの内容に相当するメタデータを設定
     *
     * @param metadata メッセージを新規作成
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * メッセージの内容に相当するメタデータを設定
     *
     * @param metadata メッセージを新規作成
     * @return this
     */
    public SendMessageByUserIdRequest withMetadata(String metadata) {
        setMetadata(metadata);
        return this;
    }

    /** 開封時に実行する入手アクション */
    private List<AcquireAction> readAcquireActions;

    /**
     * 開封時に実行する入手アクションを取得
     *
     * @return メッセージを新規作成
     */
    public List<AcquireAction> getReadAcquireActions() {
        return readAcquireActions;
    }

    /**
     * 開封時に実行する入手アクションを設定
     *
     * @param readAcquireActions メッセージを新規作成
     */
    public void setReadAcquireActions(List<AcquireAction> readAcquireActions) {
        this.readAcquireActions = readAcquireActions;
    }

    /**
     * 開封時に実行する入手アクションを設定
     *
     * @param readAcquireActions メッセージを新規作成
     * @return this
     */
    public SendMessageByUserIdRequest withReadAcquireActions(List<AcquireAction> readAcquireActions) {
        setReadAcquireActions(readAcquireActions);
        return this;
    }

    /** メッセージの有効期限 */
    private Long expiresAt;

    /**
     * メッセージの有効期限を取得
     *
     * @return メッセージを新規作成
     */
    public Long getExpiresAt() {
        return expiresAt;
    }

    /**
     * メッセージの有効期限を設定
     *
     * @param expiresAt メッセージを新規作成
     */
    public void setExpiresAt(Long expiresAt) {
        this.expiresAt = expiresAt;
    }

    /**
     * メッセージの有効期限を設定
     *
     * @param expiresAt メッセージを新規作成
     * @return this
     */
    public SendMessageByUserIdRequest withExpiresAt(Long expiresAt) {
        setExpiresAt(expiresAt);
        return this;
    }

    /** メッセージの有効期限までの差分 */
    private TimeSpan expiresTimeSpan;

    /**
     * メッセージの有効期限までの差分を取得
     *
     * @return メッセージを新規作成
     */
    public TimeSpan getExpiresTimeSpan() {
        return expiresTimeSpan;
    }

    /**
     * メッセージの有効期限までの差分を設定
     *
     * @param expiresTimeSpan メッセージを新規作成
     */
    public void setExpiresTimeSpan(TimeSpan expiresTimeSpan) {
        this.expiresTimeSpan = expiresTimeSpan;
    }

    /**
     * メッセージの有効期限までの差分を設定
     *
     * @param expiresTimeSpan メッセージを新規作成
     * @return this
     */
    public SendMessageByUserIdRequest withExpiresTimeSpan(TimeSpan expiresTimeSpan) {
        setExpiresTimeSpan(expiresTimeSpan);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return メッセージを新規作成
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider メッセージを新規作成
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider メッセージを新規作成
     * @return this
     */
    public SendMessageByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}