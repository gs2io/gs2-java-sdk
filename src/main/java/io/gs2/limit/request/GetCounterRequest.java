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

package io.gs2.limit.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.limit.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * カウンターを取得 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class GetCounterRequest extends Gs2BasicRequest<GetCounterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return カウンターを取得
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName カウンターを取得
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName カウンターを取得
     * @return this
     */
    public GetCounterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** 回数制限の種類の名前 */
    private String limitName;

    /**
     * 回数制限の種類の名前を取得
     *
     * @return カウンターを取得
     */
    public String getLimitName() {
        return limitName;
    }

    /**
     * 回数制限の種類の名前を設定
     *
     * @param limitName カウンターを取得
     */
    public void setLimitName(String limitName) {
        this.limitName = limitName;
    }

    /**
     * 回数制限の種類の名前を設定
     *
     * @param limitName カウンターを取得
     * @return this
     */
    public GetCounterRequest withLimitName(String limitName) {
        setLimitName(limitName);
        return this;
    }

    /** カウンターの名前 */
    private String counterName;

    /**
     * カウンターの名前を取得
     *
     * @return カウンターを取得
     */
    public String getCounterName() {
        return counterName;
    }

    /**
     * カウンターの名前を設定
     *
     * @param counterName カウンターを取得
     */
    public void setCounterName(String counterName) {
        this.counterName = counterName;
    }

    /**
     * カウンターの名前を設定
     *
     * @param counterName カウンターを取得
     * @return this
     */
    public GetCounterRequest withCounterName(String counterName) {
        setCounterName(counterName);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return カウンターを取得
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider カウンターを取得
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider カウンターを取得
     * @return this
     */
    public GetCounterRequest withDuplicationAvoider(String duplicationAvoider) {
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
    public GetCounterRequest withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

}