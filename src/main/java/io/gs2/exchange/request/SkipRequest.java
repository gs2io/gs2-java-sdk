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
 * 交換待機を対価を払ってスキップ のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class SkipRequest extends Gs2BasicRequest<SkipRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return 交換待機を対価を払ってスキップ
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 交換待機を対価を払ってスキップ
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 交換待機を対価を払ってスキップ
     * @return this
     */
    public SkipRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** 交換レート名 */
    private String rateName;

    /**
     * 交換レート名を取得
     *
     * @return 交換待機を対価を払ってスキップ
     */
    public String getRateName() {
        return rateName;
    }

    /**
     * 交換レート名を設定
     *
     * @param rateName 交換待機を対価を払ってスキップ
     */
    public void setRateName(String rateName) {
        this.rateName = rateName;
    }

    /**
     * 交換レート名を設定
     *
     * @param rateName 交換待機を対価を払ってスキップ
     * @return this
     */
    public SkipRequest withRateName(String rateName) {
        setRateName(rateName);
        return this;
    }

    /** 交換待機の名前 */
    private String awaitName;

    /**
     * 交換待機の名前を取得
     *
     * @return 交換待機を対価を払ってスキップ
     */
    public String getAwaitName() {
        return awaitName;
    }

    /**
     * 交換待機の名前を設定
     *
     * @param awaitName 交換待機を対価を払ってスキップ
     */
    public void setAwaitName(String awaitName) {
        this.awaitName = awaitName;
    }

    /**
     * 交換待機の名前を設定
     *
     * @param awaitName 交換待機を対価を払ってスキップ
     * @return this
     */
    public SkipRequest withAwaitName(String awaitName) {
        setAwaitName(awaitName);
        return this;
    }

    /** 設定値 */
    private List<Config> config;

    /**
     * 設定値を取得
     *
     * @return 交換待機を対価を払ってスキップ
     */
    public List<Config> getConfig() {
        return config;
    }

    /**
     * 設定値を設定
     *
     * @param config 交換待機を対価を払ってスキップ
     */
    public void setConfig(List<Config> config) {
        this.config = config;
    }

    /**
     * 設定値を設定
     *
     * @param config 交換待機を対価を払ってスキップ
     * @return this
     */
    public SkipRequest withConfig(List<Config> config) {
        setConfig(config);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return 交換待機を対価を払ってスキップ
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider 交換待機を対価を払ってスキップ
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider 交換待機を対価を払ってスキップ
     * @return this
     */
    public SkipRequest withDuplicationAvoider(String duplicationAvoider) {
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
    public SkipRequest withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

}