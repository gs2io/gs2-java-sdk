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

package io.gs2.distributor.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.distributor.model.*;
import io.gs2.control.Gs2BasicRequest;

/**
 * スタンプシートのタスクを実行する のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class RunStampTaskRequest extends Gs2BasicRequest<RunStampTaskRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return スタンプシートのタスクを実行する
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName スタンプシートのタスクを実行する
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName スタンプシートのタスクを実行する
     * @return this
     */
    public RunStampTaskRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ディストリビューターの種類名 */
    private String distributorName;

    /**
     * ディストリビューターの種類名を取得
     *
     * @return スタンプシートのタスクを実行する
     */
    public String getDistributorName() {
        return distributorName;
    }

    /**
     * ディストリビューターの種類名を設定
     *
     * @param distributorName スタンプシートのタスクを実行する
     */
    public void setDistributorName(String distributorName) {
        this.distributorName = distributorName;
    }

    /**
     * ディストリビューターの種類名を設定
     *
     * @param distributorName スタンプシートのタスクを実行する
     * @return this
     */
    public RunStampTaskRequest withDistributorName(String distributorName) {
        setDistributorName(distributorName);
        return this;
    }

    /** 実行するスタンプタスク */
    private String stampTask;

    /**
     * 実行するスタンプタスクを取得
     *
     * @return スタンプシートのタスクを実行する
     */
    public String getStampTask() {
        return stampTask;
    }

    /**
     * 実行するスタンプタスクを設定
     *
     * @param stampTask スタンプシートのタスクを実行する
     */
    public void setStampTask(String stampTask) {
        this.stampTask = stampTask;
    }

    /**
     * 実行するスタンプタスクを設定
     *
     * @param stampTask スタンプシートのタスクを実行する
     * @return this
     */
    public RunStampTaskRequest withStampTask(String stampTask) {
        setStampTask(stampTask);
        return this;
    }

    /** スタンプシートの暗号化に使用した暗号鍵GRN */
    private String keyId;

    /**
     * スタンプシートの暗号化に使用した暗号鍵GRNを取得
     *
     * @return スタンプシートのタスクを実行する
     */
    public String getKeyId() {
        return keyId;
    }

    /**
     * スタンプシートの暗号化に使用した暗号鍵GRNを設定
     *
     * @param keyId スタンプシートのタスクを実行する
     */
    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    /**
     * スタンプシートの暗号化に使用した暗号鍵GRNを設定
     *
     * @param keyId スタンプシートのタスクを実行する
     * @return this
     */
    public RunStampTaskRequest withKeyId(String keyId) {
        setKeyId(keyId);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return スタンプシートのタスクを実行する
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider スタンプシートのタスクを実行する
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider スタンプシートのタスクを実行する
     * @return this
     */
    public RunStampTaskRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}