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

package io.gs2.formation.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.formation.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * 署名付きスロットを使ってフォームを更新 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class AcquireActionsToFormPropertiesRequest extends Gs2BasicRequest<AcquireActionsToFormPropertiesRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return 署名付きスロットを使ってフォームを更新
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 署名付きスロットを使ってフォームを更新
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 署名付きスロットを使ってフォームを更新
     * @return this
     */
    public AcquireActionsToFormPropertiesRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return 署名付きスロットを使ってフォームを更新
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId 署名付きスロットを使ってフォームを更新
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId 署名付きスロットを使ってフォームを更新
     * @return this
     */
    public AcquireActionsToFormPropertiesRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** フォームの保存領域の名前 */
    private String moldName;

    /**
     * フォームの保存領域の名前を取得
     *
     * @return 署名付きスロットを使ってフォームを更新
     */
    public String getMoldName() {
        return moldName;
    }

    /**
     * フォームの保存領域の名前を設定
     *
     * @param moldName 署名付きスロットを使ってフォームを更新
     */
    public void setMoldName(String moldName) {
        this.moldName = moldName;
    }

    /**
     * フォームの保存領域の名前を設定
     *
     * @param moldName 署名付きスロットを使ってフォームを更新
     * @return this
     */
    public AcquireActionsToFormPropertiesRequest withMoldName(String moldName) {
        setMoldName(moldName);
        return this;
    }

    /** 保存領域のインデックス */
    private Integer index;

    /**
     * 保存領域のインデックスを取得
     *
     * @return 署名付きスロットを使ってフォームを更新
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * 保存領域のインデックスを設定
     *
     * @param index 署名付きスロットを使ってフォームを更新
     */
    public void setIndex(Integer index) {
        this.index = index;
    }

    /**
     * 保存領域のインデックスを設定
     *
     * @param index 署名付きスロットを使ってフォームを更新
     * @return this
     */
    public AcquireActionsToFormPropertiesRequest withIndex(Integer index) {
        setIndex(index);
        return this;
    }

    /** フォームのプロパティに適用する入手アクション */
    private AcquireAction acquireAction;

    /**
     * フォームのプロパティに適用する入手アクションを取得
     *
     * @return 署名付きスロットを使ってフォームを更新
     */
    public AcquireAction getAcquireAction() {
        return acquireAction;
    }

    /**
     * フォームのプロパティに適用する入手アクションを設定
     *
     * @param acquireAction 署名付きスロットを使ってフォームを更新
     */
    public void setAcquireAction(AcquireAction acquireAction) {
        this.acquireAction = acquireAction;
    }

    /**
     * フォームのプロパティに適用する入手アクションを設定
     *
     * @param acquireAction 署名付きスロットを使ってフォームを更新
     * @return this
     */
    public AcquireActionsToFormPropertiesRequest withAcquireAction(AcquireAction acquireAction) {
        setAcquireAction(acquireAction);
        return this;
    }

    /** 入手処理を登録する GS2-JobQueue のネームスペース のGRN */
    private String queueNamespaceId;

    /**
     * 入手処理を登録する GS2-JobQueue のネームスペース のGRNを取得
     *
     * @return 署名付きスロットを使ってフォームを更新
     */
    public String getQueueNamespaceId() {
        return queueNamespaceId;
    }

    /**
     * 入手処理を登録する GS2-JobQueue のネームスペース のGRNを設定
     *
     * @param queueNamespaceId 署名付きスロットを使ってフォームを更新
     */
    public void setQueueNamespaceId(String queueNamespaceId) {
        this.queueNamespaceId = queueNamespaceId;
    }

    /**
     * 入手処理を登録する GS2-JobQueue のネームスペース のGRNを設定
     *
     * @param queueNamespaceId 署名付きスロットを使ってフォームを更新
     * @return this
     */
    public AcquireActionsToFormPropertiesRequest withQueueNamespaceId(String queueNamespaceId) {
        setQueueNamespaceId(queueNamespaceId);
        return this;
    }

    /** スタンプシートの発行に使用する GS2-Key の暗号鍵 のGRN */
    private String keyId;

    /**
     * スタンプシートの発行に使用する GS2-Key の暗号鍵 のGRNを取得
     *
     * @return 署名付きスロットを使ってフォームを更新
     */
    public String getKeyId() {
        return keyId;
    }

    /**
     * スタンプシートの発行に使用する GS2-Key の暗号鍵 のGRNを設定
     *
     * @param keyId 署名付きスロットを使ってフォームを更新
     */
    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    /**
     * スタンプシートの発行に使用する GS2-Key の暗号鍵 のGRNを設定
     *
     * @param keyId 署名付きスロットを使ってフォームを更新
     * @return this
     */
    public AcquireActionsToFormPropertiesRequest withKeyId(String keyId) {
        setKeyId(keyId);
        return this;
    }

    /** 入手アクションに適用するコンフィグ */
    private List<AcquireActionConfig> config;

    /**
     * 入手アクションに適用するコンフィグを取得
     *
     * @return 署名付きスロットを使ってフォームを更新
     */
    public List<AcquireActionConfig> getConfig() {
        return config;
    }

    /**
     * 入手アクションに適用するコンフィグを設定
     *
     * @param config 署名付きスロットを使ってフォームを更新
     */
    public void setConfig(List<AcquireActionConfig> config) {
        this.config = config;
    }

    /**
     * 入手アクションに適用するコンフィグを設定
     *
     * @param config 署名付きスロットを使ってフォームを更新
     * @return this
     */
    public AcquireActionsToFormPropertiesRequest withConfig(List<AcquireActionConfig> config) {
        setConfig(config);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return 署名付きスロットを使ってフォームを更新
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider 署名付きスロットを使ってフォームを更新
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider 署名付きスロットを使ってフォームを更新
     * @return this
     */
    public AcquireActionsToFormPropertiesRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}