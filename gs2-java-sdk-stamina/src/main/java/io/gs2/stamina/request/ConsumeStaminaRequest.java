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

package io.gs2.stamina.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.stamina.model.*;
import io.gs2.control.Gs2BasicRequest;

/**
 * スタミナを消費 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class ConsumeStaminaRequest extends Gs2BasicRequest<ConsumeStaminaRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return スタミナを消費
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName スタミナを消費
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName スタミナを消費
     * @return this
     */
    public ConsumeStaminaRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** スタミナの種類名 */
    private String staminaName;

    /**
     * スタミナの種類名を取得
     *
     * @return スタミナを消費
     */
    public String getStaminaName() {
        return staminaName;
    }

    /**
     * スタミナの種類名を設定
     *
     * @param staminaName スタミナを消費
     */
    public void setStaminaName(String staminaName) {
        this.staminaName = staminaName;
    }

    /**
     * スタミナの種類名を設定
     *
     * @param staminaName スタミナを消費
     * @return this
     */
    public ConsumeStaminaRequest withStaminaName(String staminaName) {
        setStaminaName(staminaName);
        return this;
    }

    /** 消費するスタミナ量 */
    private Integer consumeValue;

    /**
     * 消費するスタミナ量を取得
     *
     * @return スタミナを消費
     */
    public Integer getConsumeValue() {
        return consumeValue;
    }

    /**
     * 消費するスタミナ量を設定
     *
     * @param consumeValue スタミナを消費
     */
    public void setConsumeValue(Integer consumeValue) {
        this.consumeValue = consumeValue;
    }

    /**
     * 消費するスタミナ量を設定
     *
     * @param consumeValue スタミナを消費
     * @return this
     */
    public ConsumeStaminaRequest withConsumeValue(Integer consumeValue) {
        setConsumeValue(consumeValue);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return スタミナを消費
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider スタミナを消費
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider スタミナを消費
     * @return this
     */
    public ConsumeStaminaRequest withDuplicationAvoider(String duplicationAvoider) {
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
    public ConsumeStaminaRequest withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

}