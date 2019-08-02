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

package io.gs2.lock.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.lock.model.*;
import io.gs2.control.Gs2BasicRequest;

/**
 * ミューテックスを取得 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class LockRequest extends Gs2BasicRequest<LockRequest> {

    /** カテゴリー名 */
    private String namespaceName;

    /**
     * カテゴリー名を取得
     *
     * @return ミューテックスを取得
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * カテゴリー名を設定
     *
     * @param namespaceName ミューテックスを取得
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * カテゴリー名を設定
     *
     * @param namespaceName ミューテックスを取得
     * @return this
     */
    public LockRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** プロパティID */
    private String propertyId;

    /**
     * プロパティIDを取得
     *
     * @return ミューテックスを取得
     */
    public String getPropertyId() {
        return propertyId;
    }

    /**
     * プロパティIDを設定
     *
     * @param propertyId ミューテックスを取得
     */
    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    /**
     * プロパティIDを設定
     *
     * @param propertyId ミューテックスを取得
     * @return this
     */
    public LockRequest withPropertyId(String propertyId) {
        setPropertyId(propertyId);
        return this;
    }

    /** ロックを取得するトランザクションID */
    private String transactionId;

    /**
     * ロックを取得するトランザクションIDを取得
     *
     * @return ミューテックスを取得
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * ロックを取得するトランザクションIDを設定
     *
     * @param transactionId ミューテックスを取得
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * ロックを取得するトランザクションIDを設定
     *
     * @param transactionId ミューテックスを取得
     * @return this
     */
    public LockRequest withTransactionId(String transactionId) {
        setTransactionId(transactionId);
        return this;
    }

    /** ロックを取得する期限（秒） */
    private Long ttl;

    /**
     * ロックを取得する期限（秒）を取得
     *
     * @return ミューテックスを取得
     */
    public Long getTtl() {
        return ttl;
    }

    /**
     * ロックを取得する期限（秒）を設定
     *
     * @param ttl ミューテックスを取得
     */
    public void setTtl(Long ttl) {
        this.ttl = ttl;
    }

    /**
     * ロックを取得する期限（秒）を設定
     *
     * @param ttl ミューテックスを取得
     * @return this
     */
    public LockRequest withTtl(Long ttl) {
        setTtl(ttl);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return ミューテックスを取得
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ミューテックスを取得
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ミューテックスを取得
     * @return this
     */
    public LockRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

    /** アクセストークン */
    private String accessToken;

    /**
     * アクセストークンを取得
     *
     * @return アクセストークン
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * アクセストークンを設定
     *
     * @param accessToken アクセストークン
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * アクセストークンを設定
     *
     * @param accessToken アクセストークン
     * @return this
     */
    public LockRequest withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

}