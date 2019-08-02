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
import io.gs2.control.Gs2BasicRequest;

/**
 * ユーザIDを指定してカウントアップ のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CountUpByUserIdRequest extends Gs2BasicRequest<CountUpByUserIdRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return ユーザIDを指定してカウントアップ
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ユーザIDを指定してカウントアップ
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ユーザIDを指定してカウントアップ
     * @return this
     */
    public CountUpByUserIdRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** 回数制限の種類の名前 */
    private String limitName;

    /**
     * 回数制限の種類の名前を取得
     *
     * @return ユーザIDを指定してカウントアップ
     */
    public String getLimitName() {
        return limitName;
    }

    /**
     * 回数制限の種類の名前を設定
     *
     * @param limitName ユーザIDを指定してカウントアップ
     */
    public void setLimitName(String limitName) {
        this.limitName = limitName;
    }

    /**
     * 回数制限の種類の名前を設定
     *
     * @param limitName ユーザIDを指定してカウントアップ
     * @return this
     */
    public CountUpByUserIdRequest withLimitName(String limitName) {
        setLimitName(limitName);
        return this;
    }

    /** カウンターの名前 */
    private String counterName;

    /**
     * カウンターの名前を取得
     *
     * @return ユーザIDを指定してカウントアップ
     */
    public String getCounterName() {
        return counterName;
    }

    /**
     * カウンターの名前を設定
     *
     * @param counterName ユーザIDを指定してカウントアップ
     */
    public void setCounterName(String counterName) {
        this.counterName = counterName;
    }

    /**
     * カウンターの名前を設定
     *
     * @param counterName ユーザIDを指定してカウントアップ
     * @return this
     */
    public CountUpByUserIdRequest withCounterName(String counterName) {
        setCounterName(counterName);
        return this;
    }

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return ユーザIDを指定してカウントアップ
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザIDを指定してカウントアップ
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザIDを指定してカウントアップ
     * @return this
     */
    public CountUpByUserIdRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** カウントアップする量 */
    private Integer countUpValue;

    /**
     * カウントアップする量を取得
     *
     * @return ユーザIDを指定してカウントアップ
     */
    public Integer getCountUpValue() {
        return countUpValue;
    }

    /**
     * カウントアップする量を設定
     *
     * @param countUpValue ユーザIDを指定してカウントアップ
     */
    public void setCountUpValue(Integer countUpValue) {
        this.countUpValue = countUpValue;
    }

    /**
     * カウントアップする量を設定
     *
     * @param countUpValue ユーザIDを指定してカウントアップ
     * @return this
     */
    public CountUpByUserIdRequest withCountUpValue(Integer countUpValue) {
        setCountUpValue(countUpValue);
        return this;
    }

    /** カウントアップを許容する最大値 を入力してください */
    private Integer maxValue;

    /**
     * カウントアップを許容する最大値 を入力してくださいを取得
     *
     * @return ユーザIDを指定してカウントアップ
     */
    public Integer getMaxValue() {
        return maxValue;
    }

    /**
     * カウントアップを許容する最大値 を入力してくださいを設定
     *
     * @param maxValue ユーザIDを指定してカウントアップ
     */
    public void setMaxValue(Integer maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * カウントアップを許容する最大値 を入力してくださいを設定
     *
     * @param maxValue ユーザIDを指定してカウントアップ
     * @return this
     */
    public CountUpByUserIdRequest withMaxValue(Integer maxValue) {
        setMaxValue(maxValue);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return ユーザIDを指定してカウントアップ
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザIDを指定してカウントアップ
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザIDを指定してカウントアップ
     * @return this
     */
    public CountUpByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}