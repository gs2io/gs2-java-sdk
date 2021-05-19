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
import io.gs2.core.control.Gs2BasicRequest;

/**
 * スタミナの最大値をGS2-Experienceのステータスを使用して更新 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class SetMaxValueByStatusRequest extends Gs2BasicRequest<SetMaxValueByStatusRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return スタミナの最大値をGS2-Experienceのステータスを使用して更新
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName スタミナの最大値をGS2-Experienceのステータスを使用して更新
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName スタミナの最大値をGS2-Experienceのステータスを使用して更新
     * @return this
     */
    public SetMaxValueByStatusRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** スタミナの種類名 */
    private String staminaName;

    /**
     * スタミナの種類名を取得
     *
     * @return スタミナの最大値をGS2-Experienceのステータスを使用して更新
     */
    public String getStaminaName() {
        return staminaName;
    }

    /**
     * スタミナの種類名を設定
     *
     * @param staminaName スタミナの最大値をGS2-Experienceのステータスを使用して更新
     */
    public void setStaminaName(String staminaName) {
        this.staminaName = staminaName;
    }

    /**
     * スタミナの種類名を設定
     *
     * @param staminaName スタミナの最大値をGS2-Experienceのステータスを使用して更新
     * @return this
     */
    public SetMaxValueByStatusRequest withStaminaName(String staminaName) {
        setStaminaName(staminaName);
        return this;
    }

    /** 署名をつけるのに使用した暗号鍵 のGRN */
    private String keyId;

    /**
     * 署名をつけるのに使用した暗号鍵 のGRNを取得
     *
     * @return スタミナの最大値をGS2-Experienceのステータスを使用して更新
     */
    public String getKeyId() {
        return keyId;
    }

    /**
     * 署名をつけるのに使用した暗号鍵 のGRNを設定
     *
     * @param keyId スタミナの最大値をGS2-Experienceのステータスを使用して更新
     */
    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    /**
     * 署名をつけるのに使用した暗号鍵 のGRNを設定
     *
     * @param keyId スタミナの最大値をGS2-Experienceのステータスを使用して更新
     * @return this
     */
    public SetMaxValueByStatusRequest withKeyId(String keyId) {
        setKeyId(keyId);
        return this;
    }

    /** 署名対象のステータスボディ */
    private String signedStatusBody;

    /**
     * 署名対象のステータスボディを取得
     *
     * @return スタミナの最大値をGS2-Experienceのステータスを使用して更新
     */
    public String getSignedStatusBody() {
        return signedStatusBody;
    }

    /**
     * 署名対象のステータスボディを設定
     *
     * @param signedStatusBody スタミナの最大値をGS2-Experienceのステータスを使用して更新
     */
    public void setSignedStatusBody(String signedStatusBody) {
        this.signedStatusBody = signedStatusBody;
    }

    /**
     * 署名対象のステータスボディを設定
     *
     * @param signedStatusBody スタミナの最大値をGS2-Experienceのステータスを使用して更新
     * @return this
     */
    public SetMaxValueByStatusRequest withSignedStatusBody(String signedStatusBody) {
        setSignedStatusBody(signedStatusBody);
        return this;
    }

    /** ステータスの署名 */
    private String signedStatusSignature;

    /**
     * ステータスの署名を取得
     *
     * @return スタミナの最大値をGS2-Experienceのステータスを使用して更新
     */
    public String getSignedStatusSignature() {
        return signedStatusSignature;
    }

    /**
     * ステータスの署名を設定
     *
     * @param signedStatusSignature スタミナの最大値をGS2-Experienceのステータスを使用して更新
     */
    public void setSignedStatusSignature(String signedStatusSignature) {
        this.signedStatusSignature = signedStatusSignature;
    }

    /**
     * ステータスの署名を設定
     *
     * @param signedStatusSignature スタミナの最大値をGS2-Experienceのステータスを使用して更新
     * @return this
     */
    public SetMaxValueByStatusRequest withSignedStatusSignature(String signedStatusSignature) {
        setSignedStatusSignature(signedStatusSignature);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return スタミナの最大値をGS2-Experienceのステータスを使用して更新
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider スタミナの最大値をGS2-Experienceのステータスを使用して更新
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider スタミナの最大値をGS2-Experienceのステータスを使用して更新
     * @return this
     */
    public SetMaxValueByStatusRequest withDuplicationAvoider(String duplicationAvoider) {
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
    public SetMaxValueByStatusRequest withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

}